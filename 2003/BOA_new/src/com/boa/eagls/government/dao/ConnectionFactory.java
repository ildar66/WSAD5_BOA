/**
 * Connection Factory Class
 */
package com.boa.eagls.government.dao;

import java.io.*;
import java.util.*;

import org.apache.log4j.*;

/**
 * <p>Description: This class will be used to create Database Connections</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @version 1.0
 * @invariant $none
 */
public class ConnectionFactory {
    private static final Logger LOGGER =
            Logger.getLogger("com.boa.eagls.government.dao.ConnectionFactory.class");

    /**
     * @param $none
     * @return Object An instance of the Connection wraper class specified in properties file
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @pre $none
     * @post $none
     */
    public static IConnectionService getInstance()
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        LOGGER
                .debug("Entering getInstance() method of ConnectionFactory class ");
        IConnectionService object = null;
        String connectionType = new ConnectionFactory().loadConnectionType();

        if (connectionType != null) {
            object = (IConnectionService) Class.forName(connectionType).newInstance();
        }
        LOGGER
                .debug("Exiting getInstance() method of ConnectionFactory class, Return value "
                + object);
        return object;
    }

    /**
     * @return String
     * @pre $none
     * @post $none
     */
    private String loadConnectionType() {
        LOGGER
                .debug("Entering loadConnectionType() method of ConnectionFactory class ");
        Properties prop = new Properties();
        InputStream is = null;
        String result = null;

        try {
            is = new FileInputStream(getClass().getResource("/eagls.properties").getFile());
            prop.load(is);
            result = prop.getProperty("connectionClass");
        } catch (FileNotFoundException ex) {
            LOGGER.error("File not found in loadConnectionType() method of ConnectionFactory class.",ex);
            result = null;
        } catch (IOException ex1) {
            LOGGER.error("IO Exception occured in loadConnectionType() method of ConnectionFactory class.",ex1);
            result = null;
        }
        LOGGER.debug("Exiting loadConnectionType() method of ConnectionFactory class, Return value is= "+ result);
        return result;
    }

}
