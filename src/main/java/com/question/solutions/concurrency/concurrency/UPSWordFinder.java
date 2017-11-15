package com.question.solutions.concurrency.concurrency;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UPSWordFinder {
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		File file = new File(System.getenv("upsAndNonUPSWordsFilePath"));
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String line = "";
		
		File upsFile = new File(System.getenv("UPSWordsFilePath"));
		File nonUPSFile = new File(System.getenv("nonUPSWordsFilePath"));
		
		FileWriterUPSNonUPS fileWriterUPSNonUPS = new FileWriterUPSNonUPS();
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		List<Future<Boolean>> futures = new ArrayList<>();
		
		int count = 0;
		
		while((line = reader.readLine()) != null) { //execute batches of 10 threads sequentially after each batch is successful
			try {
				if(futures.size() == 10) {
					for(Future future : futures) {
						if(!future.isDone())
							future.get();
					}
					futures.clear();
				}else {
					Future<Boolean> future = executor.submit(new FileWriterInfo(line, upsFile, nonUPSFile, fileWriterUPSNonUPS));
					futures.add(future);
				}
			}finally {
				if(futures.size() > 0)
					count += 1;
				futures.clear();
			}
			
		}
		
		System.out.println("Thread Service Called: " + count);
		executor.shutdown();
		reader.close();
	}

}



class FileWriterInfo implements Callable<Boolean>{
	public String line;
	public File upsFile;
	public File nonUPSFile;
	FileWriterUPSNonUPS fileWriter;
	
	public FileWriterInfo(String line, File upsFile, File nonUPSFile, FileWriterUPSNonUPS fileWriter) {
		this.line = line;
		this.upsFile = upsFile;
		this.nonUPSFile = nonUPSFile;
		this.fileWriter = fileWriter;
	}

	@Override
	public Boolean call() throws Exception {
		// opportunity to do extra processing if needed before writing to file
		if(line.contains("UPS")) {
			fileWriter.writeToFiles(true, line, upsFile, nonUPSFile);
		}else 
			fileWriter.writeToFiles(false, line, upsFile, nonUPSFile);
		
		return true;
	}
}

class FileWriterUPSNonUPS {
	
	public synchronized void writeToFiles(boolean lineHasUPS, String line,  File upsFile, File nonUPSFile) throws IOException {
		FileWriter fileWriter = null;
		if(lineHasUPS)
			fileWriter = new FileWriter(upsFile, true);
		else
			fileWriter = new FileWriter(nonUPSFile, true);
		
		BufferedWriter writer = new BufferedWriter(fileWriter);
		writer.write(line);
		writer.newLine();
		writer.close();
	}
	
}
