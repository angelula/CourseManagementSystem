package com.gatech.cs6310.coursemgmt.util.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public abstract class GenericCSVParser<E> {
	
	private File file;
	
	private BufferedReader reader;
	
	protected static String cvsSplitBy = ",";
	
	public synchronized final E parse(File f) throws IOException, IllegalArgumentException {
		
		String line="";
		
		if (f==null || !f.exists()) {
			throw new IllegalArgumentException("Invalid file: "+ f);
		}
		setFile(f);
		openReader();
		E parsed = parse();
		closeReader();
		setFile(null);
		return parsed;
	}
	
	protected abstract E parse() throws IOException;
	
	protected void openReader() throws IOException {
//		BufferedReader reader = new BufferedReader(new FileReader(getFile()));
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(getFile())));
		setReader(reader);
	}
	
	protected void closeReader() throws IOException {
		if (getReader() != null) {
			getReader().close();
			setReader(null);
		}
	}
	
	protected String readLine() throws IOException {
		String line = getReader().readLine();
		return line;
	}
	
	protected File getFile() {
		return file;
	}
	
	protected void setFile(File file) {
		this.file=file;
	}
	
	protected BufferedReader getReader() {
		return this.reader;
	}
	
	private void setReader(BufferedReader reader) {
		this.reader = reader;
	}
}
