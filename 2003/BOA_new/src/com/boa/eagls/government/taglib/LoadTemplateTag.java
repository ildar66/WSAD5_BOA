package com.boa.eagls.government.taglib;

import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;
import org.apache.struts.taglib.html.Constants;
import org.apache.log4j.Logger;

import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.util.GeneratePage;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import java.io.IOException;

public class LoadTemplateTag extends TagSupport
{
    private static final Logger logger= Logger.getLogger(LoadTemplateTag.class);
    /**
     * The name of the bean containing our underlying property.
     */
    protected String name = Constants.BEAN_KEY;

    public String getName()
    {
        return (this.name);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * The name of the bean property to be used as a variable.
     */
    protected String property = null;

    public String getProperty()
    {
        return (this.property);
    }

    public void setProperty(String property)
    {
        this.property = property;
    }

    private String filterName = "";

    public String getFilterName()
    {
        return filterName;
    }

    public void setFilterName(String filterName)
    {
        this.filterName = filterName;
    }

    private String filterValue = "";

    public String getFilterValue()
    {
        return filterValue;
    }

    public void setFilterValue(String filterValue)
    {
        this.filterValue = filterValue;
    }

    private String contextPath = "";

    public String getContextPath()
    {
        return contextPath;
    }

    public void setContextPath(String contextPath)
    {
        this.contextPath = contextPath;
    }


    /**
     * The user principal name to be checked for.
     */
    protected String user = null;

    public String getUser()
    {
        return (this.user);
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    /**
     * The user xml filename to be loaded.
     */
    protected String xmlName = null;

    public String getXmlName()
    {
        return (this.xmlName);
    }

    public void setXmlName(String xmlName)
    {
        this.xmlName = xmlName;
    }

    /**
     * The user xlst filename used for the transformation.
     */
    protected String xsltName = null;

    public String getXsltName()
    {
        return (this.xsltName);
    }

    public void setXsltName(String xsltName)
    {
        this.xsltName = xsltName;
    }

    /**
     * Get the filter criteria for the HTML blocks and then generate the required page
     *
     * @exception JspException if a JSP exception occurs
     */
    public int doStartTag() throws JspException
    {
//        System.out.println("LoadTemplateTag --> doStartTag() --> Start" + name);
        GeneratePage pageGenerator = new GeneratePage(contextPath, xmlName, xsltName, filterName, filterValue);
        try
        {
            StringBuffer results = new StringBuffer(pageGenerator.generate());
            String page = results.toString();
//            System.out.println("LoadTemplateTag --> doStartTag() --> Page \n"+page);
            page = replaceEntities(page, "##hs##", "&#160");
            ResponseUtils.write(pageContext, replaceEntities(page, "##nbsp##", "&nbsp;"));
        }
        catch (IOException t)
        {
//        System.out.println("LoadTemplateTag --> doStartTag() --> Exception "+t);
            RequestUtils.saveException(pageContext, t);
            throw new JspException("Exception " + t);
//                    (messages.getMessage("eagls.generate.IOException", name, property,
//                            t.toString()));
        }
        catch (Throwable t)
        {
//        System.out.println("LoadTemplateTag --> doStartTag() --> Exception "+t);
            logger.error(t.getMessage(), t);
            RequestUtils.saveException(pageContext, t);
            throw new JspException("Exception " + t);
//                    (messages.getMessage("eagls.generate.Throwable", name, property,
//                            t.toString()));
        }
//        System.out.println("LoadTemplateTag --> doStartTag() --> End ");

        return (SKIP_BODY);
    }


    /**
     * Evaluate the remainder of the current page normally.
     *
     * @exception JspException if a JSP exception occurs
     */
    public int doEndTag() throws JspException
    {
        return (EVAL_PAGE);
    }


    /**
     * Release all allocated resources.
     */
    public void release()
    {

        super.release();
        xmlName = null;
        xsltName = null;
        property = null;
        user = null;
    }

    /**
     * Changes the placeholders for the entities in the input string
     *
     * @param page The string (page) containing the entities
     * @param entityOld The entity to be replaced
     * @param entityNew The new value for the entity
     * @return The string (page) containing the desired (html) entities
     */
    String replaceEntities(String page, String entityOld, String entityNew)
    {
        int indx;
        while ((indx = page.indexOf(entityOld)) >= 0)
        {
            page = page.substring(0, indx) + entityNew + page.substring(indx + entityOld.length());
        }
        return page;
    }
}