package com.fp.gp;

import java.util.List;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;

public class TunedCompositeIndividual implements Individual {

	private List<Individual> members;
	private Classifier tuner;
	private Instances dataSet;

	public TunedCompositeIndividual(List<Individual> members, Classifier tuningClassifier, Instances dataSet) {
		this.members = members;
		this.tuner = tuningClassifier;
		this.dataSet = dataSet;
	}
	
	public double getProportionalFitness() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getFitness() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setFitness(double fitness) {
		// TODO Auto-generated method stub
		
	}

	public int compareTo(Object arg) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object evaluate(Double... data) {
		double[] wekaData = new double[data.length+1];
		int i = 0;
		for (double d : data) {
			wekaData[i++] = d;
		}
		
		Instance inst = new Instance(1, wekaData);
		inst.setDataset(dataSet);
		
		double[] distro;
		Double result = 0d;
		try {
			distro = tuner.distributionForInstance(inst);
//			for (double d : distro) {
//				System.out.print(d + " ");
//			}
//			System.out.println();
			i = 0;
			for (Individual ind : members) {
				result += distro[i++] * (Double)ind.evaluate(data);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return result;
	}

}
