package com.boa.eagls.government.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/**
 * Contains key-calue pairs for WHERE SQL clause.
 * User: OlegK
 * Date: 27.06.2003
 * Time: 18:44:43
 * To change this template use Options | File Templates.
 */
public class ValListWrap {

    private HashMap keyVals = new HashMap();


    /**
     * creates a new key-value pair for WHERE SQL clause.
      * @param key
     * @param val
     */
    public void set(String key, String val) {
        keyVals.put(key, val);
    }

    /**
     *
     * @param orig
     * @param key
     * @param val
     * @return
     */
    private static String replace(String orig, String key, String val) {
        String res = null;
        int pos = orig.indexOf(key);
        if (pos>=0) {
            res = orig.substring(0, pos);
            res += val;
            res += orig.substring(pos+key.length());
        }
        else {
            res = orig;
        }
        return res;
    }

    /**
     * Repcales all accurances of ":key" method in SQL query to accordanly value
     * @param str
     */
    public String substituteParameters(String str) {
        Iterator it = keyVals.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            str = replace(str, (String)entry.getKey(), (String)entry.getValue());
        }
        return str;
    }
}
