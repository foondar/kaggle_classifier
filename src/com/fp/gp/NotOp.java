package com.fp.gp;

public class NotOp extends BooleanOperator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Operator protoCopy() {
		return this;
	}

	@Override
	public int argumentCount() {
		return 1;
	}

	@Override
	public Class[] argumentTypes() {
		return new Class[]{Boolean.class};
	}

	@Override
	public Object evaluate(Object... arguments) {
		return !(Boolean)arguments[0];
	}

	@Override
	public String getName() {
		return "NOT";
	}

}
