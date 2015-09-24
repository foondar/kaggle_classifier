package com.fp.gp;

public class SqrtOp extends UnaryArithmeticOperator {

	@Override
	public Object evaluate(Object... arguments) {
		Double arg = (Double)arguments[0];
		return Math.sqrt(arg);
	}

	@Override
	public String getName() {
		return "Sqrt";
	}

}
