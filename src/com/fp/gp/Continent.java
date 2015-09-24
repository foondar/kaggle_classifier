package com.fp.gp;

import java.io.File;

import java.util.LinkedList;
import java.util.List;

import com.fp.gp.wikipedia.InputMeanOp;
import com.fp.gp.wikipedia.LastFiveMonthsOp;

public class Continent {
	private TrainingSet data;
	private SampleNaturalSelector selector;
	private GenerationGenerator generationator;
	private DefaultIndividual benchmark;
	Population population;

	public Continent(String trainingPath) {
		data = new TrainingSet();
		data.loadFromFile(new File(trainingPath));
		selector = new SampleNaturalSelector(data);
		data.getInputs();
		DoubleInputOp lastFiveInputs[] = new DoubleInputOp[5];
		for (int i = 0; i < 5;i++) {
			lastFiveInputs[i] = data.getInputs().get(i*4 + 7*4);
		}
		LastFiveMonthsOp lastFive = new LastFiveMonthsOp(lastFiveInputs);

		DoubleInputOp lastTwelveInputs[] = new DoubleInputOp[12];
		for (int i = 0; i < 12; i++) {
			lastTwelveInputs[i] = data.getInputs().get(i*4);
		}
		InputMeanOp aveEdits = new InputMeanOp(lastTwelveInputs);

		benchmark = new DefaultIndividual(data.getInputs());
		benchmark.setGenome(new DefaultNode(lastFive));	

		PopulationGenerator popGenerator = new PopulationGenerator();
		List<Operator> others = new LinkedList<Operator>();
		others.add(lastFive);
		others.add(aveEdits);

		population = popGenerator.generate(1000, Double.class, 10, 10, others,data.getInputs());
		generationator = new GenerationGenerator(new DefaultMutator(new DefaultTreeGenerator(popGenerator.getOperators())));
	}

	public Individual getComposite() {
		return population.getTopFive();
	}
	
	public Individual mostFit() {
		return population.mostFit();
	}
	
	public Individual evaluate() {
		selector.select(population);//, benchmark);
		return population.mostFit();
	}

	public void evolve() {
		generationator.nextGen(population);
	}

	public Population getPopulation() {
		return population;
	}


	public Individual getBenchMark() {
		return benchmark;
	}


}
