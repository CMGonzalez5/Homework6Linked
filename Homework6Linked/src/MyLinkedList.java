public class MyLinkedList<E> implements MyList<E> {
    protected Node<E> head, tail;
    protected int size = 0; // Number of elements in the list

    /** Create an empty list */
    public MyLinkedList() {
    }

    /** Create a list from an array of objects */
    public MyLinkedList(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    /** Return the head element in the list */
    public E getFirst() {
        if (size == 0) {
            return null;
        }
        else {
            return head.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            return null;
        }
        else {
            return tail.element;
        }
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node
        newNode.next = head; // link the new node with the head
        head = newNode; // head points to the new node
        size++; // Increase list size

        if (tail == null) // the new node is the only node in list
            tail = head;
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); // Create a new for element e

        if (tail == null) {
            head = tail = newNode; // The new node is the only node in list
        }
        else {
            tail.next = newNode; // Link the new with the last node
            tail = newNode; // tail now points to the last node
        }

        size++; // Increase size
    }

    @Override /** Add a new element at the specified index
     * in this list. The index of the head element is 0 */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        }
        else if (index >= size) {
            addLast(e);
        }
        else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp;
            size++;
        }
    }

    /** Remove the head node and
     *  return the object that is contained in the removed node. */
    public E removeFirst() {
        if (size == 0) { // if list is empty nothing to delete
            return null;
        }
        else {
            E temp = head.element;// store element to be removed/returned
            head = head.next; //remove first node by pointing head to second node
            size--;
            if (head == null) {// if list becomes empty after removal tail should be null
                tail = null;
            }
            return temp;
        }
    }

    /** Remove the last node and
     * return the object that is contained in the removed node. */
    public E removeLast() {
        if (size == 0) {// if list is empty
            return null;
        }
        else if (size == 1) {// if list has single element
            E temp = head.element;//store element to be removed/returned
            head = tail = null;
            size = 0;
            return temp;
        }
        else {
            Node<E> current = head;
            //iterate current until second to last node
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }

            E temp = tail.element;//store element to be removed/returned
            tail = current; //tail is now pointing to second to last node
            tail.next = null;
            size--;
            return temp;
        }
    }

    @Override /** Remove the element at the specified position in this
     *  list. Return the element that was removed from the list. */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;// if index is beyond range of list return null
        }
        else if (index == 0) {//if only one item
            return removeFirst();
        }
        else if (index == size - 1) {//if index last item
            return removeLast();
        }
        else {
            Node<E> previous = head;
            //iterate until you reach one index before the specified index
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }

            Node<E> current = previous.next;//store node to be removed/returned
            previous.next = current.next;// after this current is not on the list
            size--;
            return current.element;
        }
    }

    @Override /** Override toString() to return elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            }
            else {
                result.append("]"); // Insert the closing ] in the string
            }
        }

        return result.toString();
    }

    @Override /** Clear the list */
    public void clear() {
        size = 0;
        head = tail = null;
    }

    @Override /** Return true if this list contains the element e */
    public boolean contains(Object e) {
        // Left as an exercise
        return true;
    }

    @Override /** Return the element at the specified index */
    public E get(int index) {
        // Left as an exercise
        return null;
    }

    @Override /** Return the index of the first matching element in
     *  this list. Return -1 if no match. */
    public int indexOf(Object e) {
        // Left as an exercise
        return 0;
    }

    @Override /** Return the index of the last matching element in
     *  this list. Return -1 if no match. */
    public int lastIndexOf(E e) {
        // Left as an exercise
        return 0;
    }

    @Override /** Replace the element at the specified position
     *  in this list with the specified element. */
    public E set(int index, E e) {
        // Left as an exercise
        return null;
    }

    @Override /** Override iterator() defined in Iterable */
    public java.util.Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator
            implements java.util.Iterator<E> {
        private Node<E> current = head; // Current index

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }

        @Override
        public void remove() {
            // Left as an exercise
        }
    }

    protected static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    @Override /** Return the number of elements in this list */
    public int size() {
        return size;
    }
}