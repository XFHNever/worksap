package jp.co.worksap.global.immutablequeue;


import java.util.NoSuchElementException;

/**
 * Created by never on 2014/9/24.
 */
public class ImmutableStack<E> {
    private E head;
    private ImmutableStack<E> tail;
    private int size;

    public ImmutableStack() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public ImmutableStack(E head, ImmutableStack<E> tail) {
        this.head = head;
        this.tail = tail;
        if (tail != null) {
            this.size = (head == null ? (tail.size()) : (1 + tail.size()));
        } else {
            this.size = (head == null ? 0 : 1);
        }
    }

    public E peek() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return head;
    }

    public ImmutableStack<E> pop() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail;
    }

    public boolean isEmpty() {
        return (head == null && (tail == null || tail.isEmpty()));
    }

    public ImmutableStack<E> push(E head) {
        if (head == null) {
            throw new IllegalArgumentException();
        }
        return new ImmutableStack<E>(head, this);
    }

    public ImmutableStack<E> reverse() {
        ImmutableStack<E> reverseStack = new ImmutableStack<E>();

        for (ImmutableStack<E> stack = this; !stack.isEmpty(); stack = stack.pop()) {
            reverseStack = reverseStack.push(stack.peek());
        }

        return reverseStack;
    }

    public int size() {
        return this.size;
    }
}
