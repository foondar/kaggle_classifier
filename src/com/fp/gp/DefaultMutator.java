package com.fp.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DefaultMutator implements Mutator {

	private TreeGenerator treeGen;
	private Random rand = new Random(System.currentTimeMillis());
	public DefaultMutator(TreeGenerator treeGen) {
		this.treeGen = treeGen;
	}

	@Override
	public void mutate(DefaultIndividual ind) {
		if (rand.nextDouble() > 0.5) {
			Node target = getRandomChild(ind.getGenome());
			if (target != null) {
				Node targetParent = target.getParent();
				if (targetParent != null) {
					Node mutation = treeGen.generate(target.returnType(), 3);
					targetParent.replaceChild(target, mutation);
				}
			}
		}
		else {
			mutateConstant(ind);
		}
	}

	private void mutateConstant(DefaultIndividual ind) {
		List<Node> constants = new ArrayList<Node>();
		for (Node node : ind.getGenome().getDescendants()) {
			if (node.getOperator() instanceof ConstantOp) constants.add(node);
		}
		if (constants.size() == 0) return;
		Node target = constants.get(rand.nextInt(constants.size()));
		ConstantOp constant = (ConstantOp) target.getOperator().protoCopy();
//		System.out.print("mutating constant: " + constant.evaluate(new Object[]{}) );
		constant = new ConstantOp((Double)constant.evaluate(new Object[]{}) + rand.nextGaussian()/10d);
//		System.out.println(" to " + constant.evaluate(new Object[]{}));
		target.setOperator(constant);
		
		
	}
	
	private Node getRandomChild(Node parent) {
		List<Node> desc = parent.getDescendants();
		if (desc.size() <= 0) return null;
		return desc.get(rand .nextInt(desc.size()));
	}
}
