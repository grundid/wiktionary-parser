package de.grundid.twiki.parser.consumer;

import java.util.concurrent.BlockingQueue;

public abstract class Consumer<T> implements Runnable {

	private BlockingQueue<T> queue;
	private boolean finishWhenDone;

	protected abstract void consume(T element);

	/**
	 * This method is called at the end of the consume loop. Override this if you need to flush any batches.
	 */
	protected void finishConsuming() {
	}

	public void setQueue(BlockingQueue<T> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				if (queue.isEmpty() && finishWhenDone)
					break;
				else
					Thread.sleep(10);

				while (!queue.isEmpty()) {
					consume(queue.take());
				}
			}
		}
		catch (InterruptedException ex) {
		}
		finishConsuming();
		synchronized (this) {
			notifyAll();
		}
	}

	public void producerFinished() {
		this.finishWhenDone = true;
	}
}
