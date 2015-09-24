package com.fp.gp;

import java.util.ArrayList;
import java.util.List;

public class RMSLESampleEvaluator implements Evaluator {

	List<TrainingVector> sample = new ArrayList<TrainingVector>();
	List<DoubleInputOp> inputs = null;
	
	public RMSLESampleEvaluator(List<DoubleInputOp> inputs) {
		this.inputs = inputs;
	}
	
	public void setInputs(List<DoubleInputOp> inputs) {
		this.inputs = inputs;
	}
	
	public void setSample(List<TrainingVector> sample) {
		this.sample.clear();
		this.sample.addAll(sample);
	}
	@Override
	public double evaluate(Individual ind) {
		
		double squareLogErrorSum = 0d;
		int n = 0;
		for (TrainingVector data : sample) {
			int ndx = 0;
			n++;
			for (DoubleInputOp input : inputs) {
				input.setValue(data.getInputs().get(ndx++));
			}
			Double input[] = new Double[data.getInputs().size()];
			int i = 0;
			for (Double in : data.getInputs()) {
				input[i++] = in; 
			}
			double result = (Double) ind.evaluate(input);
			
			if (Double.isNaN(result) || result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY || result < 0) result = 0;
			double correct = data.getOutput();
//			System.out.println("*- " + correct);
			squareLogErrorSum += Math.pow(Math.log(correct+1) - Math.log(result+1),2);
		}
		double rmsle = Math.sqrt(squareLogErrorSum/n);
		if (rmsle == Double.NaN) rmsle = 1000;
		return rmsle;
	}

}
