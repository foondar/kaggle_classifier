package com.fp.gp;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BranchOpTest {

	Node root;
	Node boolNode;
	Node smallNode;
	Node largeNode;
	Node leftNode;
	Node rightNode;
	BranchOp bo;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bo = new BranchOp();
		root = new DefaultNode(bo);
		boolNode = new DefaultNode(new GreaterOp());
		smallNode = new DefaultNode(ConstantOp.ZER0);
		largeNode = new DefaultNode(ConstantOp.PI);
		leftNode = new DefaultNode(new ConstantOp(4444d));
		rightNode = new DefaultNode(new ConstantOp(-4444d));
		root.setChildren(boolNode,leftNode,rightNode);
		boolNode.setChildren(largeNode, smallNode);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testArgumentCount() {
		assertEquals(3, bo.argumentCount());
	}

	@Test
	public void testArgumentTypes() {
		assertEquals(Boolean.class, bo.argumentTypes()[0]);
		assertEquals(Double.class, bo.argumentTypes()[1]);
		assertEquals(Double.class, bo.argumentTypes()[2]);
	}

	@Test
	public void testEvaluate() {
		assertEquals(4444d, root.evaluate());
		boolNode.setChildren(smallNode, largeNode);
		assertEquals(-4444d, root.evaluate());
	}

	@Test
	public void testReturnType() {
		assertEquals(Double.class, root.returnType());
	}

}
