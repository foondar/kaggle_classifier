package com.fp.gp;

public interface Individual {

	public abstract double getProportionalFitness();

	public abstract double getFitness();

	public abstract void setFitness(double fitness);

	public abstract int compareTo(Object arg);

	public abstract Object evaluate(Double... data);

//	public abstract Node getFunctionGenome(int functionNdx);

//	public abstract void setFunctionGenome(int functionNdx, Node genome);

}