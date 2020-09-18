package by.kvach.lightBase.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rp.com.google.common.io.BaseEncoding;

import java.io.File;
import java.nio.charset.StandardCharsets;

//ReportPortalLog4j2Appender.class
public class LogUtils {

    private static final Logger LOGGER = LogManager.getLogger("binary_data_logger");
    private static LogUtils instance;

    public static LogUtils getInstance() {
        if (instance == null) {
            instance = new LogUtils();
        }
        return instance;
    }

    public void logInfo(String message) {
        LOGGER.info(message, StandardCharsets.UTF_8);
    }

    public void logInfo(File file, String message) {
        LOGGER.info("RP_MESSAGE#FILE#" + file + "#" + message);
    }

    public void logInfo(byte[] bytes, String message) {
        LOGGER.info("RP_MESSAGE#BASE64#" + BaseEncoding.base64().encode(bytes) + "#" + message);
    }

    public void logError(File file, String message) {
        LOGGER.error("RP_MESSAGE#FILE#" + file + "#" + message);
    }

}
