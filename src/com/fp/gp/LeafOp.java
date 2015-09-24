package com.fp.gp;

public abstract class LeafOp implements Operator {

	@Override
	public int argumentCount() {
		return 0;
	}

	@Override
	public Class[] argumentTypes() {
		return new Class[]{};
	}


}
