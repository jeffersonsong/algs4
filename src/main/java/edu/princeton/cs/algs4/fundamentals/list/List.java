package edu.princeton.cs.algs4.fundamentals.list;

public interface List<Item> extends Iterable<Item> {
    /**
     * Add item to the front.
     * @param item item.
     */
    void addFront(Item item);

    /**
     * Add item to the back.
     * @param item item.
     */
    void addBack(Item item);

    /**
     * Remove from the front.
     * @return first item.
     */
    Item deleteFront();

    /**
     * Remove from the back.
     * @return last item.
     */
    Item deleteBack();

    /**
     * Remove item from the list.
     * @param item item to be removed.
     */
    void delete(Item item);

    /**
     * Add item as the ith in the list.
     * @param i position to be added.
     * @param item item to be added.
     */
    void add(int i, Item item);

    /**
     * Remove the ith item from the list.
     * @param i index.
     * @return item removed.
     */
    Item delete(int i);

    /**
     * Is key in the list?
     * @param item item.
     * @return true if key in the list otherwise false.
     */
    boolean contains(Item item);

    /**
     * @return Is the list empty?
     */
    boolean isEmpty();

    /**
     * @return Number of items in the list.
     */
    int size();
}
