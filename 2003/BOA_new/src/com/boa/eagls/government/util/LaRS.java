/**
 * LaRS
 */
package com.boa.eagls.government.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.io.*;
import java.util.*;

public class LaRS implements Serializable
{
    /**
     * FILEPATH holds the location on the local file system where LaRS .dat
     * and .mod files will be stored.  Set this with setFilePath and
     * getFilePath before using LaRS.
     */
    protected static String FILEPATH = "";
    private static final Logger logger= Logger.getLogger(LaRS.class);

    String _dataFileName;
    String _modFileName;
    String _userID;
    int _queryID;
    Vector _resultSet;
    long _timeOfLastAccess;
    int[][] _index;
    int _indexPos, _indexSize;
    int _numberOfRecords;
    Object[] _modifiableStorage;
    Object _defaultValue;

    public LaRS()
    {
    }

    /**
     * Constructor that creates a LaRS object with an
     * userID as a file path indicator and a queryID as a form of validation
     * AppLogic ID is no longer Needed
     * Parmaters:
     * <ul>
     * <li>userID
     * <li>queryID
     *
     * @param userID Used to constructor file path of LaRS object
     * @param queryID Created when LaRS is created to validate for future use
     */

    public LaRS(String userID, int queryID)
    {
        if (LaRS.FILEPATH == null)
        {
            throw new IllegalStateException(getClass().getName() + ":LaRS.FILEPATH not set");
        }

        _userID = userID;
        _queryID = queryID;
        _resultSet = new Vector();
        _timeOfLastAccess = System.currentTimeMillis();
        _dataFileName = _userID + ".dat";
        _modFileName = _userID + ".mod";
        _numberOfRecords = 0;
        _modifiableStorage = null;


        // Check LaRS file path exists; create it if not.
        File file = new File(FILEPATH);
        if (!file.exists())
        {
            file.mkdirs();
        }

        // Delete any existing LaRS files with this userID
        file = new File(FILEPATH, _dataFileName);
        if (file.exists())
        {
            file.delete();
        }

        file = new File(FILEPATH, _modFileName);
        if (file.exists())
        {
            file.delete();
        }
    }

    //DEPRECATE  - please use constructor with appLogicID
    public LaRS(String userID, String appLogicID, int queryID)
    {
        if (LaRS.FILEPATH == null)
        {
            throw new IllegalStateException(getClass().getName() + ":LaRS.FILEPATH not set");
        }

        _userID = userID;
        _queryID = queryID;
        _resultSet = new Vector();
        _timeOfLastAccess = System.currentTimeMillis();
        _dataFileName = _userID + ".dat";
        _modFileName = _userID + ".mod";
        _numberOfRecords = 0;
        _modifiableStorage = null;


        File file = new File(FILEPATH);
        if (!file.exists())
        {
            file.mkdirs();
        }

        // Delete any existing LaRS files with this userID
        file = new File(FILEPATH, _dataFileName);
        if (file.exists())
        {
            file.delete();
        }

        file = new File(FILEPATH, _modFileName);
        if (file.exists())
        {
            file.delete();
        }
    }

    public LaRS(LaRS in_lars)
    {
        _dataFileName = in_lars._dataFileName;
        _modFileName = in_lars._modFileName;
        _userID = in_lars._userID;
        _queryID = in_lars._queryID;
        _resultSet = in_lars._resultSet;
        _timeOfLastAccess = in_lars._timeOfLastAccess;
        _index = in_lars._index;
        _indexPos = in_lars._indexPos;
        _indexSize = in_lars._indexSize;
        _numberOfRecords = in_lars._numberOfRecords;
        _modifiableStorage = in_lars._modifiableStorage;
        _defaultValue = in_lars._defaultValue;
    }


    /**
     * Method that validates that LaRS used is validate after retrieving it.
     * queryID is used to do match.  If the query ID that is kept by objects/html
     * is the same, then it is valid.
     * AppLogic ID is no longer Needed
     * Parmaters:
     * <ul>
     * <li>queryID
     *
     * @param queryID Created when LaRS is created to validate for future use
     */

    public boolean validate(int queryID)
    {
        File file = new File(FILEPATH, _dataFileName);
        return (file.exists() && _queryID == queryID);
    }

    public void writeRecord(Object record)
    {
        _resultSet.addElement(record);
    }

    public void finishedWriting()
    {
        _numberOfRecords = _resultSet.size();
        _index = new int[_numberOfRecords][2];

        File file = new File(FILEPATH, _dataFileName);
        RandomAccessFile raFile = null;
        try
        {

            raFile = new RandomAccessFile(file, "rw");
            byte[] serialized;
            int pos = 0;
            for (int i = 0; i < _numberOfRecords; i++)
            {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(_resultSet.elementAt(i));
                oos.flush();
                serialized = baos.toByteArray();
                _index[i][0] = pos;
                _index[i][1] = serialized.length;
                pos += serialized.length;
                raFile.write(serialized);
                baos.reset();
                oos.close();
                baos.close();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(_index);
            oos.flush();
            serialized = baos.toByteArray();
            _indexPos = pos;
            _indexSize = serialized.length;
            pos += serialized.length;
            raFile.write(serialized);
            baos.reset();
            oos.close();
            baos.close();
            _index = null;
        }
        catch (IOException e)
        {
            logger.error("LaRS.finishedWriting::I/O Exception serializing LaRS:",e);
        }
        if (raFile != null)
        {
            try
            {
                raFile.close();
            }
            catch (IOException e)
            {
            }
        }
        _resultSet.removeAllElements();
    }

    public int numberOfRecords()
    {
        return _numberOfRecords;
    }

    public int numberOfPages(int recordsPerPage)
    {
        return (numberOfRecords() + recordsPerPage - 1) / recordsPerPage;
    }

    public int currentPage(int currentRecord, int recordsPerPage)
    {
        return (currentRecord + recordsPerPage) / recordsPerPage;
    }

    public Object[] readRecords(int startRecord, int numberRecords)
    {
        if ((startRecord + numberRecords) > numberOfRecords())
        {
            numberRecords = numberOfRecords() - startRecord;
        }

        if (numberRecords < 0)
        {
            numberRecords = 0;
        }

        Object[] objectArray = new Object[numberRecords];
        File file = new File(FILEPATH, _dataFileName);
        RandomAccessFile raFile = null;
        try
        {
            byte[] serialized = new byte[_indexSize];
            raFile = new RandomAccessFile(file, "r");
            raFile.seek(_indexPos);
            raFile.read(serialized);

            ByteArrayInputStream bais = new ByteArrayInputStream(serialized);
            ObjectInputStream ois = new ObjectInputStream(bais);
            _index = (int[][]) ois.readObject();
            bais.reset();
            ois.close();

            for (int i = 0; i < numberRecords; i++)
            {
                serialized = new byte[_index[startRecord + i][1]];
                raFile.seek(_index[startRecord + i][0]);
                raFile.read(serialized);

                bais = new ByteArrayInputStream(serialized);
                ois = new ObjectInputStream(bais);
                objectArray[i] = ois.readObject();

                bais.reset();
                ois.close();
            }

            _index = null;

        }
        catch (Exception e)
        {
            logger.error(getClass().getName() + ":Exception caught reading objects from file", e);
            return new Object[0];
        }
        finally
        {
            // Be sure to close the data file.
            if (raFile != null)
            {
                try
                {
                    raFile.close();
                }
                catch (IOException e)
                {
                }
            }
        }

        // Record a time stamp.
        _timeOfLastAccess = System.currentTimeMillis();
        return objectArray;
    }


    public Object readRecord(int position)
    {
        if (position > numberOfRecords() || position < 0)
        {
            return null;
        }

        Object o = null;

        File file = new File(FILEPATH, _dataFileName);
        RandomAccessFile raFile = null;
        try
        {
            byte[] serialized = new byte[_indexSize];
            raFile = new RandomAccessFile(file, "r");
            raFile.seek(_indexPos);
            raFile.read(serialized);

            // retrieve index from end of file
            ByteArrayInputStream bais = new ByteArrayInputStream(serialized);
            ObjectInputStream ois = new ObjectInputStream(bais);
            _index = (int[][]) ois.readObject();
            bais.reset();
            ois.close();


            // Grab the bytes for this object.
            serialized = new byte[_index[position][1]];
            raFile.seek(_index[position][0]);
            raFile.read(serialized);

            // Deserialize the byte array into an object.
            bais = new ByteArrayInputStream(serialized);
            ois = new ObjectInputStream(bais);
            o = ois.readObject();

            ois.close();
            bais.close();

            _index = null;

        }
        catch (Exception e)
        {
            logger.error(":Exception caught reading single object from file", e);
            return new Object[0];
        }
        finally
        {
            // Be sure to close the data file.
            if (raFile != null)
            {
                try
                {
                    raFile.close();
                }
                catch (IOException e)
                {
                }
            }
        }

        // Record a time stamp.
        _timeOfLastAccess = System.currentTimeMillis();
        return o;
    }

    public Date timeOfLastAccess()
    {
        return new Date(_timeOfLastAccess);
    }

    public void createModifiableStorage(Object defaultValue)
    {
        File file = new File(FILEPATH, _modFileName);
        if (file.exists())
        {
            file.delete();
        }
        _defaultValue = defaultValue;

        try
        {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (int i = 0; i < numberOfRecords(); i++)
            {
                oos.writeObject(defaultValue);
            }
            oos.flush();
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            logger.error("I/O Exception creating modifiable storage space file", ioe);
        }
    }

    public void loadModifiableStorage()
    {
        if (_modifiableStorage == null)
        {
            File file = new File(FILEPATH, _modFileName);

            try
            {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);

                _modifiableStorage = new Object[numberOfRecords()];

                for (int i = 0; i < _modifiableStorage.length; i++)
                {
                    _modifiableStorage[i] = ois.readObject();
                }
                ois.close();
                fis.close();
            }
            catch (FileNotFoundException fnfe)
            {
                logger.error("File " + _modFileName + " not found for modifiable storage space - recreating file (data modifications will be lost)", fnfe);
                createModifiableStorage(_defaultValue);
                loadModifiableStorage();
            }
            catch (StreamCorruptedException sce)
            {
                logger.error("Stream Corrupted Exception deserializing modifiable storage space", sce);
            }
            catch (IOException ioe)
            {
                logger.error("I/O Exception deserializing modifiable storage space", ioe);
            }
            catch (ClassNotFoundException cnfe)
            {
                logger.error("Class Not Found Exception deserializing modifiable storage space", cnfe);
            }
        }

    }

    public void updateModifiableStorageEntry(int position, Object newValue)
    {
        _modifiableStorage[position] = newValue;
    }

    public Object retrieveModifiableStorageEntry(int position)
    {
        return _modifiableStorage[position];
    }

    public void saveModifiableStorage()
    {
        File file = new File(FILEPATH, _modFileName);
        if (file.exists())
        {
            file.delete();
        }

        try
        {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (int i = 0; i < _modifiableStorage.length; i++)
            {
                oos.writeObject(_modifiableStorage[i]);
            }
            oos.flush();
            oos.close();
            fos.close();
            _modifiableStorage = null;
        }
        catch (IOException ioe)
        {
            logger.error("I/O Exception updating modifiable storage space file", ioe);

            StringBuffer sb = new StringBuffer("APP_I00001:EAGLSBaseAL.execute::");
            sb.append("An exception was caught with the following stack trace:<PRE>\n");
            StringWriter sw = new StringWriter();
            ioe.printStackTrace(new PrintWriter(sw));
            sb.append(sw.getBuffer());
            sb.append("\n</PRE>\n");
            logger.error(sb.toString());

        }

    }

    public static byte[] serialize(LaRS resultSet)
    {
        // String serialized=null;

        byte[] serialized = null;

        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try
        {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);

            oos.writeObject(resultSet);
            oos.flush();

            // serialized=UU.encode(baos.toString());
            serialized = baos.toByteArray();

        }
        catch (IOException e)
        {
            logger.error("LaRS.serialize:Caught exception while serializing LaRS object", e);
            return null;
        }
        finally
        {
            try
            {
                if (oos != null)
                {
                    oos.close();
                }
                if (baos != null)
                {
                    baos.close();
                }
            }
            catch (IOException e)
            {
            }
        }

        return serialized;
    }

    public static LaRS deserialize(byte[] serializedResultSet)
    {
        LaRS resultSet = null;

        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try
        {
            //  String serialized=UU.decode(serializedResultSet);

            //  bais=new ByteArrayInputStream(serialized.getBytes());
            bais = new ByteArrayInputStream(serializedResultSet);
            ois = new ObjectInputStream(bais);

            resultSet = (LaRS) ois.readObject();

            ois.close();
            bais.close();

        }
        catch (Exception e)
        {
            logger.error("LaRS.deserialize:Caught exception while deserializing LaRS object", e);
            return null;
        }
        finally
        {
            try
            {
                if (ois != null)
                {
                    ois.close();
                }
                if (bais != null)
                {
                    bais.close();
                }
            }
            catch (IOException ioe)
            {
            }
        }

        return resultSet;
    }


    /**
     * Set the global path to the LaRS data files.
     *
     * @param	path	a valid file path;
     */
    public static void setFilePath(String path)
    {
        if (path == null)
        {
            FILEPATH = "";
        }
        else
        {
            FILEPATH = path;
        }
    }

    /**
     * Get the global path to the LaRS data files.
     *
     * @return	a file path.
     */
    public static String getFilePath()
    {
        return FILEPATH;
    }

    public int getQueryID()
    {
        return _queryID;
    }

}
