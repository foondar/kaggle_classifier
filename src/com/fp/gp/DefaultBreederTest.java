package com.fp.gp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DefaultBreederTest {
	List<Operator> ops;
	DefaultTreeGenerator gen ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		ops = new ArrayList<Operator>();
		ops.add(new AddOperator());
		ops.add(new SubtractOperator());
		ops.add(new DivideOperator());
		ops.add(new MultiplyOperator());
		ops.add(ConstantOp.PI);
		ops.add(ConstantOp.ZER0);
		ops.add(new RandomOp());
		ops.add(new BranchOp());
		ops.add(new GreaterOp());
		gen = new DefaultTreeGenerator(ops);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCrossbreed() {
		Node n1 = gen.generate(Double.class, 3);
		Node n2 = gen.generate(Double.class, 3);
		
		System.out.println(n1.prettyPrint());
		System.out.println();
		System.out.println(n2.prettyPrint());
		System.out.println("***");
		
		Breeder b = new DefaultBreeder();
		
		b.crossbreed(n1, n2);
		
		System.out.println(n1.prettyPrint());
		System.out.println();
		System.out.println(n2.prettyPrint());
		
	}

}
