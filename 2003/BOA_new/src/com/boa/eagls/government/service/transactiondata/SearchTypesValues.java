package com.boa.eagls.government.service.transactiondata;

/**
 * Created by IntelliJ IDEA.
 * User: StanislavS
 * Date: 16.07.2003
 * Time: 18:55:14
 * To change this template use Options | File Templates.
 */
public interface SearchTypesValues {
    public static final short CURRENT_TRANSACTIONS = 0;
    public static final short CURRENT_STATEMENT = 1;
    public static final short CURRENT_INVOICE = 2;//1

    //searchFor
    public final short FOR_CURRENT_TRANSACTIONS_BY_ACCOUNTNUM = 0;
    public final short FOR_CURRENT_TRANSACTIONS_BY_HIERARCHY = 1;
    public final short FOR_CURRENT_STATEMENT_BY_ACCOUNTNUM = 2;
    public final short FOR_CURRENT_STATEMENT_BY_HIERARCHY = 3;
    public final short FOR_DATE_BY_ACCOUNTNUM = 4;
    public final short FOR_DATE_BY_HIERARCHY = 5;
    public final short FOR_DATE_RANGE_BY_ACCOUNTNUM = 6;
    public final short FOR_DATE_RANGE_BY_HIERARCHY = 7;

    //search options
    public static final short TRANSACTION_POST_DATE = 0;
    public static final short TRANSACTION_AMOUNT = 1;
    public static final short TRANSACTION_STATUS = 2;
    public static final short MERCHANT = 3;

}
