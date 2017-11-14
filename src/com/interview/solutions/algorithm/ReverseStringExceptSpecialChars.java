package com.interview.solutions.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ReverseStringExceptSpecialChars {
	
	public static void main(String[] args) {
		String input = "a,Bc$d";
		
		String[] stringParts = input.split("");
		
		Stack<String> inputAlphabetsStack = new Stack<>();
		
		List<String> listRefAlphabets = Arrays.asList("abcdefghijklmnopqrstuvwxyz".split(""));
		
		for(int i=0; i<stringParts.length; i++) {
			if(listRefAlphabets.contains(stringParts[i].toLowerCase())) {
				inputAlphabetsStack.push(stringParts[i]);
				stringParts[i] = null;
			}
		}
		
		for(int i=0; i<stringParts.length; i++) {
			if(stringParts[i] == null)
				stringParts[i] = inputAlphabetsStack.pop();
		}
		
		StringBuilder builder = new StringBuilder();
		for(String part : stringParts) {
			builder.append(part);
		}
		System.out.println(builder.toString());
	}

}
