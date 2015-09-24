package com.fp.gp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DefaultTreeGeneratorTest {
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
	public void testGetRandomOperator() {
		Operator op = gen.getRandomOperator(Double.class);
		assertTrue(op.returnType().equals(Double.class));
		assertTrue(ops.contains(op));
	}

	@Test
	public void testGetRandomLeaf() {

		Operator op = gen.getRandomLeaf(Double.class);
		assertTrue(op.returnType().equals(Double.class));
		assertTrue(ops.contains(op));
		assertTrue(op.argumentCount() == 0);
	}

	@Test
	public void testGenerate() {
		Node root = gen.generate(Double.class, 6);
		System.out.println(root.prettyPrint());
	}

}
