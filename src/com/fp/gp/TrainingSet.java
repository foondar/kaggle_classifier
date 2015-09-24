package com.fp.gp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TrainingSet {
	List<String> columns;
	List<TrainingVector> trainingData = new ArrayList<TrainingVector>();
	Random rand = new Random(System.currentTimeMillis());
	List<DoubleInputOp> inputs = new ArrayList<DoubleInputOp>();
	
	public List<DoubleInputOp> getInputs() {
		return inputs;
	}

	public TrainingSet() {
		
	}
	
	public String[] getColumns() {
		return (String[]) columns.toArray();
	}
	public void loadFromFile(File input) {
		loadFromFile(input, -1);
	}
	
	public void loadFromFile(File input, int sizeLimit) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(input));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = null;
		try {
			line = reader.readLine();//read header line
			columns = Arrays.asList(line.split(","));
			for(int i = 0; i < columns.size() - 1;i++) {
				inputs.add(new DoubleInputOp(columns.get(i)));
			}
			line = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 0;
		while (line != null && (sizeLimit <= 0 || count++ < sizeLimit)) {
			String[] tokens = line.split(",");
			List<Double> data = new ArrayList<Double>(tokens.length);
			for (String string : tokens) {
				data.add(Double.parseDouble(string));
			}
			trainingData.add(new TrainingVector(data));
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<TrainingVector> getData() {
		return this.trainingData;
	}
	
	public List<TrainingVector> getTrainingSample(int sampleSize) {
		int count = 0;
		List<TrainingVector> sample = new ArrayList<TrainingVector>(sampleSize);
		while (count++ < sampleSize) {
			sample.add(trainingData.get(rand.nextInt(trainingData.size())));
		}
		return sample;
	}
}
