package de.grundid.twiki.parser;

import java.util.HashSet;
import java.util.Set;

public class DbWriterConsumer extends Consumer<WiktionaryEntry> {

	private static Set<String> ignorePrefixes = new HashSet<String>();
	static {
		ignorePrefixes.add("MediaWiki:");
		ignorePrefixes.add("Wiktionary:");

		ignorePrefixes.add("Datei:");
		ignorePrefixes.add("Hilfe:");
		ignorePrefixes.add("Vorlage:");
		ignorePrefixes.add("Kategorie:");
		ignorePrefixes.add("Thesaurus:");
		ignorePrefixes.add("Verzeichnis:");

		ignorePrefixes.add("Appendix:");
		ignorePrefixes.add("Category:");
		ignorePrefixes.add("Help:");
		ignorePrefixes.add("Template:");
		ignorePrefixes.add("Rhymes:");
		ignorePrefixes.add("Rhmyes:");
		ignorePrefixes.add("Sign gloss:");
		ignorePrefixes.add("Summary:");
		ignorePrefixes.add("Thread:");
		ignorePrefixes.add("Transwiki:");
		ignorePrefixes.add("Unsupported titles/");
		ignorePrefixes.add("Wikisaurus:");
		ignorePrefixes.add("Citations:");
		ignorePrefixes.add("Concordance:");
		ignorePrefixes.add("File:");
		ignorePrefixes.add("Glossary:");
		ignorePrefixes.add("Index:");
		ignorePrefixes.add("Wiktionary talk:");
	}

	@Override
	protected void consume(WiktionaryEntry element) {
		// TODO Auto-generated method stub

	}

}
