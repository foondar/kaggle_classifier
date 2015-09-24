package com.fp.gp;

import java.util.List;
import java.util.Random;

public class DefaultBreeder implements Breeder {

	private static Random rand = new Random(System.currentTimeMillis());

	@Override
	public void crossbreed(Node n1, Node n2) {
		if (n1.childCount() > 0 && n2.childCount() > 0) {
			Node n1_child = getRandomChild(n1);
			Node n2_child = getRandomChild(n2, n1_child.returnType());
			if (n1_child == null || n2_child == null) return;
			Node n1_parent = n1_child.getParent();
			Node n2_parent = n2_child.getParent();
			n1_parent.replaceChild(n1_child, n2_child);
			n2_parent.replaceChild(n2_child, n1_child);
			n1_child.setParent(n2_parent);
			n2_child.setParent(n1_parent);
		}
	}
	
	private Node getRandomChild(Node parent, Class returnType) {
		Node child = getRandomChild(parent);
		if (child.returnType() != returnType) {
			child = null;
		}
		return child;
	}

	private Node getRandomChild(Node parent) {
		List<Node> desc = parent.getDescendants();
		if (desc.size() <= 0) return null;
		return desc.get(rand.nextInt(desc.size()));
	}

}
