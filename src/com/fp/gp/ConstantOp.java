package com.fp.gp;

public class ConstantOp extends DoubleLeafOp {

	public static ConstantOp PI = new ConstantOp(Math.PI);
	public static ConstantOp ONE = new ConstantOp(1d);
	public static ConstantOp ZER0 = new ConstantOp(0);
	public static ConstantOp POS_INF = new ConstantOp(Double.POSITIVE_INFINITY);
	public static ConstantOp NEG_INF = new ConstantOp(Double.NEGATIVE_INFINITY);
	
	private double val;
	
	public ConstantOp (double val) {
		this.val = val;
	}
	@Override
	public Object evaluate(Object... arguments) {
		return val;
	}
	@Override
	public ConstantOp protoCopy() {
		ConstantOp copy = new ConstantOp(val);
		return copy;
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
