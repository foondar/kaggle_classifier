package com.fp.gp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DefaultNode implements Node {

	private Node parent;
	private Operator op;
	private List<Node> children = new ArrayList<Node>();

	public DefaultNode(Operator op) {
		super();
		this.op = op;
	}
	
	public DefaultNode clone() {
		DefaultNode copy = new DefaultNode(this.op);
		return copy;
	}
	
	@Override
	public Operator getOperator() {
		return op;
	}

	@Override
	public void setOperator(Operator o) {
		op = o;
	}

	@Override
	public Class returnType() {
		return op.returnType();
	}

	@Override
	public Class[] argumentTypes() {
		return op.argumentTypes();
	}

	@Override
	public int childCount() {
		return op.argumentCount();
	}

	@Override
	public boolean isLeaf() {
		return op.argumentCount() == 0;
	}

	@Override
	public List<Node> getChildren() {
		return children;
	}

	@Override
	public void replaceChild(Node oldChild, Node newChild) {
		if (!oldChild.returnType().equals(newChild.returnType())) {
			throw new IllegalArgumentException("return types of child nodes must be the same");
		}
		int ndx = children.indexOf(oldChild);
		children.set(ndx, newChild);
		oldChild.setParent(null);
		newChild.setParent(this);
	}

	@Override
	public int getChildCount() {
		return children.size();
	}

	@Override
	public Object evaluate() {
		Object[] args = new Object[getChildCount()];
		int i = 0;
		for (Node child : children) {
			args[i++] = child.evaluate();
		}
		return op.evaluate(args);
	}

	public Node getRoot() {
		if (this.getParent() == null) return this;
		else return this.getParent().getRoot();
	}
	
	public List<Node> getDescendants() {
		List<Node> desc = new LinkedList<Node>();
		for (Node child : getChildren()) {
			desc.add(child);
			desc.addAll(child.getDescendants());
		}
		return desc;
	}
	
	public int getDepth() {
		int depth = 0;
		Node cursor = this;
		while (cursor.getParent() != null) {
			cursor = cursor.getParent();
			depth++;
		}
		return depth;
	}

	public boolean isRoot() {
		return getParent() == null;
	}
	@Override
	public Node getParent() {
		return parent;
	}
	
	@Override
	public void setParent(Node parent) {
		this.parent = parent;
	}

	@Override
	public void setChildren(Node... children) {
		int ndx = 0;
		for (Node node : children) {
			if (!node.returnType().equals(this.argumentTypes()[ndx++])) throw new IllegalArgumentException("child node has wrong return type");
			node.setParent(this);
		}
		this.children.clear();
		this.children.addAll(Arrays.asList(children));
		
	}
	
	public String prettyPrint() {
		String out = "";
		
		for(int tab = 0; tab < getDepth();tab++) {
			out += "   ";
		}
		out += this.getOperator().getName();
		out += "\n";
		for (Node child : getChildren()) {
			out += child.prettyPrint();
		}
		
		return out;
	}

	@Override
	public Node copy() {
		 Node n = new DefaultNode(this.getOperator());
		 Node[] children = new Node[this.childCount()];
		 int ndx = 0;
		 for (Node child : this.getChildren()) {
			children[ndx++] = child.copy();
		 }
		 n.setChildren(children);
		 return n;
	}

	@Override
	public int size() {
		return getDescendants().size();
	}

	@Override
	public String getName() {
		return getOperator().getName();
	}
}