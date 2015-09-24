package com.fp.gp;

import java.io.Serializable;

public interface Operator extends Serializable{
	public Operator protoCopy();
	public int argumentCount();
	public Class[] argumentTypes();
	public Object evaluate(Object... arguments);
	public Class returnType();
	public String getName();
}
