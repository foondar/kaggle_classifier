
package com.fp.gp;

public abstract class UnaryArithmeticOperator extends ArithmeticOperator {

	@Override
	public Class[] argumentTypes() {
		return new Class[]{Double.class};
	}

	@Override
	public int argumentCount() {
		return 1;
	}


}
