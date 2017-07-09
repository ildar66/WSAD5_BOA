package com.boa.eagls.government.util.pagedList;

import com.boa.eagls.government.exceptions.system.EaglsDAOError;

import java.util.Collection;

/**
 * Like standart <code>Iterator</code> class methods <code>hasPrevious</code> <code>hasNext</code>should be invoked before
 * <code>getPrevious</code> and <code>getNext</code>
 * @author OlegK
 * Date: 04.07.2003
 */
public interface ValueListIterator {
    /**
     * Returns <code>Collection</code> of <code>numRecords</code> elements <b>before</b> the current position
     * of the <code>ValueListIterator</code>
     * @param numRecords the expected quantity of the elements in <code>Collection</code>
     * @return the <code>Collection</code>
     * @throws EaglsDAOError if some troubles in DataBase occurs
     * @throws IndexOutOfBoundsException if the <code>hasPrevious(int)</code> wasn't be executed before
     */
    public  Collection getPrevious(int numRecords)throws EaglsDAOError;
    /**
     * Returns <code>Collection</code> of <code>numRecords</code> elements <b>from</b> the current position
     * of the <code>ValueListIterator</code>
     * @param numRecords
     * @return the <code>Collection</code>
     * @throws EaglsDAOError
     * @throws IndexOutOfBoundsException if the <code>hasNext(int)</code> wasn't be executed before
     */
    public  Collection getNext(int numRecords) throws EaglsDAOError;
    /**
     * @return the total quantity of the elements in the DataBase used by this Iterator.
     */
    public  int getSize();

    /**
     * should be invoked before <code>getPrevious()</code>
     * @param numRecords
     * @return
     * @throws EaglsDAOError
     */    public boolean hasPrevious(int numRecords)  throws EaglsDAOError;

    /**
     * should be invoked before <code>getNext()</code>
     * @param numRecords
     * @return
     * @throws EaglsDAOError
     */
    public boolean hasNext(int numRecords) throws EaglsDAOError;

}
