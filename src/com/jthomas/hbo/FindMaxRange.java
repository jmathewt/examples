package com.jthomas.hbo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FindMaxRange {
	
	public static void main(String[] args) {
		
		String input = "13245613451";
		
		String[] inputNumbers = input.split("");
		
		int startIndex = 0;
		int endIndex = 0;
		
		int refVal = 0;
		
		List<Map<String, Integer>> listStartEndIndexes =  new ArrayList<>(); 
		
		for(int i=0; i<inputNumbers.length; i++) {
			if(i == 0) {
				refVal = Integer.parseInt(inputNumbers[i]);
			}else if(Integer.parseInt(inputNumbers[i]) > refVal){
				refVal = Integer.parseInt(inputNumbers[i]);
				endIndex = i;
				if(i == inputNumbers.length - 1) {
					Map<String, Integer> startAndEndVals = new HashMap<>();
					startAndEndVals.put("start", startIndex);
					startAndEndVals.put("end", endIndex);
					listStartEndIndexes.add(startAndEndVals);
				}
			}else {
				Map<String, Integer> startAndEndVals = new HashMap<>();
				startAndEndVals.put("start", startIndex);
				startAndEndVals.put("end", endIndex);
				listStartEndIndexes.add(startAndEndVals);
				startIndex += 1;
				i = startIndex;
				endIndex = startIndex;
				refVal = Integer.parseInt(inputNumbers[i]);
			}
		}
		
		System.out.println(listStartEndIndexes);
		
		Map<Integer, List<int[]>> differenceValAndIndexes = new TreeMap<>();
		
		for(Map<String, Integer> startAndEndIndex : listStartEndIndexes) {
			int difference = startAndEndIndex.get("end") - startAndEndIndex.get("start"); 
			if(differenceValAndIndexes.get(difference) == null) {
				List<int[]> indexes = new ArrayList<>();
				indexes.add(new int[] {startAndEndIndex.get("start"), startAndEndIndex.get("end")});
				differenceValAndIndexes.put(difference, indexes);
			}else {
				List<int[]> indexes = differenceValAndIndexes.get(difference);
				indexes.add(new int[] {startAndEndIndex.get("start"), startAndEndIndex.get("end")});
				differenceValAndIndexes.put(difference, indexes);
			}
		}
		List<int[]> longestSeqIndexes = differenceValAndIndexes.get(differenceValAndIndexes.size() - 1);
		for(int[] indexes : longestSeqIndexes) {
			System.out.println("Longest Sequence Index: (" + indexes[0] + " - " + indexes[1] + ")");
		}
	}

}
