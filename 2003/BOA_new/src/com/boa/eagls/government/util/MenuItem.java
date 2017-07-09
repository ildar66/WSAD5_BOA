/**
 * MenuItem
 */
package com.boa.eagls.government.util;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * To change this template use Options | File Templates.
 */
public class MenuItem
{
    private String name;
    private String hyperlink;
    private String target;
    private Vector functions;
    private boolean isSelected;

    /**
     * Constructor declaration
     *
     */
    public MenuItem()
    {
    }

    /**
     * Constructor declaration
     *
     *
     * @param name
     * @param hyperlink
     * @param target
     */
    public MenuItem(String name, String hyperlink, String target)
    {
        this.name = name;
        this.hyperlink = hyperlink;
        this.target = target;
        this.isSelected = false;
        this.functions = new Vector();
    }

    /**
     * Constructor declaration
     *
     *
     * @param name
     * @param hyperlink
     * @param target
     * @param functions
     */
    public MenuItem(String name, String hyperlink, String target,
                    Vector functions)
    {
        this.name = name;
        this.hyperlink = hyperlink;
        this.target = target;
        this.isSelected = false;
        this.functions = functions;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method declaration
     *
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getHyperlink()
    {
        return hyperlink;
    }

    /**
     * Method declaration
     *
     *
     * @param hyperlink
     */
    public void setHyperlink(String hyperlink)
    {
        this.hyperlink = hyperlink;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getTarget()
    {
        return target;
    }

    /**
     * Method declaration
     *
     *
     * @param target
     */
    public void setTarget(String target)
    {
        this.target = target;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public Vector getFunctions()
    {
        return functions;
    }

    /**
     * Method declaration
     *
     *
     * @param functions
     */
    public void setFunctions(Vector functions)
    {
        this.functions = functions;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public boolean getIsSelecetd()
    {
        return this.isSelected;
    }

    /**
     * Method declaration
     *
     *
     * @param selected
     */
    public void setIsSelected(boolean selected)
    {
        this.isSelected = selected;
    }

}
