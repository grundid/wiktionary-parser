package de.grundid.twiki.parser;

import org.junit.Test;

import de.grundid.twiki.parser.consumer.SimpleFilterConsumer;

public class SimpleImportTest {

	private static final String[] files = { "c:\\tmp\\dewiktionary-20120225-pages-articles.xml",
			"c:\\tmp\\enwiktionary-20120406-pages-articles.xml" };

	@Test
	public void testImport() throws Exception {

		long time = System.currentTimeMillis();
		SimpleImporter importer = new SimpleImporter();
		ImportHandler<WiktionaryEntry> handler = new ImportHandler<WiktionaryEntry>();

		WiktionaryImporter de = processFile(importer, handler, files[0], "de");
		//		WiktionaryImporter en = processFile(importer, handler, files[1]);
		//		System.out.println("DE before: " + de.getWordsCount());
		//		de.getWords().removeAll(en.getWords());
		//		System.out.println("DE remained: " + de.getWordsCount());

		System.out.println("Time: " + (System.currentTimeMillis() - time) + " ms");
	}

	private WiktionaryImporter processFile(SimpleImporter importer, ImportHandler<WiktionaryEntry> handler,
			String file, String source) {
		WiktionaryImporter wiktionaryImporter = new WiktionaryImporter();

		SimpleFilterConsumer consumer = new SimpleFilterConsumer();
		for (String part : WiktionaryData.partsOfSpeech)
			consumer.addPattern("{{Wortart|" + part + "|Deutsch");

		handler.setProducer(wiktionaryImporter);
		handler.setConsumer(consumer);
		importer.run(file, handler);

		consumer.outputMatches();
		return wiktionaryImporter;
	}
}
