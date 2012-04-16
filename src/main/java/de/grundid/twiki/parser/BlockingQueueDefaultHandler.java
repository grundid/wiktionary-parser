package de.grundid.twiki.parser;

import java.util.concurrent.BlockingQueue;

import org.xml.sax.ext.DefaultHandler2;

public class BlockingQueueDefaultHandler<T> extends DefaultHandler2 {

	protected BlockingQueue<T> queue;

	public void setQueue(BlockingQueue<T> queue) {
		this.queue = queue;
	}

	protected void addToQueue(T element) {
		try {
			if (queue != null)
				queue.put(element);
		}
		catch (InterruptedException e) {
		}

	}
}
