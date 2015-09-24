package com.fp.gp;

import java.util.ArrayList;
import java.util.List;

public class GenerationGenerator {
	Mutator mutator;
	
	public GenerationGenerator(Mutator mut) {
		mutator = mut;
	}
	
	public void nextGen(Population pop) {
		Breeder breeder = new DefaultBreeder();
		List<DefaultIndividual> nextGen = new ArrayList<DefaultIndividual>(pop.size());
		for (int i = 0; i < 10;i++) nextGen.add(pop.mostFit());
		while (nextGen.size() < pop.size()) {
			double selection = Math.random();
			if (selection < 0.5 || pop.size() - nextGen.size() < 2) {
				DefaultIndividual clone;
				do {
					clone = pop.getFitIndividual().copy();
				} while (clone.getGenome().size() < 10);
				nextGen.add(clone);
			}
			else if (selection < 0.98) {
				DefaultIndividual mom = pop.getFitIndividual().copy();
				DefaultIndividual dad = pop.getFitIndividual().copy();
				breeder.crossbreed(mom.getGenome(), dad.getGenome());
				if (mom.getGenome().size() > 10) nextGen.add(mom);
				if (dad.getGenome().size() > 10) nextGen.add(dad);
			}
			else {
				DefaultIndividual mutant = pop.getFitIndividual().copy();
				mutator.mutate(mutant);
			}
		}
		
		pop.setIndividuals(nextGen);
	}
}
