package com.fp.gp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DefaultIndividual implements Comparable, Serializable, Individual{
	Node genome;
	double fitness;
	double proportionalFitness;
	int functionCount = 0;
	
	public int getFunctionCount() {
		return functionCount;
	}

	List<Node> functionNodes = new ArrayList<Node>();
	
	List<DoubleInputOp> inputs = new ArrayList<DoubleInputOp>();
	
	public DefaultIndividual(List<DoubleInputOp> inputs, int functionCount) {
		this.inputs = inputs;
		this.functionCount = functionCount;
	}

	public Node getGenome() {
		return genome;
	}

	public void setGenome(Node genome) {
		this.genome = genome;
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

	/* (non-Javadoc)
	 * @see com.fp.gp.Individual#compareTo(java.lang.Object)
	 */
	
	@Override
	public  int compareTo(Object arg) {
		Individual other = (Individual) arg;
		if (this.fitness < other.getFitness()) return 1;
		if (this.fitness == other.getFitness()) return 0;
		return -1;
	}
	
	/* (non-Javadoc)
	 * @see com.fp.gp.Individual#evaluate(java.lang.Double)
	 */
	@Override
	public Object evaluate(Double... data) {
		int i = 0;
		boolean all_zeroes = true;
		for (DoubleInputOp input : inputs) {
			if (data[i] != 0) all_zeroes =false;
			input.setValue(data[i++]);
		}
		if (all_zeroes) return new Double(0);
		return (Double)((Long)Math.round((Double) getGenome().evaluate())).doubleValue();
	}
	
	public DefaultIndividual copy() {
		DefaultIndividual ind = new DefaultIndividual(inputs, functionCount);
		for (Node functionGenome : functionNodes) {
			ind.functionNodes.add(functionGenome.copy());
		}
		ind.setGenome(this.getGenome().copy());
		ind.setFitness(this.getFitness());
		
		return ind;
	}

	@Override
	public Node getFunctionGenome(int functionNdx) {
		if (functionNdx >= getFunctionCount()) throw new IllegalArgumentException("functionNdx >= functinCount");
		return functionNodes.get(functionNdx);
	}

	@Override
	public void setFunctionGenome(int functionNdx, Node genome) {
		if (functionNdx >= getFunctionCount()) throw new IllegalArgumentException("functionNdx >= functinCount");
		functionNodes.set(functionNdx, genome);
		
	}
}
