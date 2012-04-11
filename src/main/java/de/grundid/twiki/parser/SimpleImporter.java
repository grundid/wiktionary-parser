package de.grundid.twiki.parser;

import java.io.FileInputStream;

public class SimpleImporter {

	public void run(String importFile, ImportHandler<?> importHandler) {
		try {
			System.out.println("Importing " + importFile);
			importHandler.setInputStream(new FileInputStream(importFile));
			importHandler.run();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
