package jp.co.worksap.global.immutablequeue;

import org.apache.log4j.Logger;

import java.util.NoSuchElementException;

/**
 * Created by never on 2014/9/24.
 */
public class ImmutableQueue<E> {
    private static final Logger LOG = Logger.getLogger(ImmutableQueue.class);

    private ImmutableStack<E> forwards;
    private ImmutableStack<E> backwards;

    /**
     * constructor
     */
    public ImmutableQueue() {
        this.forwards = new ImmutableStack<E>();
        this.backwards = new ImmutableStack<E>();
    }

    public ImmutableQueue(ImmutableStack<E> forwards, ImmutableStack<E> backwards) {
        this.forwards = forwards;
        this.backwards = backwards;
    }


    /**
     * Returns the queue that adds an item into the tail of this queue without modifying this queue.
     * If the element e is null, throws IllegalArgumentException.
     * @param e
     * @return
     * @throws java.lang.IllegalArgumentException
     */
    public ImmutableQueue<E> enqueue(E e){
        LOG.debug("enqueue Element: " + e.toString() + ", without modifying this queue!");

        if (e == null) {
            throw new IllegalArgumentException();
        }

        return new ImmutableQueue<E>(forwards, backwards.push(e));
    }

    /**
     * Returns the queue that removes the object at the head of this queue without modifying this queue.
     * If the element e is null, throws java.util.NoSuchElementException..
     * @return
     * throws java.util.NoSuchElementException.
     */
    public ImmutableQueue<E> dequeue() {
        LOG.debug("dequeue without modifying this queue!");

        if (forwards.isEmpty() && backwards.isEmpty()) {
            throw new NoSuchElementException();
        }

        ImmutableStack<E> stack = null;
        if (!forwards.isEmpty()) {
            forwards.pop();
        }

        if (stack != null && !stack.isEmpty()) {
            return new ImmutableQueue<E>(stack, backwards);
        } else if (backwards.isEmpty()) {
            return new ImmutableQueue();
        } else {
            return new ImmutableQueue<E>(backwards.reverse(), new ImmutableStack<E>());
        }
    }

    /**
     * Looks at the object which is the head of this queue without removing it from the queue.
     * If the element e is null, throws java.util.NoSuchElementException.
     * @return
     * throws java.util.NoSuchElementException.
     */
    public E peek() {
        LOG.debug("Looks at the object which is the head of this queue without removing it from the queue");

        if (forwards.isEmpty() && backwards.isEmpty()) {
            throw new NoSuchElementException();
        }

        if (forwards.isEmpty()) {
            return backwards.reverse().peek();
        }
        return forwards.peek();
    }

    /**
     * Returns the number of objects in this queue.
     * @return
     */
    public int size() {
        LOG.debug("get the size of the queue");

        return forwards.size() + backwards.size();
    }
}
