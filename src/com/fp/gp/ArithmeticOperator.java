package com.fp.gp;

public  abstract class ArithmeticOperator implements Operator {

	public int argumentCount() {
		return 2;
	}

	public Class[] argumentTypes() {
		return new Class[]{Double.class, Double.class};
	}

	public Class returnType() {
		return Double.class;
	}

	public ArithmeticOperator protoCopy() {
		return this;
	}


}
