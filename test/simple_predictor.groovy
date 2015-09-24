import java.io.File;


File training = new File("C:\\dev\\kaggle\\wikipedia\\training.csv")
File predictions = new File("simple_predictions.csv")
boolean first = true
log_error_sum = 0
error_sum = 0
n = 0
training.splitEachLine(",") {
	if ( !first) {
		momentum_sum = 0
		estimate = calculate_estimate(it)
		actual = Integer.parseInt(it[48])
		log_error_sum += Math.pow(Math.log(actual + 1) - Math.log(estimate + 1),2)
		error_sum += actual - estimate
		n++
		//		println estimate + ", " + actual
	}
	else {
		first = false
	}
}
error_sum /= n
log_error_sum /= n
rmsle = Math.sqrt(log_error_sum)
println rmsle + ", " + error_sum

def calculate_estimate(data) {
	calculate_estimate_naive(data)
}

def calculate_estimate_naive(data) {
	edit_sum = 0
	for (ndx in (0..11)) {
		if (ndx >= 7) {
			int val = Integer.parseInt(data[ndx * 4])
			edit_sum += val
		}
	}
	//		if (lastVal == 0 && lastlast == 0) edit_sum = 0

	//		edit_sum += moment
	estimate = Math.round(edit_sum)
	if (estimate < 0) estimate = 0
	estimate
}

def calculate_estimate_exp(data) {
	int lastVal = 0
	lastlast = 0
	edit_sum = 0
	for (ndx in (0..11)) {
		int val = Integer.parseInt(data[ndx * 4])
		val *= ndx + 1
		edit_sum += val
		momentum_sum += (val - lastVal) * ndx

		lastlast = lastVal
		lastVal = val
	}
	moment = momentum_sum / (13.0 * 6.0)
	edit_sum /= (13.0 * 6.0)
	//		if (lastVal == 0 && lastlast == 0) edit_sum = 0

	//		edit_sum += moment
	edit_sum *= 3.4
	estimate = Math.round(edit_sum)
	if (estimate < 0) estimate = 0
	estimate
}