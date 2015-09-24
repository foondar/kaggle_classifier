package com.fp.gp;

public class LogOperator extends UnaryArithmeticOperator {

	@Override
	public Object evaluate(Object... arguments) {
		Double arg = (Double)arguments[0];
		return Math.log(arg);
	}

	@Override
	public String getName() {
		return "Log";
	}

}
