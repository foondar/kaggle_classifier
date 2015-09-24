package com.fp.gp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.List;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

public class RunClassifier {

	Population classifier;
	
	File dataFile;
	File outputFile;
	
	
	public RunClassifier(String classifier_path, String data_path, String output_path) {
		File classifierFile = new File(classifier_path);
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(classifierFile));
			classifier = (Population)ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dataFile = new File(data_path);
		outputFile = new File(output_path);
	}
	
	public void classify() {
		try {
			LineInputStream dataInput = new LineInputStream(new FileInputStream(dataFile));
			PrintStream resultOutput = new PrintStream(new FileOutputStream(outputFile));
			
			String line = null;
			line = dataInput.readLine();
			DefaultIndividual best = classifier.mostFit();
			List<DoubleInputOp> inputs = classifier.getInputs();
			while (line != null) {
				String[] tokens = line.split(";");
				int id = Integer.parseInt(tokens[0]);
//				Double[] data = new Double[tokens.length-1];
				boolean allzeroes = true;
				for (int ndx = 1; ndx < tokens.length;ndx++) {
					double field = Double.parseDouble(tokens[ndx]);
					if (field != 0) allzeroes = false;
//					data[ndx - 1] = Double.parseDouble(tokens[ndx]);
					inputs.get(ndx - 1).setValue(field);
				}
//				System.out.println(data[0]);
//				double result = (Double)classifier.evaluate(data);
				int result = allzeroes ? 0 : ((Double)best.getGenome().evaluate()).intValue();
//				int result = ((Double)best.getGenome().evaluate()).intValue();
				System.out.println(id + ", " + result);
				resultOutput.println(id+","+Double.toString(result));
				line = dataInput.readLine();
			}
			resultOutput.flush();
			resultOutput.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RunClassifier runner = new RunClassifier("best", "C:/dev/kaggle/wikipedia/evaluation.csv", 
				"C:/dev/kaggle/wikipedia/results.csv");
		runner.classify();

	}

}
