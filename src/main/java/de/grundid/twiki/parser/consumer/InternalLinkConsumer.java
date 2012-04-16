package de.grundid.twiki.parser.consumer;

import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.grundid.twiki.parser.FileWriterUtils;
import de.grundid.twiki.parser.WiktionaryData;
import de.grundid.twiki.parser.WiktionaryEntry;

public class InternalLinkConsumer extends Consumer<WiktionaryEntry> {

	private Set<String> matches = new TreeSet<String>();
	private Pattern linkPattern = Pattern.compile("\\[\\[(.*?)\\]\\]");

	private String outputFile;

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	@Override
	protected void consume(WiktionaryEntry element) {
		if (!WiktionaryData.isPrefixed(element.getTitle())) {
			addIfMatches(element.getText());
		}
	}

	private void addIfMatches(String text) {
		Matcher matcher = linkPattern.matcher(text);
		while (matcher.find()) {
			String match = matcher.group(1).trim();
			if (!WiktionaryData.isPrefixed(match)) {
				if (match.indexOf(':') == -1) {
					matches.add(match);
				}
			}
		}

	}

	@Override
	protected void finishConsuming() {
		FileWriterUtils.writeWords(outputFile + "-all-links.txt", matches);
	}

}
