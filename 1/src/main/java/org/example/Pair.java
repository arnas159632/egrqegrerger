package org.example;

import java.io.Closeable;
import java.time.Period;
import java.util.List;

@Author("Arnas DREVINSKAS")
@LastModified("2024-02-17")
public class Pair<K extends Period & Runnable & Closeable, V> {
    private K first;
    private V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    public void print(List<? extends Number> list) {
        System.out.println("First: " + first + ", Second: " + second);
    }
}


