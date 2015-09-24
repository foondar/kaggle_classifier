package com.fp.gp;

public class DoubleInputOp extends DoubleLeafOp {

	private double value = 0d;
	private String name;
	
	public DoubleInputOp(String name) {
		this.name = name;
	}
	public void setValue(double val) {
		value = val;
	}
	
	public double getValue() {
		return value;
	}
	
	@Override
	public Object evaluate(Object... arguments) {
		return getValue();
	}

	@Override
	public DoubleInputOp protoCopy() {
		return this;
	}
	@Override
	public String getName() {
		return name;
	}
}
