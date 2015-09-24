package com.fp.gp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PopulationGeneratorTest {

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
	public void testGenerate() {
		List<DoubleInputOp> inputs = new ArrayList<DoubleInputOp>(5);
		for (int i = 0; i< 5;i++) {
			inputs.add(new DoubleInputOp("Input " + i));
		}
		PopulationGenerator gen = new PopulationGenerator();
		Population pop = gen.generate(100, Double.class, 10, 0, null, inputs);
		for (DoubleFunctionOp func : pop.getFunctions()) {
			System.out.println(func.genome.prettyPrint());
			System.out.println("\n\n");
		}
		
		for (DefaultIndividual ind : pop.getIndividuals()) {
			System.out.println(ind.getGenome().prettyPrint());
			System.out.println("***\n\n");
		}
	}

}
