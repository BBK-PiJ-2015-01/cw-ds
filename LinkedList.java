/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import static cwsrc.ErrorMessage.*;
import cwsrc.List;
import cwsrc.ReturnObject;

/**
 *
 * @author user0001
 */
public class LinkedList implements List {

    private ListItem head;
    private ListItem tail;

    public LinkedList() {
    }

    public LinkedList(Object[] fromArray) {

        initFromArray(fromArray);
    }

    private void initFromArray(Object[] fromArray) {

        if (fromArray != null) {
            for (Object o : fromArray) {
                add(o);
            }
        }
    }

    @Override
    public boolean isEmpty() {

        return head == null;
    }

    @Override
    public int size() {

        int indexCount = 0;
        ListItem item = head;
        while (item != null) {
            indexCount++;
            item = item.next;
        }
        return indexCount;
    }

    @Override
    public ReturnObject get(int index) {

        if (head == null) {
            return new ReturnObjectImpl(null, EMPTY_STRUCTURE);
        }
        if (isOutOfBounds(index)) {
            return new ReturnObjectImpl(null, INDEX_OUT_OF_BOUNDS);
        }
        if (index == 0) {
            return new ReturnObjectImpl(head.value, NO_ERROR);
        }
        ListItem listItemAtIndex = getListItemAtIndex(index);
        return new ReturnObjectImpl(listItemAtIndex.value, NO_ERROR);
    }

    @Override
    public ReturnObject remove(int index) {

        if (head == null) {
            return new ReturnObjectImpl(null, EMPTY_STRUCTURE);
        }
        if (isOutOfBounds(index)) {
            return new ReturnObjectImpl(null, INDEX_OUT_OF_BOUNDS);
        }

        ListItem listItemAtIndex = getListItemAtIndex(index);
        if (listItemAtIndex.prev != null) {
            listItemAtIndex.prev.next = listItemAtIndex.next;
        }
        if (listItemAtIndex.next != null) {
            listItemAtIndex.next.prev = listItemAtIndex.prev;
        }
        if (listItemAtIndex == head) {
            head = head.next;
        } else if (listItemAtIndex == tail) {
            tail = tail.prev;
        }
        if (head == null) {
            tail = null;
        }
        return new ReturnObjectImpl(listItemAtIndex.value, NO_ERROR);
    }

    @Override
    public ReturnObject add(int index, Object item) {

        if (item == null) {
            return new ReturnObjectImpl(null, INVALID_ARGUMENT);
        }
        if (index != 0 && isOutOfBounds(index)) {
            return new ReturnObjectImpl(null, INDEX_OUT_OF_BOUNDS);
        }
        ListItem addItem = new ListItem(item);
        if (head == null) {
            head = addItem;
            tail = head;
        } else {
            ListItem listItemAtIndex = getListItemAtIndex(index);
            addItem.next = listItemAtIndex;
            addItem.prev = listItemAtIndex.prev;
            listItemAtIndex.prev = addItem;
            if (addItem.prev == null) {
                head = addItem;
            } else {
                addItem.prev.next = addItem;
            }
        }
        return new ReturnObjectImpl(null, NO_ERROR);
    }

    @Override
    public ReturnObject add(Object item) {

        if (item == null) {
            return new ReturnObjectImpl(null, INVALID_ARGUMENT);
        }
        ListItem addItem = new ListItem(item);
        if (head == null) {
            head = addItem;
            tail = head;
        } else {
            tail.next = addItem;
            addItem.prev = tail;
            tail = addItem;
        }
        return new ReturnObjectImpl(null, NO_ERROR);
    }

    public Object[] asArray() {

        if (head == null) {
            return new Object[0];
        }
        Object[] returnArray = new Object[size()];
        int indexCount = 0;
        ListItem item = head;
        while (item != null) {
            returnArray[indexCount++] = item.value;
            item = item.next;
        }
        return returnArray;
    }

    private ListItem getListItemAtIndex(int index) {

        int indexCount = 0;
        ListItem item = head;
        while (item != null) {
            if (indexCount == index) {
                return item;
            }
            item = item.next;
            indexCount++;
        }
        return null;
    }

    private boolean isOutOfBounds(int index) {

        return index < 0 || (index > size() - 1);
    }

    private class ListItem {

        private final Object value;
        //
        private ListItem prev;
        private ListItem next;

        private ListItem(Object value) {
            this.value = value;
        }
    }
}
