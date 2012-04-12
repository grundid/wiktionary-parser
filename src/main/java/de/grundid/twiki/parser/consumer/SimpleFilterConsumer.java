package de.grundid.twiki.parser.consumer;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import de.grundid.twiki.parser.WiktionaryData;
import de.grundid.twiki.parser.WiktionaryEntry;

public class SimpleFilterConsumer extends Consumer<WiktionaryEntry> {

	private Map<String, Map<String, Integer>> matches = new TreeMap<String, Map<String, Integer>>();
	private int total;

	public void addPattern(String pattern) {
		matches.put(pattern, new TreeMap<String, Integer>());
	}

	@Override
	protected void consume(WiktionaryEntry element) {
		if (!WiktionaryData.isPrefixed(element.getTitle())) {
			addIfMatches(element.getText());
		}
	}

	private void addIfMatches(String text) {
		for (Entry<String, Map<String, Integer>> entry : matches.entrySet()) {
			String pattern = entry.getKey();
			Map<String, Integer> subMatches = entry.getValue();
			int pos = text.indexOf(pattern);
			if (pos >= 0) {
				int endOfLine = text.indexOf("\n", pos);
				String match = text.substring(pos, endOfLine).replaceAll(" |'|,|=|/", "");
				incMatch(match, subMatches);

				//				String[] split = match.split("\\||}");
				//				incMatch(split[1], subMatches);
			}
		}
	}

	private void incMatch(String match, Map<String, Integer> subMatches) {
		Integer c = subMatches.get(match);
		if (c == null)
			c = Integer.valueOf(1);
		else
			c += 1;
		subMatches.put(match, c);
		total++;
	}

	public void outputMatches() {

		for (Entry<String, Map<String, Integer>> entry : matches.entrySet()) {
			for (Entry<String, Integer> subEntry : entry.getValue().entrySet()) {
				System.out.println(entry.getKey() + "\t" + subEntry.getKey() + "\t" + subEntry.getValue());
			}
		}

		System.out.println("Total: " + total);

	}
}
