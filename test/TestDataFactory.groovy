import com.fp.kaggle.wikipedia.MonthlyEdit

import java.util.Date;
import java.text.SimpleDateFormat;


class TestDataFactory {

	File f = new File("C:\\dev\\kaggle\\wikipedia\\monthly_edits.csv")

	File training = new File("C:\\dev\\kaggle\\wikipedia\\training.csv")
	File evaluation = new File("C:\\dev\\kaggle\\wikipedia\\evaluation.csv")

	public static void main(String[] args) {
		TestDataFactory factory = new TestDataFactory()
		factory.training.delete()
		factory.evaluation.delete()
		
		for (month in 1..12) {
			for (field in 1..4) {
				factory.training << month + "_" + field + ","
			}
		}
		factory.training << "prediction\n"
		factory.processEdits()
	}
		
	 def processEdits() {
		int current_id = -1
		List current_data = []
		boolean first = true
		f.splitEachLine(',') {
			if (!first){
				MonthlyEdit edit = MonthlyEdit.parseData(it)

				if (edit.id != current_id) {
					process_data(current_id,current_data)
					current_data = []
					current_id = edit.id
				}
				else {
					current_data << edit
				}
			}
			else {
				first = false
			}
		}
	}
	// 5/1/2001 to 8/1/2010
	def process_data(id,data) {
		Date finalMonth = new Date(110,8,1)

		List final_monthly_edits = []
		java.util.Date previous_date = null
		
		data.each {
			Date date = it.month
			
			if (previous_date == null) {
				previous_date = it.month.clone()
			}
			else if (it.month.month == previous_date.month + 1 ||
			(date.month == 0 && previous_date.month == 11 && date.year == previous_date.year + 1)) {
			}
			else {
				previous_date.month++
				while (previous_date < it.month) {
					MonthlyEdit empty_edit = new MonthlyEdit(id, previous_date.clone(), 0, 0f, 0f, 0f)
					final_monthly_edits << empty_edit
					previous_date.month++
				}
			}
			final_monthly_edits << it
			previous_date = it.month.clone()
		}
		if (previous_date != null) previous_date.month++
		while (previous_date != null && previous_date < finalMonth ) {
			MonthlyEdit empty_edit = new MonthlyEdit(id, previous_date.clone(), 0, 0f, 0f, 0f)
			final_monthly_edits << empty_edit
			previous_date.month++
		}
		println(id)
		generateTrainingData(final_monthly_edits)
		generateTestData(id, final_monthly_edits)
	}

	def generateTrainingData(List<MonthlyEdit> monthly_edits) {
		if (monthly_edits.size() > 16) {
			for (target in (monthly_edits.size()-20)..(monthly_edits.size() -5) ) {
				MonthlyEdit targetEdit = monthly_edits[target]
				for (prior_ndx in (target-12)..(target-1)) {
					MonthlyEdit priorEdit = monthly_edits[prior_ndx]
					appendPriorEvidence(priorEdit)
				}
				int total = targetEdit.total_edits
				for (targetRangeNdx in target..(target+4)) {
					MonthlyEdit nextEdit = monthly_edits[targetRangeNdx]
					total += nextEdit.total_edits
				}
				training << total + "\n"
			}
		}
	}
	
	def generateTestData(int id, List<MonthlyEdit> monthly_edits) {
		evaluation << id + ","
		if (monthly_edits.size() >= 12) {
			for (ndx in (monthly_edits.size() - 12)..monthly_edits.size()-1){
				MonthlyEdit prior = monthly_edits[ndx]
				evaluation << prior.total_edits + "," + prior.avg_article_size + "," + prior.avg_delta + "," + prior.reversion_percentage + ","
			}
		}
		else {
			int emptyMonthCount = 12 - monthly_edits.size();
			for (int i = 0; i < emptyMonthCount;i++) {
				evaluation << 0 + "," + 0 + "," + 0 + "," + 0 + ","
			}
			for (MonthlyEdit prior : monthly_edits) {
				evaluation << prior.total_edits + "," + prior.avg_article_size + "," + prior.avg_delta + "," + prior.reversion_percentage + ","
			}
		}
		evaluation << "\n"
	}

	def appendPriorEvidence(MonthlyEdit prior) {
		training << prior.total_edits + "," + prior.avg_article_size + "," + prior.avg_delta + "," + prior.reversion_percentage + ","
	}
}