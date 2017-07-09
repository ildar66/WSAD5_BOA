/**
 * Parent
 */
package com.boa.eagls.government.util;

/**
 * Package containing data structures for storage of dynamic menu state and data.
 * @version $Revision: 1.3 $
 */

import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

// Parent contains top level entries in the menu.  Each parent can
// have one or more children, which are displayed when the parent is
// selected.  The guid is optional; the default will be the applogic
// that toggles the selection status.

/**
 * Parent
 *
 *
 * @author
 * @version %I%, %G%
 */
class Parent implements Serializable{
    private static final Logger logger= Logger.getLogger(Parent.class);
    private String _name;
    private Vector _children;
    private boolean _selected;
    private String _guid;

    /**
     * Constructor declaration
     *
     *
     * @param name
     */
    public Parent(String name)
    {
        _name = name;
        _selected = false;
        _children = new Vector();
        _guid = "{A8B18EF0-2B9E-11D2-96E0-204C4F4F5020}";
    }

    /**
     * Constructor declaration
     *
     *
     * @param name
     * @param guid
     */
    public Parent(String name, String guid)
    {
        _name = name;
        _selected = false;
        _children = new Vector();
        _guid = guid;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getGUID()
    {
        return _guid;
    }

    /**
     * Method declaration
     *
     */
    public void alphabetize()
    {
        int size = _children.size();

        for (int i = 0; i < size - 1; i++)
        {
            for (int j = i + 1; j < size; j++)
            {
                if (((Child) _children.elementAt(j)).getName()
                        .compareTo(((Child) _children.elementAt(i))
                        .getName()) < 0)
                {
                    _children.insertElementAt(_children.elementAt(j), i);
                    _children.removeElementAt(j + 1);
                }
            }
        }
    }

    /**
     * Method declaration
     *
     *
     * @param child
     */
    public void addChild(Child child)
    {
        _children.addElement(child);
    }

    /**
     * Method declaration
     *
     *
     * @param name
     *
     * @return
     */
    public boolean removeChild(String name)
    {
        Child child = find(name);

        if (child != null)
        {
            return _children.removeElement(child);
        }
        else
        {
            return false;
        }
    }

    /**
     * Method declaration
     *
     *
     * @param name
     *
     * @return
     */
    public Child find(String name)
    {
        for (int i = 0; i < _children.size(); i++)
        {
            if (((Child) _children.elementAt(i)).getName().equals(name))
            {
                return (Child) _children.elementAt(i);
            }
        }
        return null;
    }

    /**
     * Method declaration
     *
     *
     * @param p
     *
     * @return
     */
    public boolean equals(Parent p)
    {
        return p._name == _name;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getName()
    {
        return _name;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getURLName()
    {
        return _name.replace(' ', '~');
    }

    /**
     * Method declaration
     *
     */
    public void toggle()
    {
        _selected = !_selected;
    }

    /**
     * Method declaration
     *
     */
    public void select()
    {
        _selected = true;
    }

    /**
     * Method declaration
     *
     */
    public void deselect()
    {
        _selected = false;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public boolean isSelected()
    {
        return _selected;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public boolean hasChildren()
    {
        return (_children.size() > 0);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public Child[] getChildren()
    {
        Child[] childArray = new Child[_children.size()];

        _children.copyInto(childArray);
        return childArray;
    }

}

// Child contains data for a menu item under a parent.  The
// data consists of a name and guid pair.  The guid is called
// without parameters whenever the name is clicked on the screen.

/**
 * Class declaration
 *
 *
 * @author
 * @version %I%, %G%
 */
class Child implements Serializable
{
    String _name;
    String _guid;

    /**
     * Constructor declaration
     *
     *
     * @param name
     * @param guid
     */
    public Child(String name, String guid)
    {
        _name = name;
        _guid = guid;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getName()
    {
        return _name;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getGUID()
    {
        return _guid;
    }

}

// The class to hold all the dynamic menu data and provide
// necessary functionality.  Basic functionality includes adding
// parent and child items, toggling a parent, and generating
// TemplateDataBasic to display the current state of the menu.
// Static functions are included to support serialization and
// deserialization of the class.

/**
 * Class declaration
 *
 *
 * @author
 * @version %I%, %G%
 */
public class DynMenuData implements Serializable
{
    private static final Logger logger= Logger.getLogger(DynMenuData.class);
    private Vector _parents;
    private static final long serialVersionUID = 1;

    /**
     * Constructor declaration
     *
     */
    public DynMenuData()
    {
        _parents = new Vector();
    }

    /**
     * Method declaration
     *
     *
     * @param name
     *
     * @return
     */
    public Parent addParent(String name)
    {
        Parent parent = new Parent(name);

        _parents.addElement(parent);
        return parent;
    }

    /**
     * Method declaration
     *
     *
     * @param name
     *
     * @return
     */
    public boolean removeParent(String name)
    {
        boolean returnValue = false;
        Parent parent = find(name);

        if (name != null)
        {
            returnValue = _parents.removeElement(parent);
        }
        return returnValue;
    }

    /**
     * Method declaration
     *
     */
    public void alphabetize()
    {
        int size = _parents.size();

        for (int i = 0; i < size - 1; i++)
        {
            for (int j = i + 1; j < size; j++)
            {
                if (((Parent) _parents.elementAt(j)).getName()
                        .compareTo(((Parent) _parents.elementAt(i))
                        .getName()) < 0)
                {
                    _parents.insertElementAt(_parents.elementAt(j), i);
                    _parents.removeElementAt(j + 1);
                }
            }
        }
        for (int i = 0; i < size; i++)
        {
            ((Parent) _parents.elementAt(i)).alphabetize();
        }
    }

    /**
     * Find Parent
     * @param name
     * @return Parent
     */
    public Parent find(String name)
    {
        Parent returnValue = null;

        for (int i = 0; i < _parents.size(); i++)
        {
            if (((Parent) _parents.elementAt(i)).getName().equals(name))
            {
                returnValue = (Parent) _parents.elementAt(i);
            }
        }
        return returnValue;
    }

    /**
     * Add child
     * @param name
     * @param parentName
     * @param guid
     */
    public void addChild(String name, String parentName, String guid)
    {
        Parent parent = find(parentName);

        if (parent == null)
        {
            parent = addParent(parentName);
        }
        if (parentName != null && guid != null && name.length() > 2)
        {
            parent.addChild(new Child(name.substring(2), guid));
        }
    }

    /**
     * RemoveChild
     * @param name
     * @param parentName
     * @return boolean
     */
    public boolean removeChild(String name, String parentName)
    {
        boolean returnValue = false;
        Parent parent = find(parentName);

        if (parent != null && parent.removeChild(name))
        {
            if (!parent.hasChildren())
            {    // remove parent with no children
                removeParent(parentName);
            }
            returnValue = true;
        }
        return returnValue;
    }

    /**
     * Toggle
     * @param name
     * @return boolean
     */
    public boolean toggle(String name)
    {
        boolean returnValue = false;
        Parent parent = find(name.replace('~', ' '));

        if (parent != null)
        {
            parent.toggle();
            returnValue = true;
        }
        return returnValue;
    }

    /**
     * selects a value
     * @param name
     * @return boolean
     */
    public boolean select(String name)
    {
        boolean returnValue = false;
        Parent parent = find(name.replace('~', ' '));

        if (parent != null)
        {
            parent.select();
            returnValue = true;
        }
        return returnValue;
    }

    /**
     * Deselects a value
     * @param name
     * @return boolean
     */
    public boolean deselect(String name)
    {
        boolean returnValue = false;
        Parent parent = find(name.replace('~', ' '));

        if (parent != null)
        {
            parent.deselect();
            returnValue = true;
        }
        return returnValue;
    }

    /**
     * Generate Display List
     * @return Vector
     */
    public Vector generateDisplayList()
    {
        Vector menu = new Vector();
        Parent parent;

        // --String hyperlink;
        for (int i = 0; i < _parents.size(); i++)
        {
            parent = (Parent) _parents.elementAt(i);
            menu.add("name=" + parent.getName()
                    + ";hyperlink=/cgi-bin/gx.cgi/GUIDGX-"
                    + parent.getGUID() + "?name=" + parent.getURLName()
                    + "&selected=" + (parent.isSelected() ? "T" : "F"));
            if (parent.isSelected() && parent.hasChildren())
            {
                Vector subcategories = new Vector();
                Child[] children = parent.getChildren();

                for (int j = 0; j < children.length; j++)
                {

                    // hyperlink is changed to point to NotificationMessagesAL
                    String guidString = children[j].getGUID();

                    if (guidString != null)
                    {
                        if (guidString.indexOf("cgi-bin/gx.cgi") != -1)
                        {
                            subcategories
                                    .add("name=" + children[j].getName()
                                    + ";hyperlink=/cgi-bin/gx.cgi/GUIDGX-"
                                    + "{ac7481f0-5232-11d2-a724-204c4f4f5020}?nextColumn="
                                    + guidString);
                        }
                        else
                        {
                            subcategories.add("name=" + children[j].getName()
                                    + ";hyperlink=" + guidString);
                        }
                    }
                }
                menu.add(subcategories);
            }
        }
        return menu;
    }

    /**
     * Serialize Dynamic Menu
     * @param data
     * @return String
     */
    public static String serialize(DynMenuData data)
    {
        String serialized = null;
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;

        try
        {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(data);
            oos.reset();
            oos.flush();
            serialized = UU.encode(baos.toString());
        }
        catch (IOException e)
        {
            logger.error("I/O Exception serializing dynamic menu data.", e);
        }
        finally
        {
            try
            {
                oos.close();
            }
            catch (IOException ex)
            {
                logger.error("I/O Exception serializing dynamic menu data.", ex);
            }
        }
        return serialized;
    }

    /**
     * Deserialize Dynamic Menu Data
     * @param data
     * @return String
     */
    public static DynMenuData deserialize(String data)
    {
        DynMenuData dyn = null;

        try
        {
            String serialized = null;

            if (data != null)
            {
                serialized = UU.decode(data);
            }
            ByteArrayInputStream bais =
                    new ByteArrayInputStream(serialized.getBytes());
            ObjectInputStream ois = new ObjectInputStream(bais);

            dyn = (DynMenuData) ois.readObject();
            ois.reset();
        }
        catch (StreamCorruptedException e)
        {
            logger.error("Stream corrupted exception deserializing dynamic menu data.", e);
        }
        catch (IOException e)
        {
            logger.error("I/O exception deserializing dynamic menu data.", e);
        }
        catch (ClassNotFoundException e)
        {
            logger.error("Class not found exception deserializing dynamic menu data.", e);
        }
        catch (NullPointerException e)
        {
            logger.error("Null Pointer Exception deserializing dynamic menu data.", e);
        }
        return dyn;
    }

}
