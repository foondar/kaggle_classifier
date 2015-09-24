package com.fp.gp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class DefaultTreeGenerator implements TreeGenerator {

	private static Random rand = new Random(System.currentTimeMillis());
	private Map<Class, List<Operator>> operatorsByType = new HashMap<Class, List<Operator>>();
	private Set<Operator> allOps = new HashSet<Operator>();
	private Map<Class, List<LeafOp>> leafOpsByType = new HashMap<Class, List<LeafOp>>();
	
	public DefaultTreeGenerator(List<Operator> ops) {
		
		for (Operator operator : ops) {
			Class returnType = operator.returnType();
			if (operator instanceof LeafOp) {
				if (!leafOpsByType.containsKey(returnType)) {
					leafOpsByType.put(returnType, new ArrayList<LeafOp>());
				}
				leafOpsByType.get(returnType).add((LeafOp)operator);
			}
			allOps.add(operator);
			if (!operatorsByType.containsKey(returnType)) {
				operatorsByType.put(returnType, new ArrayList<Operator>());
			}
			operatorsByType.get(returnType).add(operator);
		}
	}
	
	protected Operator getRandomOperator(Class returnType) {
		List<Operator> ops = operatorsByType.get(returnType);
		return ops.get(rand.nextInt(ops.size()));
	}
	
	protected LeafOp getRandomLeaf(Class returnType) {
		List<LeafOp> ops = leafOpsByType.get(returnType);
		return ops.get(rand.nextInt(ops.size()));
	}
	
	@Override
	public Node generate(Class rootReturnType, int maxDepth) {
		if (maxDepth <= 0) {
			try {
				Node n = new DefaultNode(getRandomLeaf(rootReturnType).protoCopy());
				return n;
			}
			catch(Exception e) {
//				System.out.println("can't find leaf: "+rootReturnType + ", " + maxDepth);
				Node n = new DefaultNode(getRandomOperator(rootReturnType).protoCopy());
				Node[] children = new Node[n.childCount()];
				for (int i = 0; i < n.childCount();i++) {
					Class retType = n.argumentTypes()[i];
					children[i] = generate(retType, maxDepth - 1);
				}
				n.setChildren(children);
				return n;
			}
		}
		else {
			Node n = new DefaultNode(getRandomOperator(rootReturnType).protoCopy());
			Node[] children = new Node[n.childCount()];
			for (int i = 0; i < n.childCount();i++) {
				Class retType = n.argumentTypes()[i];
				children[i] = generate(retType, maxDepth - 1);
			}
			n.setChildren(children);
			return n;
		}
	}

}
