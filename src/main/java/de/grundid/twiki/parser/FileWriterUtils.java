package de.grundid.twiki.parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class FileWriterUtils {

	public static void writeWords(String fileName, Collection<String> data) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(fileName));
			for (String word : data) {
				fw.write(word);
				fw.write('\n');
			}
		}
		catch (IOException e) {
		}
		finally {
			if (fw != null) {
				try {
					fw.flush();
					fw.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
