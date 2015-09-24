package com.fp.gp;

public class RandomOp extends DoubleLeafOp {
	private double val;
	
	public RandomOp() {
		val = Math.random();
	}
	
	@Override
	public Object evaluate(Object... arguments) {
		return val;
	}

	@Override
	public RandomOp protoCopy() {
		return new RandomOp();
	}
	

	@Override
	public String toString() {
		String out = super.toString();
		out += ": " + val;
		return out;
	}

	@Override
	public String getName() {
		return Double.toString(val);
	}

}
