package com.boa.eagls.government.taglib.pagedList;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;

/**
 * <p><small> DVI Company, 01.08.2003 13:53:11</small></p>
 * @author romanso
 */
public class PageListException extends JspException{
    public PageListException(String message, Throwable error){
        super(message, error);
    }

}