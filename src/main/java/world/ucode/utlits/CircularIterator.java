package world.ucode.utlits;

import java.util.Iterator;
import java.util.List;

public class CircularIterator<E> implements Iterator<E>{
    private List<E> list;
    private int indexSelected = 0;
    public CircularIterator(List<E> list){
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return indexSelected < list.size() - 1;
    }

    @Override
    public E next() {
        indexSelected++;
        if (indexSelected > 2) indexSelected = 0;
        return list.get(indexSelected % list.size());
    }

    public E previous() {
        indexSelected--;
        if (indexSelected < 0) indexSelected *= -1 + indexSelected;
        return list.get(indexSelected % list.size());
    }

    public E getElement(int i) {
        return list.get(i);
    }

    public int getIndexSelected() {
        return indexSelected;
    }
}
