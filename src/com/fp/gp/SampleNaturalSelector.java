package com.fp.gp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SampleNaturalSelector implements NaturalSelector {

	static Random rand = new Random(System.currentTimeMillis());
	TrainingSet trainingData;
	RMSLESampleEvaluator eval;
//	GenerationGenerator gen = new GenerationGenerator();
	List<DoubleInputOp> inputs = new ArrayList<DoubleInputOp>();
	List<TrainingVector> sample;
	int sample_size = 100;
	double sample_step = 1;
	int currentStep = 0;
	int changeStep = 2;
	Individual priorBest = null;
	
	public Individual getPriorBest() {
		return priorBest;
	}

	public SampleNaturalSelector(TrainingSet data) {
		trainingData = data;
		sample = trainingData.getTrainingSample(sample_size);
		eval = new RMSLESampleEvaluator(trainingData.getInputs());
	}
	
	@Override
	public void select(Population pop/*, Individual benchmark*/) {
//		List<TrainingVector> sample = trainingData.getTrainingSample(500);
		if (currentStep++ >= changeStep) {
			sample_size += sample_step;
			sample = trainingData.getTrainingSample(sample_size);
			currentStep = 0;
			priorBest = pop.mostFit();
		}
		eval.setSample(sample);
		double fitSum = 0d;
		double maxFit = Double.MIN_VALUE;
//		benchmark.setFitness(eval.evaluate(benchmark));
		pop.getTopFive().setFitness(eval.evaluate(pop.getTopFive()));
		for (Individual ind : pop.getIndividuals()) {
			double fit = eval.evaluate(ind);
			if (fit == Double.NaN) fit = 1000;
			ind.setFitness(fit);
			
			if (fit > maxFit) maxFit = fit;
		}

		for (DefaultIndividual ind : pop.getIndividuals()) {
			ind.setProportionalFitness(maxFit - ind.getFitness());

			fitSum += ind.getProportionalFitness();
		}

		pop.setFitnessSum(fitSum);
		double prop_sum = 0;
		for (Individual ind : pop.getIndividuals()) {
			prop_sum += ind.getProportionalFitness();
		}
//		gen.nextGen(pop);
	}
}
