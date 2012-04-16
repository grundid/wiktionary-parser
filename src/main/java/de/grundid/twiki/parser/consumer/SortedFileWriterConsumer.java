package de.grundid.twiki.parser.consumer;

import java.util.Set;
import java.util.TreeSet;

import de.grundid.twiki.parser.FileWriterUtils;
import de.grundid.twiki.parser.WiktionaryData;
import de.grundid.twiki.parser.WiktionaryEntry;

public class SortedFileWriterConsumer extends Consumer<WiktionaryEntry> {

	private Set<String> words = new TreeSet<String>();
	private Set<String> prefixes = new TreeSet<String>();
	private String outputFile;

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	@Override
	protected void consume(WiktionaryEntry element) {
		for (String prefix : WiktionaryData.ignorePrefixes) {
			if (element.getTitle().startsWith(prefix)) {
				return;
			}
		}
		for (String prefix : WiktionaryData.structurePrefixes) {
			if (element.getTitle().startsWith(prefix)) {
				prefixes.add(element.getTitle());
				return;
			}
		}
		words.add(element.getTitle());
	}

	@Override
	protected void finishConsuming() {
		FileWriterUtils.writeWords(outputFile + ".txt", words);
		FileWriterUtils.writeWords(outputFile + "-prefixes.txt", prefixes);
	}

}
