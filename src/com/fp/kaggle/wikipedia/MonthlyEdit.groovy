package com.fp.kaggle.wikipedia
import java.text.SimpleDateFormat;

import org.apache.ivy.core.search.RevisionEntry;

class MonthlyEdit {
	Date month
	int id
	int total_edits
	float avg_article_size
	float avg_delta
	float reversion_percentage
	
	public MonthlyEdit(int id, Date month, int total_edits, float avg_article_size,
		float avg_delta, float reversion_percentage) {
		this.id = id
		this.month = month
		this.total_edits = total_edits
		this.avg_article_size = avg_article_size
		this.avg_delta = avg_delta
		this.reversion_percentage = reversion_percentage
	}
	
	public String toString() {
		return id + ", " + this.month.toString() + ", " + total_edits + ", " + avg_article_size + ", " + avg_delta + ", " + reversion_percentage
	}
	static SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy hh:mm")
	
	public static MonthlyEdit parseData(List data) {
		int id = Integer.parseInt(data[0])
		Date month = f.parse(data[1])
		int total_edits = Integer.parseInt(data[2])
		float avg_article_size = Float.parseFloat(data[3])
		float avg_delta = Float.parseFloat(data[4])
		float reversion_percentage = Float.parseFloat(data[5])
	
		return new MonthlyEdit(id, month, total_edits, avg_article_size, avg_delta, reversion_percentage)
	}
}
