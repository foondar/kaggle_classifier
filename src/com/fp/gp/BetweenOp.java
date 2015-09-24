package com.fp.gp;

public class BetweenOp extends BooleanOperator {

	@Override
	public Operator protoCopy() {
		return this;
	}

	@Override
	public int argumentCount() {
		return 3;
	}

	@Override
	public Class[] argumentTypes() {
		return new Class[]{Double.class, Double.class, Double.class};
	}

	@Override
	public Object evaluate(Object... arg) {
		double target = (Double)arg[0];
		double low = (Double)arg[1];
		double high = (Double)arg[2];
		return target > low && target < high;
	}

	@Override
	public String getName() {
		return "Between";
	}

}
