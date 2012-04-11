package de.grundid.twiki.parser;

import java.util.Deque;
import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class WiktionaryImporter extends OsmDefaultHandler<WiktionaryEntry> {

	private WiktionaryEntry entry;

	private Deque<String> tagQueue = new LinkedList<String>();

	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		tagQueue.add(name);
		if (name.equals("page"))
			entry = new WiktionaryEntry();
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String currentTag = tagQueue.peekLast();
		String content = new String(ch, start, length).trim();
		if (currentTag.equals("title")) {
			entry.setTitle(content);
		}
		else if (currentTag.equals("text")) {
			entry.setText(content);
		}
	}

	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {
		tagQueue.removeLast();

		if (entry == null)
			return;

		if (name.equals("page"))
			addToQueue(entry);
	}
}
