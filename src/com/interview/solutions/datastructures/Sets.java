package com.jthomas.datastructures;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class Sets {
	
	public static void main(String[] args) {
		Set<Person> people = new TreeSet<>();
		
		for(int i=0; i<10000; i++) {
			Person person = new Person();
			int age = getRandomAge(10, 70);
			person.age = age;
			people.add(person);
		}
		
		for(Person person : people) {
			System.out.println(person.age);
		}
	}
	
	private static int getRandomAge(int min, int max) {
		Random random = new Random();
		int randomAge = random.nextInt((max - min) + 1) + min;
		return randomAge;
	}

}

class Person implements Comparable<Person>{
	int age;
	String name = UUID.randomUUID().toString();
	
	@Override
	public int compareTo(Person person) {
		return this.age - person.age;
	}
}
