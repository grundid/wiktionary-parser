package de.grundid.twiki.parser.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.grundid.twiki.jpa.WikiEntry;
import de.grundid.twiki.jpa.WikiEntryRepository;
import de.grundid.twiki.parser.WiktionaryData;
import de.grundid.twiki.parser.WiktionaryEntry;

@Component
public class DbWriterConsumer extends Consumer<WiktionaryEntry> {

	@Autowired
	private WikiEntryRepository wikiEntryRepository;

	private String currentSource;

	@Override
	protected void consume(WiktionaryEntry element) {

		WikiEntry wikiEntry = new WikiEntry();
		wikiEntry.setSource(currentSource);
		wikiEntry.setTitle(element.getTitle());
		wikiEntry.setEntry(element.getText());
		wikiEntry.setCategory("_word_");

		for (String prefix : WiktionaryData.ignorePrefixes) {
			if (element.getTitle().startsWith(prefix)) {
				wikiEntry.setCategory(prefix);
				break;
			}
		}

		wikiEntryRepository.save(wikiEntry);

	}

	public void setCurrentSource(String currentSource) {
		this.currentSource = currentSource;
	}
}
