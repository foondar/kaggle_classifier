package com.fp.gp;

public class LessOp extends BinaryBoolOp{

	@Override
	public Class[] argumentTypes() {
		return new Class[]{Double.class, Double.class};
	}

	@Override
	public Object evaluate(Object... arguments) {
		Double arg1 = (Double)arguments[0];
		Double arg2 = (Double)arguments[1];
		return arg1 < arg2;
	}

	@Override
	public String getName() {
		return "Less";
	}

}
