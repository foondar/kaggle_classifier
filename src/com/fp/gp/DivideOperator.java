package com.fp.gp;

public class DivideOperator extends ArithmeticOperator {

	@Override
	public Object evaluate(Object... arguments) {
		Double a1 = (Double) arguments[0];
		Double a2 = (Double) arguments[1];
		try {
			return a1/a2;
		} catch (Exception e) {
			return Double.POSITIVE_INFINITY;
		}
	}

	@Override
	public String getName() {
		return "Divide";
	}

}
