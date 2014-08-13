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
		}
	}

}
