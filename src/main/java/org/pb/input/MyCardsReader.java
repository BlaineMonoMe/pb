package org.pb.input;

import org.pb.input_output_util.IOUtil;

public class MyCardsReader {

	public MyCardsReader() {
	}

	
	public void printCardsOnTable() {
		while (true) {
			IOUtil.wait(1000);
			if (IOUtil.existPicture("res\\images\\cards\\4s.PNG")) {
				System.out.println("-------> GOT! 4s");
			}
			if (IOUtil.existPicture("res\\images\\cards\\6c.PNG")) {
				System.out.println("-------> GOT! 6c");
			}
			if (IOUtil.existPicture("res\\images\\cards\\6s.PNG")) {
				System.out.println("-------> GOT! 6s");
			}
			if (IOUtil.existPicture("res\\images\\cards\\3s.PNG")) {
				System.out.println("-------> GOT! 3s");
			}
			if (IOUtil.existPicture("res\\images\\cards\\7d.PNG")) {
				System.out.println("-------> GOT! 7d");
			}
			if (IOUtil.existPicture("res\\images\\cards\\7h.PNG")) {
				System.out.println("-------> GOT! 7h");
			}
			if (IOUtil.existPicture("res\\images\\cards\\9c.PNG")) {
				System.out.println("-------> GOT! 9c");
			}
			if (IOUtil.existPicture("res\\images\\cards\\Td.PNG")) {
				System.out.println("-------> GOT! Td");
			}
			if (IOUtil.existPicture("res\\images\\cards\\Th.PNG")) {
				System.out.println("-------> GOT! Th");
			}
			if (IOUtil.existPicture("res\\images\\cards\\Js.PNG")) {
				System.out.println("-------> GOT! Js");
			}
			if (IOUtil.existPicture("res\\images\\cards\\Qh.PNG")) {
				System.out.println("-------> GOT! Qh");
			}
			if (IOUtil.existPicture("res\\images\\cards\\Kc.PNG")) {
				System.out.println("-------> GOT! Kc");
			}
		}
	}

}
