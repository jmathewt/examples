package com.interview.solutions.concurrency;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class UPSFileGenerator {

	public static void main(String[] args) throws IOException {
		File upsWordsFile = new File(System.getenv("upsNonUPSWordsFilePath"));
		boolean fileExists = (!(upsWordsFile.exists())) ? upsWordsFile.createNewFile() : false;
		FileWriter fileWriter = new FileWriter(upsWordsFile);
		BufferedWriter buffWriter = new BufferedWriter(fileWriter);

		Set<Integer> linesWithUPS = new HashSet<>();

		for (int i = 0; i < 5000; i++) {
			int randomNum = new Random().nextInt(5001);
			while (!linesWithUPS.add(randomNum)) {
				randomNum = new Random().nextInt(5001);
			}
		}

		for (int i = 0; i < 10000; i++) {
			StringBuilder buildLine = new StringBuilder();
			if (linesWithUPS.contains(i)) {
				buildLine.append(UUID.randomUUID() + " " + UUID.randomUUID() + UUID.randomUUID() + " "
						+ UUID.randomUUID() + " UPS");
			}else {
				buildLine.append(UUID.randomUUID() + " " + UUID.randomUUID() + UUID.randomUUID() + " "
						+ UUID.randomUUID() + " NYPL");
			}
			buffWriter.write(buildLine.toString());
			buffWriter.newLine();
		}
		
		buffWriter.close();
	}

}
