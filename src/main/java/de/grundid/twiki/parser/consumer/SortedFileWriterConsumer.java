package de.grundid.twiki.parser.consumer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import de.grundid.twiki.parser.WiktionaryData;
import de.grundid.twiki.parser.WiktionaryEntry;

public class SortedFileWriterConsumer extends Consumer<WiktionaryEntry> {

	private Set<String> words = new TreeSet<String>();
	private String outputFile;

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	@Override
	protected void consume(WiktionaryEntry element) {
		for (String prefix : WiktionaryData.ignorePrefixes) {
			if (prefix.startsWith(element.getTitle()))
				return;
		}
		words.add(element.getTitle());
	}

	@Override
	protected void finishConsuming() {
		writeWords();
	}

	private void writeWords() {
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(outputFile));
			for (String word : words) {
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
