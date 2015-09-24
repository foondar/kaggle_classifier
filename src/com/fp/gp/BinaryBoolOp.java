package com.fp.gp;

public abstract class BinaryBoolOp extends BooleanOperator {

	@Override
	public int argumentCount() {
		return 2;
	}
	
	public BinaryBoolOp protoCopy() {
		return this;
	}

}
