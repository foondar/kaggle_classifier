package com.fp.gp;

public class TopFiveOp extends DoubleLeafOp {

	private Population pop;
	
	public TopFiveOp(Population pop) {
		this.pop  = pop;
	}
	
	@Override
	public Operator protoCopy() {
		return this;
	}

	@Override
	public Object evaluate(Object... arguments) {
		return pop.evaluate(arguments);
	}

	@Override
	public String getName() {
		return "Top Five";
	}

}
