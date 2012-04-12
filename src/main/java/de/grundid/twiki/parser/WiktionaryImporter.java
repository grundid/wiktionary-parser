package de.grundid.twiki.parser;

import java.util.Deque;
import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class WiktionaryImporter extends OsmDefaultHandler<WiktionaryEntry> {

	private WiktionaryEntry entry;

	private Deque<String> tagQueue = new LinkedList<String>();
	private StringBuilder content = new StringBuilder();

	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		tagQueue.add(name);
		content.setLength(0);
		if (name.equals("page"))
			entry = new WiktionaryEntry();
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		content.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {
		String currentTag = tagQueue.removeLast();
		if (currentTag.equals("title")) {
			entry.setTitle(content.toString());
		}
		else if (currentTag.equals("text")) {
			entry.setText(content.toString());
		}

		if (entry == null)
			return;

		if (name.equals("page"))
			addToQueue(entry);
	}
}
