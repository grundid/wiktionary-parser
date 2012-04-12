package de.grundid.twiki.parser;

import java.io.InputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import de.grundid.twiki.parser.consumer.Consumer;

public class ImportHandler<T> implements Runnable {

	private int defaultQueueSize = 20000;
	private InputStream inputStream;
	private Consumer<T> consumer;
	private OsmDefaultHandler<T> producer;

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setConsumer(Consumer<T> consumer) {
		this.consumer = consumer;
	}

	public void setProducer(OsmDefaultHandler<T> producer) {
		this.producer = producer;
	}

	public void setDefaultQueueSize(int defaultQueueSize) {
		this.defaultQueueSize = defaultQueueSize;
	}

	@Override
	public void run() {

		BlockingQueue<T> queue = new LinkedBlockingDeque<T>(defaultQueueSize);
		consumer.setQueue(queue);
		producer.setQueue(queue);

		Thread thread = new Thread(consumer);
		thread.start();

		parse(inputStream, producer);

		consumer.producerFinished();

		try {
			synchronized (thread) {
				while (thread.isAlive()) {
					thread.wait(1);
				}
			}
		}
		catch (InterruptedException e) {
		}
	}

	protected void parse(InputStream is, DefaultHandler producer) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.setProperty("http://xml.org/sax/properties/lexical-handler", producer);
			parser.parse(is, producer);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
