package beehive;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Logger writes logs to a text file.
 * 
 *
 */
public class Logger
{
    /**
     * PrintStream object to write to file.
     */
    private PrintStream logFileStream;

    /**
     * Creates Logger object.
     * @param fileName Name of the file.
     * @throws FileNotFoundException Throws exception if file is not found.
     */
    public Logger(String fileName) throws FileNotFoundException {
        this.logFileStream = new PrintStream(fileName);
    }

    /**
     * Writes message to text file.
     * @param text Text to be written to a file.
     */
    public void log(String text)
    {
        logFileStream.println(text);

    }
    /**
     * Closes the file.
     */
    public void close()
    {
        logFileStream.close();
    }
}