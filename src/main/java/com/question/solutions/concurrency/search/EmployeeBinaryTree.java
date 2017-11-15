package com.question.solutions.concurrency.search;

import java.util.Collection;

public class EmployeeBinaryTree {
	
	public Node getRootNodeOfEmployeeTree(Collection<Employee> employees) {
		Node rootNode = null;
		for(Employee employee : employees) {
			if(rootNode == null) {
				rootNode = new Node();
				rootNode.employee = employee;
			}else {
				addNode(rootNode, employee);
			}
			
		}
		return rootNode;
	}
	
	private void addNode(Node parentNode, Employee employee) {
		if(parentNode == null ) { 
			throw new IllegalStateException("Parent node cannot be null");
		}else if (employee.employeeId <= parentNode.employee.employeeId ) {
			if(parentNode.leftNode == null) {
				Node leftNode = new Node();
				leftNode.employee = employee;
				parentNode.leftNode = leftNode;
			}else {
				addNode(parentNode.leftNode, employee);
			}
		}else {
			if(parentNode.rightNode == null) {
				Node rightNode = new Node();
				rightNode.employee = employee;
				parentNode.rightNode = rightNode;
			}else {
				addNode(parentNode.rightNode, employee);
			}
		}
	}
	
	public void findNode(Node parentNode, int employeeId) {
		if(parentNode == null)
			throw new IllegalStateException("At least one node needed for search");
		else {
			System.out.println(parentNode.employee.employeeId);
			if(employeeId <= parentNode.employee.employeeId) {
				if(parentNode.leftNode == null)
					System.out.println("Employee id not present");
				else {
					if(parentNode.leftNode.employee.employeeId == employeeId)
						System.out.println(parentNode.leftNode.employee.employeeId);
					else{
						findNode(parentNode.leftNode, employeeId);
					}
				}
			}else {
				if(parentNode.rightNode == null)
					System.out.println("Employee id is not present");
				else {
					if(parentNode.rightNode.employee.employeeId == employeeId)
						System.out.println(parentNode.rightNode.employee.employeeId);
					else {
						findNode(parentNode.rightNode, employeeId);
					}
				}
			}
		}
	}
	
}

class Node{
	Employee employee;
	Node rightNode;
	Node leftNode;
}
