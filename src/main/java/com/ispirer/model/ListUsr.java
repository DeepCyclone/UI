package com.ispirer.model;

import java.io.Serializable;
import java.util.*;

@SuppressWarnings("unchecked")
public class ListUsr<T> implements Iterable<T> {
    private T[] array;
    public static final int DEFAULT_CAPACITY = 5;
    public ListUsr() {
        this(DEFAULT_CAPACITY);
    }
    public ListUsr(int capacity) {
        array = (T[])new Object[capacity];
    }
    public ListUsr(T[] array) {
        this.array = Arrays.copyOf(array,array.length);
    }
    public ListUsr(List<T> array) {
        this.array = (T[]) array.toArray();
    }
    public T getElement(int index) {
        appropriateIndexCheck(index);
        return array[index];
    }
    public void setElement(int index,T element) {
        appropriateIndexCheck(index);
        array[index] = element;
    }
    public T removeElement(int index) {
        appropriateIndexCheck(index);
        T deletedElement = array[index];
        if (array.length - 1 - index >= 0) {
            System.arraycopy(array, index + 1, array, index, array.length - 1 - index);
        }
        array[array.length-1] = null;
        return deletedElement;
    }
    public void ensureCapacity(int capacity){
        if(capacity > array.length) {
            array = Arrays.copyOf(array,capacity);
        }
    }

    public void add(T element){
        array = Arrays.copyOf(array,array.length + 1);
        array[array.length-1] = element;
    }

    public void addAll(Collection<T> elements){
        if(!elements.isEmpty()) {
            final int len = array.length;
            array = Arrays.copyOf(array, array.length + elements.size());
            for (int i = len; i < array.length; ++i) {
                array[i] = elements.iterator().next();//TODO test
            }
        }
    }
    public int getCapacity() {
        return array.length;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName() + "\n");
        sb.append("elements:" + "\n");
        for(int i = 0;i < array.length;i++) {
            sb.append("array["+ i +"] = " + array[i] + "\n");
        }
        return sb.toString();
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int cursorPosition = 0;
            @Override
            public boolean hasNext() {
                return cursorPosition < array.length;
            }
            @Override
            public T next() {
                return array[cursorPosition++];
            }
        };
    }
    private void appropriateIndexCheck(int index) {
        if(index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Out of bounds - Actual array size:" + array.length + "|" + "Contacted index:" + index);
        }
    }
}