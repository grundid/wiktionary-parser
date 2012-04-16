package de.grundid.twiki.parser;

import org.junit.Test;

import de.grundid.twiki.parser.consumer.Consumer;
import de.grundid.twiki.parser.consumer.InternalLinkConsumer;
import de.grundid.twiki.parser.consumer.SimpleFilterConsumer;
import de.grundid.twiki.parser.consumer.SortedFileWriterConsumer;

public class SimpleImportTest {

	private static final String[] files = { "c:\\tmp\\dewiktionary-20120225-pages-articles.xml",
			"c:\\tmp\\enwiktionary-20120406-pages-articles.xml" };

	@Test
	public void testImport() throws Exception {

		long time = System.currentTimeMillis();

		//		processFile(createSortedFileWriterConsumer(files[0]), files[0], "de");
		processFile(createInternalLinkConsumer(files[0]), files[0], "de");

		//		WiktionaryImporter en = processFile(importer, handler, files[1]);
		//		System.out.println("DE before: " + de.getWordsCount());
		//		de.getWords().removeAll(en.getWords());
		//		System.out.println("DE remained: " + de.getWordsCount());

		System.out.println("Time: " + (System.currentTimeMillis() - time) + " ms");
	}

	private SimpleFilterConsumer createSimpleFilterConsumer() {
		SimpleFilterConsumer consumer = new SimpleFilterConsumer();
		//		consumer.setReplacePattern(" |'|,|=|/");
		consumer.addPattern("= Übersetzungen =");

		//		for (String part : WiktionaryData.partsOfSpeech)
		//		{
		//			consumer.addPattern("{{Wortart|" + part + "|Deutsch");
		//		}
		return consumer;
	}

	private SortedFileWriterConsumer createSortedFileWriterConsumer(String file) {
		SortedFileWriterConsumer consumer = new SortedFileWriterConsumer();
		consumer.setOutputFile(file);
		return consumer;
	}

	private InternalLinkConsumer createInternalLinkConsumer(String file) {
		InternalLinkConsumer consumer = new InternalLinkConsumer();
		consumer.setOutputFile(file);
		return consumer;
	}

	private WiktionaryImporter processFile(Consumer<WiktionaryEntry> consumer, String file, String source) {
		SimpleImporter importer = new SimpleImporter();
		ImportHandler<WiktionaryEntry> handler = new ImportHandler<WiktionaryEntry>();
		WiktionaryImporter wiktionaryImporter = new WiktionaryImporter();

		handler.setProducer(wiktionaryImporter);
		handler.setConsumer(consumer);
		importer.run(file, handler);

		return wiktionaryImporter;
	}
}
