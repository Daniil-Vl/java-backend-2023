package edu.hw3.task8;

import java.util.Iterator;
import java.util.SequencedCollection;

public class BackwardIterator<T> implements Iterator<T> {
    Iterator<T> iterator;

    public BackwardIterator(SequencedCollection<T> sequencedCollection) {
        this.iterator = sequencedCollection.reversed().iterator();
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public T next() {
        return this.iterator.next();
    }
}
