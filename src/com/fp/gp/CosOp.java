package com.fp.gp;

public class CosOp extends UnaryArithmeticOperator {

	@Override
	public Object evaluate(Object... arguments) {
		Double arg = (Double)arguments[0];
		return Math.cos(arg);
	}

	@Override
	public String getName() {
		return "Cos";
	}

}
