package com.fp.gp;

public class SinOp extends UnaryArithmeticOperator {

	@Override
	public Object evaluate(Object... arguments) {
		Double arg = (Double)arguments[0];
		return Math.sin(arg);
	}

	@Override
	public String getName() {
		return "Sine";
	}

}
