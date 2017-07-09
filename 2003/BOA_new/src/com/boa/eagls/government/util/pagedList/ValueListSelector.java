package com.boa.eagls.government.util.pagedList;

import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.dao.DAOUtil;

import java.util.Iterator;
import java.util.Collection;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: OlegK
 * Date: 03.07.2003
 * Time: 19:05:11
 */
abstract public class ValueListSelector {

    private static Logger logger = Logger.getLogger(ValueListSelector.class);

    private static int CLAUSE_LENGTH = "SELECT".length();

    public abstract int count() throws EaglsDAOError;

    public abstract Collection select(int firstRecord, int numRecords) throws EaglsDAOError;

    public static final String generatePagedCountSQL(String sql) {
        sql = sql.toUpperCase();
        StringBuffer buf = new StringBuffer(sql);
        buf.delete(0, sql.toString().indexOf("FROM"));
        String res = buf.insert(0, "select count(*) ").toString();
        res = DAOUtil.parseForWildCard(res);
        logger.debug("sql in generatePagedCountSQL, OUT: " + res);
        return res;
    }

    /**
     * tranforms any SELECT SQL to paged SQL. For example the query
     * <code>select ACCOUNT_NBR, TRANS_DATE, POSTING_DATE, TRANSACTION_ID
     * from transaction order by ACCOUNT_NBR ASC</code>
     * will be transformed to <code>select * from (
     * select ROWNUM as MAGIC_NUMBER, ACCOUNT_NBR, TRANS_DATE, POSTING_DATE, TRANSACTION_ID
     * from (select ACCOUNT_NBR, TRANS_DATE, POSTING_DATE, TRANSACTION_ID
     * from transaction order by ACCOUNT_NBR ASC) where rownum<20)
     * where MAGIC_NUMBER>=10
     * @param sql
     * @param firstRecord
     * @param numRecords
     * @return
     */
    public static final String generatePagedSQL(String sql, int firstRecord, int numRecords) {
        sql = sql.toUpperCase().trim();
        StringBuffer buf = new StringBuffer(sql);
        buf.delete(sql.indexOf("SELECT"), CLAUSE_LENGTH);
        String fields = buf.substring(0, buf.toString().indexOf("FROM"));

        buf.insert(0, "select * from ( select MAGIC_TABLE.* " + //fields +
                ", ROWNUM as MAGIC_NUMBER from (select ");// + fields);

        buf.append(") MAGIC_TABLE where rownum<=" + (firstRecord+numRecords) +
                ") where MAGIC_NUMBER>=" + firstRecord);

        buf = new StringBuffer(DAOUtil.parseForWildCard(buf.toString()));
        logger.debug("sql in generatePagedSQL, OUT: " + buf + ", fr: " + firstRecord + ", nr: " + numRecords);
        return buf.toString();
    }

}
