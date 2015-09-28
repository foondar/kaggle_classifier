package com.fp.gp;

import java.util.ArrayList;
import java.util.List;

public class PopulationGenerator {
	List<DoubleFunctionOp> functions = new ArrayList<DoubleFunctionOp>(10);
	List<Operator> ops = new ArrayList<Operator>();
	
	public List<Operator> getOperators() {
		return ops;
	}

	public Population generate(int size, Class returnType, int depth, int minSize, List<Operator> otherOps, List<DoubleInputOp> inputs) {
		initOperators();
		ops.addAll(inputs);
		ops.addAll(otherOps);
//		generateFunctions(10);
		
		Population pop = new Population(inputs);
//		pop.setFunctions(functions);
		pop.setOperators(ops);
		pop.setInputs(inputs);
		
		TreeGenerator gen = new DefaultTreeGenerator(ops);
		for (int i = 0; i < size; i++) {
			Node genome = gen.generate(returnType, depth);
			while (genome.size() < minSize) {
				genome = gen.generate(returnType, depth);
			}
			DefaultIndividual ind = new DefaultIndividual(inputs);
			ind.setGenome(genome);
			pop.addIndividual(ind);
		}
		
		return pop;
	}
	
	public void addOperator(Operator op) {
		ops.add(op);
	}
	
	public void initOperators() {
		ops.clear();
		ops.add(new AddOperator());
		ops.add(new SubtractOperator());
		ops.add(new DivideOperator());
		ops.add(new MultiplyOperator());
//		ops.add(ConstantOp.PI);
		ops.add(ConstantOp.ZER0);
		ops.add(new RandomOp());
		ops.add(new BranchOp());
		ops.add(new GreaterOp());
		ops.add(new PowOp());
		ops.add(new ExpOperator());
		ops.add(new LogOperator());
		ops.add(ConstantOp.ONE);
		ops.add(new SqrtOp());
		ops.add(new BetweenOp());
		ops.add(new AndOp());
		ops.add(new OrOp());
		ops.add(new NotOp());
//		ops.add(new SinOp());
//		ops.add(new CosOp());
//		ops.add(new TanOp());
		ops.add(new BooleanLeafOp(true));
		ops.add(new BooleanLeafOp(false));
	}
	
/*	protected void generateFunctions(int count) {
		functions.clear();
		for (int i = 0; i < count;i++) {
			TreeGenerator gen = new DefaultTreeGenerator(ops);

			DoubleFunctionOp func = new DoubleFunctionOp("Function " + i);
			func.setGenome(gen.generate(Double.class, 3));
			ops.add(func);
			functions.add(func);
		}
	}
	*/
}
