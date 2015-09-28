package com.fp.gp;

public class AbsOp extends UnaryArithmeticOperator {

	public Object evaluate(Object... arguments) {
		Double arg = (Double)arguments[0];
		return Math.abs(arg);
	}

	public String getName() {
		return "Abs";
	}

}
