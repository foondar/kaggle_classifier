package com.fp.gp;
import java.util.List;

import weka.classifiers.meta.LogitBoost;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.trees.*;
import weka.core.*;

public class TunedCompositeTrainer {
	public TunedCompositeIndividual train(List<Individual> members, TrainingSet data, int sampleSize) {
		FastVector attributeVector = new FastVector();
		int ndx = 0;
		for (String column : data.getColumns()) {	
			Attribute attr;
			if (ndx++ < data.getColumns().length -1) {
				attr = new Attribute(column);
			}
			else {
				FastVector classes = new FastVector(members.size());
				for (int i = 0; i < members.size();i++) {
					classes.addElement(Integer.toString(i));
				}
				attr = new Attribute(column,classes);
			}
			
			attributeVector.addElement(attr);
		}
		
		Instances wekaData = new Instances("training", attributeVector, sampleSize);
		wekaData.setClassIndex(data.getColumns().length-1);
		List<TrainingVector> sample = data.getTrainingSample(sampleSize);
		for (TrainingVector datum : sample) {
			double[] wekaDatum = new double[datum.getInputs().size()+1];
			int i = 0;
			for (Double d : datum.getInputs()) {
				wekaDatum[i++] = d;
			}
			i = 0;
			double bestScore = Double.MAX_VALUE;
			int bestIndex = -1;
			for (Individual ind : members) {
				Double[] input = new Double[data.getInputs().size()];
				input = datum.getInputs().toArray(input);
				double result = (Double) ind.evaluate(input);
				double error = Math.pow(Math.log(result+1) - Math.log(datum.getOutput() + 1), 2);
				if (error < bestScore) {
					bestScore = error;
					bestIndex = i;
				}
				i++;
			}
			
			wekaDatum[wekaDatum.length-1] = (double)bestIndex;
			Instance inst = new Instance(1.0,wekaDatum);
			inst.setDataset(wekaData);
			wekaData.add(inst);
		}
		
		LogitBoost classifier = new LogitBoost();
//		DecisionTable classifier = new DecisionTable();
//		DecisionStump classifier = new DecisionStump();
//		J48 classifier = new J48();
		try {
			classifier.setNumFolds(5);
			classifier.buildClassifier(wekaData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new TunedCompositeIndividual(members, classifier, wekaData);
	}
}
