package jp.co.worksap.global.immutablequeue;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * Created by never on 2014/9/23.
 */
public class ImmutableQueue<E> {
    private E[] queue;
    private int size;
    /**
     * constructor
     */
    public ImmutableQueue() {
        this.queue = null;
        this.size = 0;
    }

    public ImmutableQueue(E[] queue) {
        this.queue = queue;
        this.size = queue.length;
    }

    /**
     * Returns the queue that adds an item into the tail of this queue without modifying this queue.
     * If the element e is null, throws IllegalArgumentException.
     * @param e
     * @return
     * @throws java.lang.IllegalArgumentException
     */
    public ImmutableQueue<E> enqueue(E e) {
        if (e == null) {
            throw new IllegalArgumentException();
        }

        E[] cloneQueue = (E[]) Array.newInstance(e.getClass(), size + 1);
        cloneQueue[0] = e;
        System.arraycopy(this.queue, 0, cloneQueue, 1, size);

        return new ImmutableQueue<E>(cloneQueue);
    }

    /**
     * Returns the queue that removes the object at the head of this queue without modifying this queue.
     * If the element e is null, throws java.util.NoSuchElementException..
     * @return
     * throws java.util.NoSuchElementException.
     */
    public ImmutableQueue<E> dequeue() {
        if (this.size <= 0) {
            throw new NoSuchElementException();
        }

        E[] cloneQueue = (E[]) new Object[size - 1];
        System.arraycopy(this.queue, 0, cloneQueue, 0, size - 1);

        return new ImmutableQueue<E>(cloneQueue);
    }

    /**
     * Looks at the object which is the head of this queue without removing it from the queue.
     * If the element e is null, throws java.util.NoSuchElementException.
     * @return
     * throws java.util.NoSuchElementException.
     */
    public E peek() {
        if (this.size <= 0) {
            throw new NoSuchElementException();
        }

        return queue[size - 1];
    }

    /**
     * Returns the number of objects in this queue.
     * @return
     */
    public int size() {
        return this.size;
    }
}
