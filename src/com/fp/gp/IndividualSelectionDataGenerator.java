package com.fp.gp;

import java.io.*;
import java.util.List;

public class IndividualSelectionDataGenerator {

	private List<Individual> individuals;
	PrintStream output;
	
	public IndividualSelectionDataGenerator(File outFile) {
		try {
			output = new PrintStream(outFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setIndividuals(List<Individual> ind) {
		this.individuals = ind;
	}
	
	public void generateSelectionData(List<Individual> ind, TrainingSet dataSet, int sampleSize) {
		this.setIndividuals(ind);
		int ndx = 0;
		for (String column : dataSet.getColumns()) {
			output.print(column);
			if (ndx++ < dataSet.getColumns().length -1) {
				output.print(",");
			}
		}
		output.println();
		for (TrainingVector data : dataSet.getTrainingSample(sampleSize)) {
			evaluate(data);
		}
		output.flush();
		output.close();
	}
	
	public void evaluate(TrainingVector data) {
		int i = 0;
		double bestScore = Double.MAX_VALUE;
		int bestIndex = -1;
		for (Individual ind : individuals) {
			Double[] input = new Double[data.getInputs().size()];
			input = data.getInputs().toArray(input);
			double result = (Double) ind.evaluate(input);
			double error = Math.pow(Math.log(result+1) - Math.log(data.getOutput() + 1), 2);
			if (error < bestScore) {
				bestScore = error;
				bestIndex = i;
			}
			i++;
		}
		
		for (Double input_val : data.getInputs()) {
			output.print(input_val + ",");
		}
		
		output.println(bestIndex + "\n");
	}
}
