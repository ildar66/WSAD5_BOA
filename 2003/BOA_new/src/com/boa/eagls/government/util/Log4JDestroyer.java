package com.boa.eagls.government.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.MalformedURLException;
import java.net.URL;

/** This class is spesific to current developers enviroment in VDI company.
 * It needed to be used on WebSphere for correct stopping of <code>SOCKET_HUB<code>
 * {@link org.apache.log4j.net.SocketHubAppender SocketHubAppender} adaper, for example.
 * And there is no needs to use it in JBoss - <b>IT CAN BE DANGEROUS FOR LOGGING IN JGOSS</b>
 * -  so, a correspondent logic is consists in.<br>
 * <b>This class is used in the project becouse of testing needs in jeneral.</b><br>
 *
 * We should <em>safely</em> close and remove all  appenders in all the categories
 * including root contained in the  default hierachy when application is stopped.
 * <p>Some appenders such as {@link org.apache.log4j.net.SocketAppender}
 * and {@link org.apache.log4j.AsyncAppender} need to be closed before the
 * application exists. Otherwise, pending logging events might be lost and some resources can be unrealized too.
 *
 * <p><small> DVI Company, 22.07.2003 17:07:56</small></p>
 * @author romanso
 */
public class Log4JDestroyer implements ServletContextListener {
    private static final String DEFAULT_XML_CONFIGURATION_FILE = "log4j.xml";

    public void contextInitialized(ServletContextEvent event) {
        Logger.getLogger(Log4JDestroyer.class).info("BOA ServletContext created");
        if (!isCatalinaUsed(event.getServletContext())) {

            /** Search for the properties file log4j.properties in the CLASSPATH.  */
            String override = OptionConverter.getSystemProperty(LogManager.DEFAULT_INIT_OVERRIDE_KEY, null);

            // if there is no default init override, then get the resource
            // specified by the user or the default config file.
            if (override == null || "false".equalsIgnoreCase(override)) {

                String configurationOptionStr = OptionConverter.getSystemProperty(LogManager.DEFAULT_CONFIGURATION_KEY, null);

                String configuratorClassName = OptionConverter.getSystemProperty(LogManager.CONFIGURATOR_CLASS_KEY, null);

                URL url = null;

                // if the user has not specified the log4j.configuration
                // property, we search first for the file "log4j.xml" and then
                // "log4j.properties"
                if (configurationOptionStr == null) {
                    url = Loader.getResource(DEFAULT_XML_CONFIGURATION_FILE);
                    if (url == null) {
                        url = Loader.getResource(LogManager.DEFAULT_CONFIGURATION_FILE);
                    }
                } else {
                    try {
                        url = new URL(configurationOptionStr);
                    } catch (MalformedURLException ex) {
                        // so, resource is not a URL:
                        // attempt to get the resource from the class path
                        url = Loader.getResource(configurationOptionStr);
                    }
                }

                // If we have a non-null url, then delegate the rest of the
                // configuration to the OptionConverter.selectAndConfigure
                // method.
                if (url != null) {
                    LogLog.debug("Using URL [" + url + "] for automatic log4j configuration.");
                    OptionConverter.selectAndConfigure(url, configuratorClassName,
                            LogManager.getLoggerRepository());
                } else {
                    LogLog.debug("Could not find resource: [" + configurationOptionStr + "].");
                }
            }
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
        Logger.getLogger(Log4JDestroyer.class).info("BOA ServletContext will be destroyed");
        if (!isCatalinaUsed(event.getServletContext()))
            LogManager.shutdown();
    }

    private static final boolean isCatalinaUsed(ServletContext context) {
        return context.getAttribute("org.apache.catalina.resources") != null;
    }
}