package com.boa.eagls.government.util.pagedList;

import com.boa.eagls.government.exceptions.system.EaglsDAOError;

import java.util.Collection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

/** This implementation of <code>ValueListIterator</code>  should be used with some assumption
 * So, the method <code>hasPrevious()</code> should be invoked before <code>getPrevious(int)</code> and
 * the method <code>hasNext()</code> should be invoked before <code>getNext(int)</code>. (In fact this is a standars
 * beheviour of the <code>Iterator</code> pattern)<br>
 * the method <code>getSize()</code> will return a correct value immediately after creation of the Object or after any
 * execution of <code>hasPrevious()</code> or <code>hasNext()</code> methods.
 *
 * Created by IntelliJ IDEA.
 * User: OlegK
 * Date: 03.07.2003
 * Time: 15:15:26
 */

public class ValueListHandler implements ValueListIterator {

    private static Logger logger = Logger.getLogger(ValueListHandler.class);

    private static int START_INDEX = 0;

    private ValueListSelector selector = null;
    private int position = START_INDEX;
    private int size = 0;
//    private boolean hasPrev;
//    private boolean hasNext;

    public ValueListHandler(ValueListSelector selector) throws EaglsDAOError {
        this.selector = selector;
        size = selector.count();
    }

    public final int getSize() {
        return size;
    }

    public final Collection getPrevious(int numRecords) throws EaglsDAOError {
        size = selector.count();
        position -= numRecords;//results.size();
        if (position < START_INDEX)
            throw new IndexOutOfBoundsException("No previous elements exist: Index: " + position + ", Size: " + size);
        logger.debug("calling getPrevious position: " + position + ", Size: " + size);
        Collection results = selector.select(position - numRecords, numRecords);

//        if (position-numRecords >= START_INDEX) {
//            position -= results.size();
//        }

//        if (position-numRecords >= START_INDEX) {
//            hasPrev = true;
//        }
//        else {
//            hasPrev = false;
//        }
//        if (position+numRecords < size) {
//            hasNext = true;
//        }
//        else {
//            hasNext = false;
//        }
        logger.debug("calling getPrevious position AFTER : " + position + ", Size: " + size);
        return results;
    }

    public final Collection getNext(int numRecords) throws EaglsDAOError {
        size = selector.count();
        if (size == 0) {
            return new ArrayList();
        }
        if (position >= size)
            throw new IndexOutOfBoundsException("No more elements exist: Index: " + position + ", Size: " + size);
        logger.debug("calling getNext");
        Collection results = selector.select(position, numRecords);
        logger.debug("BEFORE results.size(): " + results.size() + ", position:=" + position);
        position += numRecords;//results.size();
//        if (position-numRecords >= START_INDEX) {
//            hasPrev = true;
//        }
//        else {
//            hasPrev = false;
//        }
//        if (position+numRecords < size) {
//            position += results.size();
//        }
//        if (position+numRecords < size) {
//            position += results.size();
//            hasNext = true;
//        }
//        else {
//            hasNext = false;
//        }
        logger.debug("AFTER results.size(): " + results.size() + ", position:=" + position);
        return results;
    }

    public boolean hasPrevious(int numRecords) throws EaglsDAOError {
//        return hasPrev;
        size = selector.count();
        logger.debug("hasPrev()! position: " + position + ", size: " + size);
        return position-numRecords > START_INDEX;
    }

    public boolean hasNext(int numRecords) throws EaglsDAOError {
//        return hasNext;
        size = selector.count();
        if (size == 0) {
            return false;
        }
        logger.debug("hasNext()! position: " + position + ", size: " + size);
        return position <= size;
    }

    protected void finalize() throws Throwable {
        selector = null;
        super.finalize();
    }
}
