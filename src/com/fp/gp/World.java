package com.fp.gp;

import java.io.File;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.fp.gp.wikipedia.InputMeanOp;
import com.fp.gp.wikipedia.LastFiveMonthsOp;

public class World {
	private TrainingSet data;
	RMSLESampleEvaluator evaluator;
	private DefaultIndividual benchmark;
	String fileRoot = "selection";
	int fileNum = 1;

	int runNumber = 0;

	TunedCompositeTrainer trainer = new TunedCompositeTrainer();

	//	Population population;

	private List<Continent> continents = new ArrayList<Continent>();

	public World(String trainingPath, int continentCount) {
		data = new TrainingSet();
		data.loadFromFile(new File(trainingPath));

		evaluator = new RMSLESampleEvaluator(data.getInputs());
		DoubleInputOp lastFiveInputs[] = new DoubleInputOp[5];
		for (int i = 0; i < 5;i++) {
			lastFiveInputs[i] = data.getInputs().get(i*4 + 7*4);
		}
		LastFiveMonthsOp lastFive = new LastFiveMonthsOp(lastFiveInputs);
		benchmark = new DefaultIndividual(data.getInputs());
		benchmark.setGenome(new DefaultNode(lastFive));	
		for (int i = 0; i < continentCount;i++) {
			continents.add(new Continent(trainingPath));
		}
	}

	public void test() {
		evaluator.setSample(data.getTrainingSample(100));
		int i = 0;

		double benchmark_rmsle = evaluator.evaluate(getBenchMark());
		List<Individual> best_list = getBestIndividuals();
		double worst_error = Double.MIN_VALUE;
		int worst_index = -1;
		for (Individual best : best_list) {
			double rmsle = evaluator.evaluate(best);
			double fitness = best.getFitness();
			if (fitness > worst_error) {
				worst_error = fitness;
				worst_index = i;
			}
			System.out.println ("Continent " + i++ + " = " + rmsle);
		}

		//		File outFile = new File(fileRoot + "_" + fileNum + ".csv");
		//		fileNum++;
		//		IndividualSelectionDataGenerator dataGen = new IndividualSelectionDataGenerator(outFile);
		//		dataGen.generateSelectionData(best_list, data, 1000);
		best_list.remove(worst_index);
		double rmsle = 0;
		if (runNumber++ % 5 == 0) {
			TunedCompositeIndividual tuned_composite = trainer.train(best_list, data, 1000);
			rmsle = evaluator.evaluate(tuned_composite);
			System.out.println("tuned = " + rmsle);
		}
		CompositeIndividual composite = new CompositeIndividual(best_list);
		rmsle = evaluator.evaluate(composite);
		System.out.println("Composite = " + rmsle);



		System.out.println("Benchmark = " + benchmark_rmsle);



	}

	public List<Individual> getBestIndividuals() {
		List<Individual> best = new ArrayList<Individual>(continents.size());
		for (Continent cont : continents) {
			best.add(cont.getComposite());
		}
		return best;
	}

	public CompositeIndividual getWorldComposite() {
		List<Individual> best = getBestIndividuals();
		CompositeIndividual composite = new CompositeIndividual(best);
		return composite;
	}

	public void evaluate() {
		Thread[] threads = new Thread[continents.size()];
		int i = 0;
		for (Continent cont : continents) {
			threads[i] = new Thread(new SelectionThread(cont));
			threads[i++].start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void evolve() {
		Thread[] threads = new Thread[continents.size()];
		int i = 0;
		for (Continent cont : continents) {
			threads[i] = new Thread(new GenerationThread(cont));
			threads[i++].start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private class SelectionThread implements Runnable {
		Continent continent;
		public SelectionThread(Continent pop) {
			continent = pop;
		}
		@Override
		public void run() {
			continent.evaluate();
		}		
	}

	private class GenerationThread implements Runnable {
		Continent continent;
		public GenerationThread(Continent pop) {
			continent = pop;
		}
		@Override
		public void run() {
			continent.evolve();
		}

	}

	public static void main(String args[]) {
		World world = new World("C:/dev/kaggle/wikipedia/training.csv", 5);
		for (int i= 0; i < 1000;i++) {
			world.evaluate();
			world.test();
			world.evolve();
		}
	}

	public Individual getBenchMark() {
		return benchmark;
	}


}
