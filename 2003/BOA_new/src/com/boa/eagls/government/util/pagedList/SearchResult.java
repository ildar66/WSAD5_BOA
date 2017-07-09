package com.boa.eagls.government.util.pagedList;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: OlegK
 * Date: 04.07.2003
 * Time: 10:23:10
 * To change this template use Options | File Templates.
 */
public class SearchResult {
    private int size;
    private Collection elements;
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public Collection getElements() {
        return elements;
    }
    public void setElements(Collection elements) {
        this.elements = elements;
    }
}
