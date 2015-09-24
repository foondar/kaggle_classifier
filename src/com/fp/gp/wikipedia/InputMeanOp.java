package com.fp.gp.wikipedia;

import com.fp.gp.DoubleInputOp;
import com.fp.gp.DoubleLeafOp;
import com.fp.gp.Operator;

public class InputMeanOp extends DoubleLeafOp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DoubleInputOp[] inputs;
	
	public InputMeanOp(DoubleInputOp[] in) {
		inputs = in;
	}
	
	@Override
	public Operator protoCopy() {
		return this;
	}



	@Override
	public Object evaluate(Object... arguments) {
		double result = 0d;
		for (DoubleInputOp month : inputs) {
			result += (Double)month.evaluate(new Object[]{});
		}
		return result / (double)inputs.length;
	}

	@Override
	public String getName() {
		return "Input Mean";
	}

}
