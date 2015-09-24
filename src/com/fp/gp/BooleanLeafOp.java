package com.fp.gp;

public class BooleanLeafOp implements Operator {

	boolean value = true;
	
	public BooleanLeafOp(boolean val) {
		value = val;
	}
	
	@Override
	public Operator protoCopy() {
		return this;
	}

	@Override
	public int argumentCount() {
		return 0;
	}

	@Override
	public Class[] argumentTypes() {
		return new Class[]{};
	}

	@Override
	public Object evaluate(Object... arguments) {
		return value;
	}

	@Override
	public Class returnType() {
		return Boolean.class;
	}

	@Override
	public String getName() {
		if (value) return "TRUE";
		else return "FALSE";
	}

}
