/**
 * ColumnData
 */
package com.boa.eagls.government.util;

import com.boa.eagls.government.exceptions.EAGLSException;

/**
 * Utility class to hold column data returned from database table.
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public class ColumnData
{
    private Object value;
    private String paramType;
    private String columnName;
    private Object paramDataType;

    /**
     * Public constructor
     */
    public ColumnData()
    {
        value = null;
        paramType = null;
        columnName = null;
        paramDataType = null;
    }

    /**
     * Overloaded Constructor
     * @param colName
     * @param val
     * @param pType
     */
    public ColumnData(String colName, Object val, String pType)
    {
        this.columnName = colName;
        this.value = val;
        this.paramType = pType;
        this.paramDataType = "";
    }

    /**
     * Copy Constructor
     * @param colData
     */
    public ColumnData(ColumnData colData)
    {
        this.value = colData.getValue();
        this.paramType = colData.getParamType();
        this.columnName = colData.getColumnName();
        this.paramDataType = colData.getParamDataType();
    }

    /**
     * Overloaded Constructor
     * @param colName
     * @param value
     * @param pType
     * @param pDType
     */
    public ColumnData(String colName, Object value, String pType,
                      Object pDType)
    {
        this.columnName = colName;
        this.value = value;
        this.paramType = pType;
        this.paramDataType = pDType;
    }

    /**
     * getValue
     * @return Object
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * setValue
     * @param value
     */
    public void setValue(Object value)
    {
        this.value = value;
    }

    /**
     * getParamType
     * @return String
     */
    public String getParamType()
    {
        return paramType;
    }

    /**
     * setParamType
     * @param paramType
     * @throws EAGLSException
     */
    public void setParamType(String paramType) throws EAGLSException
    {
        if ((paramType.equals(SQLConstants.INPUT_PARAMETER))
                || (paramType.equals(SQLConstants.OUTPUT_PARAMETER))
                || (paramType.equals(SQLConstants.INPUT_OUTPUT_PARAMETER)))
        {
            this.paramType = paramType;
        }
        else
        {
            throw new EAGLSException("unknown.parameter.type");
        }
    }

    /**
     * getColumnName
     * @return String
     */
    public String getColumnName()
    {
        return columnName;
    }

    /**
     * setColumnName
     * @param columnName
     */
    public void setColumnName(String columnName)
    {
        this.columnName = columnName;
    }

    /**
     * Clears all fields
     */
    public void clear()
    {
        value = "";
        paramType = "";
        columnName = "";
        paramDataType = "";
    }

    /**
     * getParamDataType
     * @return Object
     */
    public Object getParamDataType()
    {
        return paramDataType;
    }

    /**
     * setParamDataType
     * @param paramDataType
     */
    public void setParamDataType(Object paramDataType)
    {
        this.paramDataType = paramDataType;
    }

    /**
     * toString
     * @return
     */
    public String toString()
    {
        return "Param value" + value + ",Param Column=" + columnName;
    }

}
