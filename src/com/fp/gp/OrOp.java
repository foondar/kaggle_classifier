package com.fp.gp;

public class OrOp extends BinaryBoolOp {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Class[] argumentTypes() {
		return new Class[]{Boolean.class, Boolean.class};
	}

	@Override
	public Object evaluate(Object... arguments) {
		Boolean a1 = (Boolean)arguments[0];
		Boolean a2 = (Boolean)arguments[1];
		return a1 || a2;
	}

	@Override
	public String getName() {
		return "OR";
	}

}
