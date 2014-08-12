package org.pb.appl;

import org.pb.select_table.TableSearcher;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello ");

		TableSearcher tableSearcher = new TableSearcher();
		tableSearcher.searchAndSeat();

		System.out.println("Bue ");
	}
}
