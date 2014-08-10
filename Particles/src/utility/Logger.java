package utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Utility methods for logging.
 * @author Valkryst
 * --- Last Edit 07-August-2014
 */
public class Logger {
    private final static String verboseLog = "log.txt"; // Contains verbose logs.
    private final static String errorLog = "error_log.txt"; // Contains warnings & errors.
    public final static int LOG_TYPE_VERBOSE = 0, LOG_TYPE_WARNING = 1, LOG_TYPE_ERROR = 2;

    /**
     * Appends the specified string to a log file
     * along with a timestamp in yyyy/MMM/dd-HH:mm:ss
     * format. The log file that it will be appended
     * to depends on the LogType.
     * @param LOG_MESSAGE The string to append to the log file.
     */
    public static void writeLog(final String LOG_MESSAGE, final int LOG_TYPE) {
        try {
            // Create the printwriter
            PrintWriter out;
            switch(LOG_TYPE) {
                case LOG_TYPE_VERBOSE: {
                    out = new PrintWriter(new BufferedWriter(new FileWriter(verboseLog, true)));
                    break;
                }
                default: {
                    out = new PrintWriter(new BufferedWriter(new FileWriter(errorLog, true)));
                    break;
                }
            }

            // Get the timestamp and then append the log to the file.
            String timeStamp = new SimpleDateFormat("yyyy/MMM/dd-HH:mm:ss").format(Calendar.getInstance().getTime());
            out.println("{" + timeStamp + "}--LogType|" + getLogTypeAsString(LOG_TYPE) + ": " + LOG_MESSAGE);
            out.close();

            // Write every error log message to the terminal as well.
            System.out.println(LOG_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes in the ID number of a log-type and returns the
     * name of the specified log-type, as a String, if it exists.
     * @param LOG_TYPE The ID number of the log-type to get the name for.
     * @return The name of the specified log-type.
     */
    private static String getLogTypeAsString(final int LOG_TYPE) {
        switch(LOG_TYPE) {
            case LOG_TYPE_VERBOSE:
                return "VERBOSE";
            case LOG_TYPE_WARNING:
                return "WARNING";
            case LOG_TYPE_ERROR:
                return "ERROR";
            default:
                return "UNKNOWN";
        }
    }
}
