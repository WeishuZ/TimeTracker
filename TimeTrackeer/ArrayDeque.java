package TimeTrackeer;

import java.util.Arrays;
import java.util.Iterator;
public class ArrayDeque<Item> implements Iterable<Item>{
    private int maxSize = 8;
    private Item[] array;
    private int size;
    private int nextFirst;
    private int nextLast;
    public interface Iterable<Item> {
        Iterator<Item> iterator();
    }
    public Iterator<Item> iterator() {
        return new ArrayDequeIterator();
    }
    public class ArrayDequeIterator implements Iterator<Item> {
        private int wizPos;
        public ArrayDequeIterator() {
            wizPos = 0;
            array = iteratorToArray();
        }
        public boolean hasNext() {
            return wizPos < size;
        }
        public Item next() {
            Item returnItem = array[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }
    public ArrayDeque() {
        array = (Item[]) new Object[maxSize];
        size = 0;
        nextFirst = nextLast = 0;
    }


    /**
     * add last
     */
    public void addLast(Item x) {
        if (size == maxSize) {
            doubleSize();
        }
        array[nextLast] = x;
        nextLast = moveForward(nextLast);
        if (size == 0) {
            nextFirst = moveBackward(nextFirst);
        }
        size += 1;
    }
    /** add the item after the index i */
    public void addAfter(int index,Item item) {
        if(size + 1 > maxSize) {
            doubleSize();
        }
        Item[] newarray = (Item[]) new Object[maxSize];
        array = (Item[]) iteratorToArray();
        System.arraycopy(array,0,newarray,0,size);
        for(int i = 0; i < size + 1; i++) {
            if(i<=index) {
                newarray[i] = array[i];
            } else if (i == index+1) {
                newarray[index] = item;
            }else {
                newarray[i] = array[i+1];
            }
        }
        array = newarray;
    }

    public void addBefore(int index,Item item) {
        if(size + 1 > maxSize) {
            doubleSize();
        }
        Item[] newarray = (Item[]) new Object[maxSize];
        array = (Item[]) iteratorToArray();
        System.arraycopy(array,0,newarray,0,size);
        for(int i = 0; i < size + 1; i++) {
            if(i<index) {
                newarray[i] = array[i];
            } else if (i == index) {
                newarray[index] = item;
            }else {
                newarray[i] = array[i+1];
            }
        }
        array = newarray;
    }



    private void doubleSize() {
        Item[] sorted = (Item[]) iteratorToArray();
        maxSize *= 2;
        array = (Item[]) new Object[maxSize];
        System.arraycopy(sorted, 0, array, 0, size);
        nextLast = sorted.length;
        nextFirst = maxSize - 1;
    }

    private void halfSize() {
        Item[] sorted = (Item[]) iteratorToArray();
        maxSize /= 2;
        array = (Item[]) new Object[maxSize];
        System.arraycopy(sorted, 0, array, 0, size);
        nextLast = sorted.length;
        nextFirst = maxSize - 1;
    }

    public void addFirst(Item x) {
        if (size == maxSize) {
            doubleSize();
        }
        array[nextFirst] = x;
        nextFirst = moveBackward(nextFirst);
        if (size == 0) {
            nextLast = moveForward(nextLast);
        }
        size += 1;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (maxSize / size > 4 && maxSize > 8) {
            halfSize();
        }
        nextFirst = moveForward(nextFirst);
        size -= 1;
        return array[nextFirst];
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (maxSize / size > 4 && maxSize > 8) {
            halfSize();
        }
        nextLast = moveBackward(nextLast);
        size -= 1;
        return array[nextLast];
    }

    private int moveBackward(int x) {
        if (x == 0) {
            return maxSize - 1;
        } else {
            return x - 1;
        }
    }

    private int moveForward(int x) {
        if (x == maxSize - 1) {
            return 0;
        } else {
            return x + 1;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        Item[] sorted = (Item[]) iteratorToArray();
        for (Item i : sorted) {
            System.out.println(i + " ");
        }
    }

    private Item[] iteratorToArray() {
        Item[] sorted = (Item[]) new Object[size];
        int nextOne = moveForward(nextFirst);
        for (int i = 0; i < size; i++) {
            sorted[i] = array[nextOne];
            nextOne = moveForward(nextOne);
        }
        return sorted;
    }

    public Item get(int x) {
        if (isEmpty() || x > size) {
            return null;
        }
        Item[] sorted = (Item[]) iteratorToArray();
        return sorted[x - 1];
    }
}