package com.fp.gp;

import java.util.ArrayList;
import java.util.List;

public class CompositeIndividual implements Individual {

	List <Individual> members = new ArrayList<Individual>();

	double fitness;
	double proportionalFitness;
	
	public CompositeIndividual(List<Individual> members) {
		this.members = members;
	}
	private static final long serialVersionUID = 1L;
	
	public Object evaluate(Double... data) {
		double sum = 0;
		for (Individual ind : members) {
			sum += (Double)ind.evaluate(data);
		}
		return sum / members.size();
	}

	/* (non-Javadoc)
	 * @see com.fp.gp.Individual#getProportionalFitness()
	 */
	@Override
	public double getProportionalFitness() {
		return proportionalFitness;
	}
	
	public void setProportionalFitness(double fit) {
		proportionalFitness = fit;
	}

	/* (non-Javadoc)
	 * @see com.fp.gp.Individual#getFitness()
	 */
	@Override
	public double getFitness() {
		return fitness;
	}

	/* (non-Javadoc)
	 * @see com.fp.gp.Individual#setFitness(double)
	 */
	@Override
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	@Override
	public int compareTo(Object arg) {
		Individual other = (Individual) arg;
		if (this.fitness < other.getFitness()) return 1;
		if (this.fitness == other.getFitness()) return 0;
		return -1;
	}

}
