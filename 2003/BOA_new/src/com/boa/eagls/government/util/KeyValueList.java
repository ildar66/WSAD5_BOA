/**
 * KeyValueList
 */
package com.boa.eagls.government.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Key Value List
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class KeyValueList implements Iterator, Serializable {
    private int size;
    private int iterationPosition;
    private HashMap dataMap;

    /**
     * Constructor declaration
     *
     */
    public KeyValueList() {
        size = 0;
        iterationPosition = 0;
        dataMap = new HashMap();
    }

    /**
     * Method declaration
     *
     *
     * @param colData
     */
    public void add(ColumnData colData) {
        Integer i = new Integer(size);

        dataMap.put(i, colData);
        size++;
    }

    /**
     * Method declaration
     *
     *
     * @param key
     * @param value
     * @param paramType
     */
    public void add(String key, Object value, String paramType) {
        ColumnData colData = new ColumnData(key, value, paramType);

        add(colData);
    }

    /**
     * Method declaration
     *
     *
     * @param key
     * @param value
     */
    public void add(String key, Object value) {
        add(key, value, SQLConstants.OUTPUT_PARAMETER);
    }

    /**
     * Method declaration
     *
     *
     * @param key
     * @param value
     * @param paramType
     * @param paramDataType
     */
    public void add(String key, Object value, String paramType,
                    Object paramDataType) {
        ColumnData colData = new ColumnData(key, value, paramType,
                paramDataType);

        add(colData);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public ColumnData first() {
        iterationPosition = 0;
        return (ColumnData) dataMap.get(new Integer(iterationPosition++));
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public ColumnData nextColumn() {
        ColumnData returnValue = null;

        if (hasNext()) {
            iterationPosition = 0;

            // return null;
        } else {

            // return (ColumnData)dataMap.get(new Integer(iterationPosition++));
            returnValue =
                    (ColumnData) dataMap.get(new Integer(iterationPosition++));
        }
        return returnValue;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Method declaration
     *
     *
     * @param i
     *
     * @return
     */
    public ColumnData elementAt(int i) {
        ColumnData returnValue = null;

        if ((i <= size) && (i >= 0)) {

            // return (ColumnData)dataMap.get(new Integer(i));
            returnValue = (ColumnData) dataMap.get(new Integer(i));
        }

        /*
         * else {
         * return null;
         * }
         */
        return returnValue;
    }

    /**
     * Method declaration
     *
     *
     * @param key
     *
     * @return
     */
    public Object getValue(String key) {
        Object returnValue = null;

        if (this.getSize() > 0) {
            ColumnData cd = null;

            while (null != (cd = this.nextColumn())) {
                if (key.equals(cd.getColumnName())) {
                    returnValue = cd.getValue();
                }
            }
        }
        return returnValue;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String toString() {
        return "size=" + size + ", iterationPosition=" + iterationPosition
                + " , dataMap=" + dataMap + "";
    }

    public boolean hasNext() {
        return (iterationPosition >= size);
    }

    public Object next() {
        return nextColumn();
    }

    public void remove() {
        throw new java.lang.NoSuchMethodError("NOT IMPLEMENTED");
    }

}
