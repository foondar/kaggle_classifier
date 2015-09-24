package com.fp.gp;

public class BranchOp implements Operator {

	@Override
	public int argumentCount() {
		return 3;
	}

	@Override
	public Class[] argumentTypes() {
		return new Class[]{Boolean.class, Double.class, Double.class};
	}

	@Override
	public Object evaluate(Object... arguments) {
		Boolean arg1 = (Boolean) arguments[0];
		Double arg2 = (Double) arguments[1];
		Double arg3 = (Double) arguments [2];
		return arg1 ? arg2 : arg3;
	}

	@Override
	public Class returnType() {
		return Double.class;
	}

	@Override
	public BranchOp protoCopy() {
		return this;
	}

	@Override
	public String getName() {
		return "Between";
	}

}
