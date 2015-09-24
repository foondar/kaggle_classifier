package com.fp.gp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DefaultNodeTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetOperator() {
		AddOperator addOp = new AddOperator();
		DefaultNode n = new DefaultNode(addOp);
		assertEquals(addOp, n.getOperator());
	}

	@Test
	public void testSetOperator() {
		fail("Not yet implemented");
	}

	@Test
	public void testReturnType() {
		AddOperator addOp = new AddOperator();
		DefaultNode n = new DefaultNode(addOp);
		assertEquals(Double.class, n.returnType());
	}

	@Test
	public void testArgumentTypes() {
		AddOperator addOp = new AddOperator();
		DefaultNode n = new DefaultNode(addOp);
		assertEquals(Double.class, n.argumentTypes()[0]);
	}

	@Test
	public void testChildCount() {
		AddOperator addOp = new AddOperator();
		DefaultNode n = new DefaultNode(addOp);
		assertEquals(2, n.childCount());
	}

	@Test
	public void testIsLeaf() {

		AddOperator addOp = new AddOperator();
		DefaultNode n = new DefaultNode(addOp);
		assertFalse(n.isLeaf());
		n.setOperator(ConstantOp.PI);
		assertTrue(n.isLeaf());
	}

	@Test
	public void testGetChildren() {
		AddOperator addOp = new AddOperator();
		ConstantOp one = ConstantOp.ONE;
		ConstantOp zero = ConstantOp.ZER0;
		Node c1 = new DefaultNode(one);
		Node c2 = new DefaultNode(zero);
		Node n = new DefaultNode(addOp);
		
		n.setChildren(c1, c2);
		
		assertTrue(n.getChildren().contains(c1) && n.getChildren().contains(c2));
		assertEquals(2, n.getChildren().size());
	}

	@Test
	public void testReplaceChild() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetChildCount() {
		AddOperator addOp = new AddOperator();
		ConstantOp one = ConstantOp.ONE;
		ConstantOp zero = ConstantOp.ZER0;
		Node c1 = new DefaultNode(one);
		Node c2 = new DefaultNode(zero);
		Node n = new DefaultNode(addOp);
		
		n.setChildren(c1, c2);
		
		assertEquals(2, n.getChildCount());
	}

	@Test
	public void testEvaluate() {
		AddOperator addOp = new AddOperator();
		ConstantOp one = ConstantOp.ONE;
		ConstantOp zero = ConstantOp.ZER0;
		Node c1 = new DefaultNode(one);
		Node c2 = new DefaultNode(zero);
		Node n = new DefaultNode(addOp);

		n.setChildren(c1, c2);
		
		Node n2 = new DefaultNode(new ConstantOp(2.0));
		Node root = new DefaultNode(new DivideOperator());

		root.setChildren(n, n2);
		
		assertEquals(1.0, n.evaluate());
		assertEquals(0.5, root.evaluate());
	}

	@Test
	public void testGetRoot() {
		AddOperator addOp = new AddOperator();
		ConstantOp one = ConstantOp.ONE;
		ConstantOp zero = ConstantOp.ZER0;
		Node c1 = new DefaultNode(one);
		Node c2 = new DefaultNode(zero);
		Node n = new DefaultNode(addOp);

		n.setChildren(c1, c2);
		
		assertEquals(c1.getRoot(), n);
		assertEquals(n.getRoot(), n);
	}

	@Test
	public void testGetDescendants() {
		AddOperator addOp = new AddOperator();
		ConstantOp one = ConstantOp.ONE;
		ConstantOp zero = ConstantOp.ZER0;
		Node c1 = new DefaultNode(one);
		Node c2 = new DefaultNode(zero);
		Node n = new DefaultNode(addOp);

		n.setChildren(c1, c2);
		
		assertTrue(n.getDescendants().contains(c1));
	}

	@Test
	public void testGetDepth() {
		AddOperator addOp = new AddOperator();
		ConstantOp one = ConstantOp.ONE;
		ConstantOp zero = ConstantOp.ZER0;
		Node c1 = new DefaultNode(one);
		Node c2 = new DefaultNode(zero);
		Node n = new DefaultNode(addOp);

		n.setChildren(c1, c2);
		
		assertEquals(1, c1.getDepth());
		assertEquals(0,n.getDepth());
	}

	@Test
	public void testIsRoot() {
		AddOperator addOp = new AddOperator();
		ConstantOp one = ConstantOp.ONE;
		ConstantOp zero = ConstantOp.ZER0;
		Node c1 = new DefaultNode(one);
		Node c2 = new DefaultNode(zero);
		Node n = new DefaultNode(addOp);

		n.setChildren(c1, c2);
		
		assertTrue(n.isRoot());
		assertFalse(c2.isRoot());
	}
	
	@Test
	public void testPrettyPrint() {
		AddOperator addOp = new AddOperator();
		ConstantOp one = ConstantOp.ONE;
		ConstantOp zero = ConstantOp.ZER0;
		Node c1 = new DefaultNode(one);
		Node c2 = new DefaultNode(zero);
		Node n = new DefaultNode(addOp);

		n.setChildren(c1, c2);

		System.out.println(n.prettyPrint());
	}
	
		
//
//	@Test
//	public void testGetParent() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetParent() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetChildren() {
//		fail("Not yet implemented");
//	}

}
