package com.fp.gp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Population implements Serializable {
	private List<DefaultIndividual> pop = new ArrayList<DefaultIndividual>();
	private List<DoubleFunctionOp> functions;
	private List<Operator> operators;
	private List<DoubleInputOp> inputs;
	
	private DefaultIndividual topFive;
	
	public Individual getTopFive() {
		return topFive;
	}

	public Population(List<DoubleInputOp> inputs) {
		this.inputs = inputs;
		topFive = new DefaultIndividual(inputs);
		topFive.setGenome(new DefaultNode(new TopFiveOp(this)));
	}
	
	public List<DoubleInputOp> getInputs() {
		return inputs;
	}

	public void setInputs(List<DoubleInputOp> inputs) {
		this.inputs = inputs;
	}

	double fitnessSum = 0d;
	
	public double getFitnessSum() {
		return fitnessSum;
	}

	public void setFitnessSum(double fitnessSum) {
		this.fitnessSum = fitnessSum;
	}

	public double meanFitness() {
		double sum = 0;
		double n = 0;
		for (Individual ind : pop) {
			sum += ind.getFitness();
			n++;
		}
		return sum/n;
	}
	
	public int size() {
		return pop.size();
	}
	
	public List<Operator> getOperators() {
		return operators;
	}

	public void setOperators(List<Operator> operators) {
		this.operators = operators;
	}

	public List<DefaultIndividual> getIndividuals() {
		return pop;
	}

	public void setIndividuals(List<DefaultIndividual> pop) {
		this.pop = pop;
	}

	public List<DoubleFunctionOp> getFunctions() {
		return functions;
	}
	
	public void addIndividual(DefaultIndividual ind) {
		pop.add(ind);
	}
	
	public void clear() {
		pop.clear();
		
	}

	public void setFunctions(List<DoubleFunctionOp> functions) {
		this.functions = functions;
	}
	
	public double evaluate(Object... arguments) {
		int ndx = 0;
		for (Object d : arguments) {
			inputs.get(ndx).setValue((Double)d);
		}
		
		double sum = 0;
		for (DefaultIndividual ind : topFive()) {
			sum += (Double)ind.getGenome().evaluate();
		}
		return sum/5;
	}

	public Set<DefaultIndividual> topFive() {
		TreeSet<DefaultIndividual> best = new TreeSet<DefaultIndividual>();
		for (DefaultIndividual ind : pop) {
			if (best.size() < 5) best.add(ind);
			else if (best.floor(ind) != null) {
				best.remove(best.floor(ind));
				best.add(ind);
			}
		}
		return best;
	}
	
	public DefaultIndividual mostFit() {
		DefaultIndividual best = null;
		for (DefaultIndividual ind : pop) {
			if (best == null) best = ind;
			else if (ind.compareTo(best) > 0) best = ind;
		}
		return best;
	}
	
	public List<DefaultIndividual> getFitnessProportionalSample(int size) {
		List<DefaultIndividual> sample = new ArrayList<DefaultIndividual>(size);
		
		for(int i = 0; i < size;i++) {
			sample.add(getFitIndividual());
		}
		
		return sample;
	}
	
	public DefaultIndividual getFitIndividual() {
		double selection = Math.random() * fitnessSum;
		double current = 0d;
		for (DefaultIndividual ind : getIndividuals()) {
			current += ind.proportionalFitness;
			if (current > selection) {
				return ind;
			}
		}
		return getIndividuals().get(0);// shouldn't get here
	}
}
