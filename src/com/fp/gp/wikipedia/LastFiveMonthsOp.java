package com.fp.gp.wikipedia;

import com.fp.gp.DoubleInputOp;
import com.fp.gp.DoubleLeafOp;
import com.fp.gp.Operator;

public class LastFiveMonthsOp extends DoubleLeafOp {

	DoubleInputOp[] lastFiveMonths;
	
	public LastFiveMonthsOp(DoubleInputOp[] lastFive) {
		lastFiveMonths = lastFive;
	}
	
	@Override
	public Operator protoCopy() {
		return this;
	}



	@Override
	public Object evaluate(Object... arguments) {
		double result = 0d;
		for (DoubleInputOp month : lastFiveMonths) {
			result += (Double)month.evaluate(new Object[]{});
		}
		return result;
	}

	@Override
	public String getName() {
		return "Last Five Months";
	}



}
