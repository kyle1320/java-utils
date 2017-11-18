package me.kyle1320.parallel;

public class ConcurrentStream<T> {
    private LinkedList<T> queue;
    private boolean finished;

    public final class StreamClosedException extends Exception {}

    public ConcurrentStream() {
        this.queue = new LinkedList<>();
        this.finished = false;
    }

    public synchronized void finish() {
        this.finished = true;
    }

    public synchronized void write(T el) {
        queue.addLast(el);
        this.notifyAll();
    }

    public synchronized T read() throws StreamClosedException {
        try {
            while (true) {
                if (!this.queue.isEmpty()) return this.queue.removeFirst();
                if (this.finished) throw new StreamClosedException();

                this.wait();
            }
        } catch (InterruptedException e) {
            return null;
        }
    }

    public synchronized boolean isClosed() {
        return this.finished && this.queue.isEmpty();
    }
}