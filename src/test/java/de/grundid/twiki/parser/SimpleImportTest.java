package de.grundid.twiki.parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import org.junit.Test;

public class SimpleImportTest {

	private static final String[] files = { "c:\\tmp\\dewiktionary-20120225-pages-articles.xml",
			"c:\\tmp\\enwiktionary-20120406-pages-articles.xml" };

	private void writeWords(String file, Collection<String> words) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(file + ".txt"));
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

	@Test
	public void testImport() throws Exception {

		long time = System.currentTimeMillis();
		SimpleImporter importer = new SimpleImporter();
		ImportHandler<WiktionaryEntry> handler = new ImportHandler<WiktionaryEntry>();

		WiktionaryImporter de = processFile(importer, handler, files[0]);
		//		WiktionaryImporter en = processFile(importer, handler, files[1]);
		//		System.out.println("DE before: " + de.getWordsCount());
		//		de.getWords().removeAll(en.getWords());
		//		System.out.println("DE remained: " + de.getWordsCount());

		System.out.println("Time: " + (System.currentTimeMillis() - time) + " ms");
	}

	private WiktionaryImporter processFile(SimpleImporter importer, ImportHandler<WiktionaryEntry> handler, String file) {
		WiktionaryImporter wiktionaryImporter = new WiktionaryImporter();

		handler.setProducer(wiktionaryImporter);
		handler.setConsumer(new Consumer<WiktionaryEntry>() {

			@Override
			protected void consume(WiktionaryEntry element) {
				//				System.out.println("[" + element.getTitle() + "]");
			}
		});

		importer.run(file, handler);

		return wiktionaryImporter;
	}
}
