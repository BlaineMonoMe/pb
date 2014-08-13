package org.pb.appl;

import org.pb.input.MyCardsReader;
import org.pb.input.TableParser;
import org.pb.select_table.TableSearcher;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello ");

		TableSearcher tableSearcher = new TableSearcher(0);
		tableSearcher.searchAndSeat();

		TableParser tp = new TableParser();
		tp.start();

		MyCardsReader mcr = new MyCardsReader();
		mcr.printCardsOnTable();

		System.out.println("Bue ");
	}
}
