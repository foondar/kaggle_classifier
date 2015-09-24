package com.fp.gp;

public class AbsOp extends UnaryArithmeticOperator {

	@Override
	public Object evaluate(Object... arguments) {
		Double arg = (Double)arguments[0];
		return Math.abs(arg);
	}

	@Override
	public String getName() {
		return "Abs";
	}

}
