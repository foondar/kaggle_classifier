package com.fp.gp;

public class ExpOperator extends UnaryArithmeticOperator {

	@Override
	public Object evaluate(Object... arguments) {
		Double arg = (Double)arguments[0];
		return Math.exp(arg);
	}

	@Override
	public String getName() {
		return "Exp";
	}

}
