package com.boa.eagls.government.service.transactiondata;

import org.apache.log4j.Logger;

/**
 * <p><small> VDI Company, 29.07.2003 16:43:35</small></p>
 * @author StanislavS
 */
public class SearchCriteria {
    int sortTransBy;
    int searchFor;
    String searchByDate;
    int searchOption;
    String acctNumber;
    private int[] hCodes = new int[9];
    private int depth;

    public int[] gethCodes() {
        return hCodes;
    }

    public void sethCodes(int[] hCodes) {
        this.hCodes = hCodes;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getSearchFor() {
        return searchFor;
    }

    public void setSearchFor(int searchFor) {
        this.searchFor = searchFor;
    }

    public String getAcctNumber() {
        return acctNumber;
    }

    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
    }

    public int getSortTransBy() {
        return sortTransBy;
    }

    public void setSortTransBy(int sortTransBy) {
        this.sortTransBy = sortTransBy;
    }

    public String getSearchByDate() {
        return searchByDate;
    }

    public void setSearchByDate(String searchByDate) {
        this.searchByDate = searchByDate;
    }

    public int getSearchOption() {
        return searchOption;
    }

    public void setSearchOption(int searchOption) {
        this.searchOption = searchOption;
    }

}
