package beehive;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Logger
{
    private PrintStream logFileStream;

    public Logger(String fileName) throws FileNotFoundException {
        this.logFileStream = new PrintStream(fileName);
    }

    public void log(String text)
    {
        logFileStream.println(text);

    }
    public void close()
    {
        logFileStream.close();
    }
}