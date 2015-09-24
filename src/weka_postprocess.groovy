
f = new File("output.txt")
n = 0
total_error = 0
squared_error = 0
assume_zero_squared_error = 0
f.splitEachLine(" +") {
	if (it != null && it.size() > 3) {
		n++
		float actual = Math.log(Float.parseFloat(it[2]) + 1)
		float predicted = Float.parseFloat(it[3])
		if (predicted < 0) predicted = 0
		predicted = Math.log(predicted + 1)
		error = actual - predicted
		total_error += error
		squared_error += error * error
		if (predicted < 0.95) {
			assume_zero_squared_error += (actual - 0.69) * (actual - 0.69)
		}
		else {
			assume_zero_squared_error += error * error
		}
	}
}

mean_error = total_error / n
mse = squared_error / n
rmse = Math.sqrt(mse)
mse_assumption = assume_zero_squared_error /n
a_rmse = Math.sqrt(mse_assumption)

println mean_error + ", " + rmse + ", " + a_rmse