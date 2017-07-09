/**
 * DynMenuState
 */
package com.boa.eagls.government.util;

/**
 * Package containing data structures for storage of dynamic menu state in a
 * very compressed format to reduce the amount of session space needed per user.
 * @version
 * $Revision: 1.4 $
 */

import java.io.*;
import java.util.*;

import com.boa.eagls.government.statemgmt.EAGLSSession;
import org.apache.log4j.Logger;

// The class to hold the dynamic menu state and provide
// necessary functionality.

/**
 * Class declaration
 *
 *
 * @author
 * @version %I%, %G%
 */
public class DynMenuState
{
    private static final Logger logger= Logger.getLogger(DynMenuState.class);
    final static char EXPANDED = 'E';
    final static char CONTRACTED = 'C';
    final static char NOT_AVAILABLE = 'N';
    final static char TOP_LEVEL_FUNCTION = 'F';
    private String _state;
    private String[][] _functions;

    /**
     * @param functions
     */
    public DynMenuState(String[][] functions)
    {
        StringBuffer sb = new StringBuffer(functions.length);

        for (int i = 0; i < functions.length; i++)
        {
            if (functions[i][1].equals("Hidden"))
            {    // not available to the user role
                sb.append(NOT_AVAILABLE);
            }
            else if (functions[i][0].equals("_top_level_function"))
            {
                sb.append(TOP_LEVEL_FUNCTION);
            }
            else
            {
                sb.append(CONTRACTED);
            }
        }
        _state = sb.toString();
        _functions = functions;
    }

    /**
     * @param functions
     * @param state
     */
    public DynMenuState(String[][] functions, String state)
    {
        _state = state;
        _functions = functions;
        if (functions.length != state.length())
        {
            logger.debug("functions.length and state.length() Don't match!!\nfunctions.length = "
                    + functions.length + "\nstate.length() = "
                    + state.length());
        }
    }

    /**
     * @param name
     * @param state
     */
    private void setState(String name, char state)
    {
        if (!name.equals("_top_level_function"))
        {
            StringBuffer sb = new StringBuffer(_state);

            for (int i = 0; i < _functions.length; i++)
            {
                if (_functions[i][1].equals(name))
                {
                    sb.setCharAt(i, state);
                }
            }
            _state = sb.toString();
        }
    }

    /**
     * Method declaration
     *
     *
     * @param name
     */
    public void select(String name)
    {
        setState(name.replace('~', ' '), EXPANDED);
    }

    /**
     * Method declaration
     *
     *
     * @param name
     */
    public void deselect(String name)
    {
        setState(name.replace('~', ' '), CONTRACTED);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String toString()
    {
        return new String(_state);
    }

    /**
     * @param v
     */
    private void alphabetizeByCategory(Vector v)
    {
        int size = v.size();

        for (int i = 0; i < size - 1; i++)
        {
            for (int j = i + 1; j < size; j++)
            {
                if (((String[]) v.elementAt(j))[1]
                        .compareTo(((String[]) v.elementAt(i))[1]) < 0)
                {
                    v.insertElementAt(v.elementAt(j), i);
                    v.removeElementAt(j + 1);
                }
            }
        }
    }

    /**
     * @param v
     */
    private void alphabetizeByFunction(Vector v)
    {
        int size = v.size();

        for (int i = 0; i < size - 1; i++)
        {
            for (int j = i + 1; j < size; j++)
            {
                if (((String[]) v.elementAt(j))[0]
                        .compareTo(((String[]) v.elementAt(i))[0]) < 0)
                {
                    v.insertElementAt(v.elementAt(j), i);
                    v.removeElementAt(j + 1);
                }
            }
        }
    }

    /**
     * @param v
     */
    private void alphabetizeStrings(Vector v)
    {
        int size = v.size();

        for (int i = 0; i < size - 1; i++)
        {
            for (int j = i + 1; j < size; j++)
            {
                if (((String) v.elementAt(j))
                        .compareTo((String) v.elementAt(i)) < 0)
                {
                    v.insertElementAt(v.elementAt(j), i);
                    v.removeElementAt(j + 1);
                }
            }
        }
    }

    /**
     * check if given category is already expaneded
     * @param name
     * @return
     */
    private boolean isCategoryExpanded(String name)
    {
        for (int i = 0; i < _functions.length; i++)
        {
            if (_functions[i][1].equals(name))
            {
                if (_state.charAt(i) == EXPANDED)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * generate display menu items
     * @return Vector
     */
    public Vector generateDisplayList()
    {
        Vector menu = new Vector();
        Vector visFunctions = new Vector();

        for (int i = 0; i < _functions.length && i < _state.length(); i++)
        {
            if (_state.charAt(i) == EXPANDED)
            {
                visFunctions.addElement(_functions[i]);
            }
        }
        alphabetizeByFunction(visFunctions);
        Vector categories = new Vector();
        Hashtable topLevelFunctions = new Hashtable();

        for (int i = 0; i < _functions.length; i++)
        {
            try
            {
                if (_state.charAt(i) != NOT_AVAILABLE
                        && !categories.contains(_functions[i][1]))
                {
                    if (_functions[i][1].equals("_top_level_function"))
                    {
                        categories
                                .addElement(_functions[i][0]);    // add function name
                        topLevelFunctions
                                .put(_functions[i][0],
                                        _functions[i][2]);    // add function name and url to hash
                    }
                    else
                    {
                        categories
                                .addElement(_functions[i][1]);    // add category name
                    }
                }
            }
            catch (StringIndexOutOfBoundsException se)
            {
                if (_state.length()
                        < i)    // this is a band aid. state should not be shorter than functions.
                {
                    _state += CONTRACTED;
                }
            }
        }
        alphabetizeStrings(categories);
        int numCat = categories.size();

        for (int i = 0; i < numCat; i++)
        {
            String currentCategory = (String) categories.elementAt(i);
            boolean functionsVisible = isCategoryExpanded(currentCategory);

            if (topLevelFunctions.containsKey(currentCategory))
            {
                String url = (String) topLevelFunctions.get(currentCategory);
                String concatOperator = null;

                if (url.lastIndexOf('?') == url.length() - 1)
                {
                    concatOperator = "";
                }
                else if (url.indexOf('?') >= 0)
                {
                    concatOperator = "&";
                }
                else
                {
                    concatOperator = "?";
                }
                MenuItem menuItem =
                        new MenuItem(currentCategory,
                                url + concatOperator + "name="
                        + currentCategory.replace(' ', '~')
                        + "&selected="
                        + (functionsVisible ? "T" : "F"), "content");

                menu.add(menuItem);
            }
            else
            {
                MenuItem menuItem =
                        new MenuItem(currentCategory,
                                "DynMenuSelect.do?name="
                        + currentCategory.replace(' ', '~')
                        + "&selected="
                        + (functionsVisible ? "T" : "F"), "_self");

                // check if this menu item has child items
                if (functionsVisible)
                {
                    Vector functions = new Vector();

                    for (int j = 0; j < visFunctions.size(); j++)
                    {
                        String[] currentFunction =
                                (String[]) visFunctions.elementAt(j);

                        if (currentCategory.equals(currentFunction[1]))
                        {
                            functions
                                    .add(new MenuItem(currentFunction[0].substring(2),
                                            currentFunction[2]
                                    + "&selected="
                                    + (functionsVisible ? "T"
                                    : "F"), "_self"));
                        }
                    }
                    menuItem.setFunctions(functions);
                }
                else
                {
                    menuItem.setFunctions(new Vector());
                }
                menu.add(menuItem);
            }
        }
        return menu;
    }

}
