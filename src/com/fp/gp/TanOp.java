package com.fp.gp;

public class TanOp extends UnaryArithmeticOperator {

	@Override
	public Object evaluate(Object... arguments) {
		Double arg = (Double)arguments[0];
		return Math.tan(arg);
	}

	@Override
	public String getName() {
		return "Tan";
	}

}
