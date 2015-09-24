package com.fp.gp;

import java.io.Serializable;
import java.util.List;


public interface Node extends Serializable{
	public Operator getOperator();
	public void setOperator(Operator o);
	public Class returnType();
	public Class[] argumentTypes();
	public int childCount();
	public Node getParent();
	public void setParent(Node parent);
	public boolean isLeaf();
	public boolean isRoot();
	public Node getRoot();
	public int getDepth();
	public int size();
	public List<Node> getDescendants();
	public List<Node> getChildren();
	public void setChildren(Node... children);
	public void replaceChild(Node oldChild, Node newChild);
	public String prettyPrint();
	public int getChildCount();
	public Object evaluate();
	public String getName();
	public Node copy();
}
