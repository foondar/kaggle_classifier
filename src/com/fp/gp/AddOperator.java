package com.fp.gp;

public class AddOperator extends ArithmeticOperator {

	@Override
	public Object evaluate(Object... arguments) {
		Double a1 = (Double) arguments[0];
		Double a2 = (Double) arguments[1];
		return a1+a2;
	}

	@Override
	public String getName() {
		return "Add";
	}

}
