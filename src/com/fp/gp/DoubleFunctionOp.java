package com.fp.gp;

import java.util.List;

public class DoubleFunctionOp extends ArithmeticOperator {


	Individual ind;
	public Individual getIndividual() {
		return ind;
	}

	public void setIndividual(Individual ind) {
		this.ind = ind;
	}

	//	Node genome;
	String name;
	DoubleInputOp leftInput;
	DoubleInputOp rightInput;
	int functionNdx = 0;
	
	List<Operator> availableOperators;

	public List<Operator> getAvailableOperators() {
		return availableOperators;
	}

	public DoubleFunctionOp(String name, Individual ind, int functionNdx, List<Operator> operators) {
		this.name = name;
		this.ind = ind;
		leftInput = new DoubleInputOp(name + ":left");
		rightInput = new DoubleInputOp(name + ":right");
		this.functionNdx = functionNdx;
		this.availableOperators = operators;
	}

	public DoubleInputOp getLeftInput() {
		return leftInput;
	}

	public void setLeftInput(DoubleInputOp leftInput) {
		this.leftInput = leftInput;
	}

	public DoubleInputOp getRightInput() {
		return rightInput;
	}

	public void setRightInput(DoubleInputOp rightInput) {
		this.rightInput = rightInput;
	}

	public Node getGenome() {
		return this.getIndividual().getFunctionGenome(functionNdx);
	}

	public void setGenome(Node genome) {
		this.getIndividual().setFunctionGenome(functionNdx, genome);
	}

	@Override
	public Object evaluate(Object... arguments) {
		leftInput.setValue((Double)arguments[0]);
		rightInput.setValue((Double)arguments[1]);
		return this.getGenome().evaluate();
	}

	@Override
	public String getName() {
		return name;
	}

}
