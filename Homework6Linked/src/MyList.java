import java.util.Collection;

public interface MyList<E> extends java.util.Collection<E> {
    /** Add a new element at the specified index in this list */
    public void add(int index, E e);

    /** Return the element from this list at the specified index */
    public E get(int index);

    /** Return the index of the first matching element in this list.
     *  Return -1 if no match. */
    public int indexOf(Object e);

    /** Return the index of the last matching element in this list
     *  Return -1 if no match. */
    public int lastIndexOf(E e);

    /** Remove the element at the specified position in this list
     *  Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    public E remove(int index);

    /** Replace the element at the specified position in this list
     *  with the specified element and returns the new set. */
    public E set(int index, E e);

    @Override /** Add a new element at the end of this list */
    public default boolean add(E e) {
        add(size(), e);
        return true;
    }

    @Override /** Return true if this list contains no elements */
    public default boolean isEmpty() {
        return size() == 0;
    }

    @Override /** Remove the first occurrence of the element e
     *  from this list. Shift any subsequent elements to the left.
     *  Return true if the element is removed. */
    public default boolean remove(Object e) {
        if (indexOf(e) >= 0) {
            remove(indexOf(e));
            return true;
        }
        else
            return false;
    }

    @Override
    public default boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {
        boolean added = false;
        for (E e : c) {
            if (add(e)) {
                added  = true;
            }
        }
        return added;
    }

    @Override
    public default boolean removeAll(Collection<?> c) {
        boolean removed = false;
        for (Object o : c) {
            if (remove(o)) {
                remioved = true;
            }
        }
        return removed;
    }

    @Override
    default boolean retainAll(Collection<?> c) {
        boolean retained = false;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (!c.contains(iterator.next())) {
                iterator.remove();
                retained = true;
            }
        }
        return retained;
    }

    @Override
    public default Object[] toArray() {
        Object[] array = new Object[size()];
        int i = 0;
        for (E e : this) {
            array[i++] = e;
        }
        return array;
    }

    @Override
    default <T> T[] toArray(T[] array) {
        if (array.length < size()) {
            T[] newArray = (T[]) Arrays.copyOf(this.toArray(), size(), array.getClass());
            return newArray;
        }
        System.arraycopy(this.toArray(), 0, array, 0, size());
        if (array.length > size()) {
            array[size()] = null;
        }
        return array;
    }
}