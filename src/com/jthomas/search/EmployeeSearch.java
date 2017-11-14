package com.jthomas.search;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class EmployeeSearch {
	
	public static void main(String[] args) {
		Set<Integer> numbersGenerated = new HashSet<>();
		
		Set<Employee> employees = new HashSet<>();
		
		for(int i=0; i<1000000; i++) {
			 addEmployeeToSet(numbersGenerated, employees, 1, 1000001);
		}
		
		System.out.println("Added all the employees");
		
		/*for(Employee employee : employees) {
			System.out.println(employee.employeeId + " - " + employee.employeePassword);
		}*/
		
		System.out.println("Total employees present - " + employees.size());
		
		
		System.out.println("------------------    Iteration  ------------------------------");
		long startTime = System.currentTimeMillis();
		System.out.println(startTime);
		int count = 1;
		for(Employee employee : employees) {
			if(employee.employeeId == 9909) {
				long stopTime = System.currentTimeMillis();
				System.out.println(stopTime);
				System.out.println(stopTime - startTime);
				System.out.println("Found in steps - " + count);
			}
			count += 1;
		}
		System.out.println("-----------------------------------------------------------");
		
		Node rootNode = new EmployeeBinaryTree().getRootNodeOfEmployeeTree(employees);
		
		System.out.println("------------------    Binary Search Tree  ------------------------------");
		long startTimeTree = System.currentTimeMillis();
		new EmployeeBinaryTree().findNode(rootNode, 9909);
		long stopTimeTree = System.currentTimeMillis();
		System.out.println(startTimeTree);
		System.out.println(stopTimeTree);
		System.out.println(stopTimeTree - startTimeTree);
		System.out.println("-----------------------------------------------------------");
	} 
	
	public static void addEmployeeToSet(Set<Integer> numbersGenerated, Set<Employee> employees, int startEmployeeId, int endEmployeeId) {
		int randomNumber = getRandomNumber(endEmployeeId, startEmployeeId);
		while(!numbersGenerated.add(randomNumber)) {
			System.out.println("Got same number. Trying again - " + randomNumber);
			randomNumber = getRandomNumber(endEmployeeId, startEmployeeId);
		}
		Employee employee = new Employee();
		employee.employeeId = randomNumber;
		employees.add(employee);
	}
	
	public static Integer getRandomNumber(int high, int low) {
		Random random = new Random();
		
		return random.nextInt(high - low) + low;
	}
	
}


class Employee{
	int employeeId;
	String employeePassword = UUID.randomUUID().toString();
}
