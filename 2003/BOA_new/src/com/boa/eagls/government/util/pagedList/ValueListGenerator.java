package com.boa.eagls.government.util.pagedList;

import com.boa.eagls.government.exceptions.system.EaglsDAOError;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: OlegK
 * Date: 03.07.2003
 * Time: 19:05:11
 */
abstract public class ValueListGenerator {

    private static int CLAUSE_LENGTH = "select".length();

    abstract public SearchResult doSearch(int firstRecord, int numRecords) throws EaglsDAOError;

    public static String generatePagedCountSQL(String sql) {
        StringBuffer buf = new StringBuffer(sql);
        buf.delete(0, sql.toString().indexOf("from"));
        return buf.insert(0, "select count(*) ").toString();
    }

    public static String generatePagedSQL(String sql, int firstRecord, int numRecords) {

        StringBuffer buf = new StringBuffer(sql.toLowerCase());
        buf.delete(sql.indexOf("select"), CLAUSE_LENGTH);
        String fields = buf.substring(0, buf.toString().indexOf("from"));

        buf.insert(0, "select * from ( select " + fields +
                ", ROWNUM as MAGIC_NUMBER from (select ");// + fields);

        buf.append(") where rownum<" + (firstRecord+numRecords) +
                ") where MAGIC_NUMBER>=" + firstRecord);

        return buf.toString();
    }

}
