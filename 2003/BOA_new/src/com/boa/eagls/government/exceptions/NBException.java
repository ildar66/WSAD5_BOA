/**
 * NBException
 */
package com.boa.eagls.government.exceptions;

/**
 * This class holds all information about an application error encountered in EAGLS.
 * @version    2.0, 07/06/1998
 */

import java.lang.reflect.Field;

/**
 * Class declaration
 *
 *
 * @author
 * @version %I%, %G%
 */
public abstract class NBException extends Exception
{

    // Holds the severity of the exception
    protected short severity;
    protected String message;
    protected String detailedMessage;
    private int beginIndex = 0;
    private int endIndex = 9;

    /**
     * Public constructor to instantiate a exception without information.
     */
    public NBException()
    {
        super();
        setMessage("");
        setDetailedMessage("");
    }

    /**
     * Public constructor which instantiates an exception without
     * severity.  All possible messages are located in 'Messages.java'
     * @param   s      the error string
     */
    public NBException(String aMessage)
    {
        if (aMessage == null)
        {
            aMessage =
                    "An unidentified exception has occured, Please contact Administrator for further details.";
        }
        setException(aMessage);
    }

    /**
     * Method declaration
     *
     *
     * @param aMessage
     */
    protected void setException(String aMessage)
    {

        // Variable declarations.
        Field messageAttribute;
        Field severityAttribute;

        setDetailedMessage(aMessage);

        // Parse the message code from the incoming error message.
        String messageCode = aMessage.substring(beginIndex,
                endIndex).replace('-', '_');

        // Get the attribute corresponding to the message code from
        // the 'Messages.java' class.
        Messages messages = new Messages();
        Class messagesClass = messages.getClass();

        try
        {

            // Get the message.
            messageAttribute = messagesClass.getField(messageCode);
            setMessage((String) messageAttribute.get(messages));
        }
        catch (NoSuchFieldException e)
        {
            setMessage("");
        }
        catch (IllegalAccessException e)
        {
            setMessage("");
        }

        // Get the severity.
        String severityCode = messageCode + "_Severity";

        try
        {

            // Get the attribute corresponding to the severity code from
            // the 'Messages.java' class.
            severityAttribute = messagesClass.getField(severityCode);
            setSeverity(severityAttribute.getShort(messages));
        }
        catch (NoSuchFieldException e)
        {
            setSeverity((short) 0);
        }
        catch (IllegalAccessException e)
        {
            setSeverity((short) 0);
        }
    }

    /**
     * Public constructor which instantiates an exception with a
     * message and a severity.  All possible messages are located in 'Messages.java'
     * @param   s      the error string
     */
    public NBException(String aMessage, short aSeverity)
    {
        setMessage(aMessage);
        setSeverity(aSeverity);
    }

    /**
     * Method declaration
     *
     *
     * @param aMessage
     */
    public void setMessage(String aMessage)
    {
        if (aMessage != null)
        {
            message = aMessage;
        }
        else
        {
            message = "";
        }
    }

    /**
     * Method declaration
     *
     *
     * @param aDetailedMessage
     */
    public void setDetailedMessage(String aDetailedMessage)
    {
        if (aDetailedMessage != null)
        {
            detailedMessage = aDetailedMessage;
        }
        else
        {
            detailedMessage = "";
        }
    }

    /**
     * Method declaration
     *
     *
     * @param aSeverity
     */
    public void setSeverity(short aSeverity)
    {
        severity = aSeverity;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getDetailedMessage()
    {
        return detailedMessage;
    }

    /**
     * Returns the severity of the exception.
     */
    public short getSeverity()
    {
        return severity;
    }

}
