package com.fp.gp;

public  abstract class ArithmeticOperator implements Operator {

	@Override
	public int argumentCount() {
		return 2;
	}

	@Override
	public Class[] argumentTypes() {
		return new Class[]{Double.class, Double.class};
	}

	@Override
	public Class returnType() {
		return Double.class;
	}

	@Override
	public ArithmeticOperator protoCopy() {
		return this;
	}


}
