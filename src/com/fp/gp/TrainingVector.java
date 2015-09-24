package com.fp.gp;

import java.util.ArrayList;
import java.util.List;

public class TrainingVector {
	List<Double> inputs = new ArrayList<Double>();
	public List<Double> getInputs() {
		return inputs;
	}

	public Double getOutput() {
		return output;
	}

	Double output;
	
	public TrainingVector(List<Double> input, Double output) {
		this.inputs.addAll(input);
		this.output = output;
	}
	
	public TrainingVector(List<Double> data) {
		this.output = data.remove(data.size() - 1);
		this.inputs.addAll(data);
	}
}
