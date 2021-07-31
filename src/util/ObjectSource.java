package util;

import java.io.*;

/**
 * Read objects from a file
 */
public class ObjectSource {
    private File file;
    private ObjectInputStream instream;

    public ObjectSource(String filename)  {
        file = new File(filename);
        try {
            instream = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public Object readObject() {
        Object object = null;
        try {
            object = instream.readObject();
        } catch (EOFException eofException) {
            // Do nothing, EOF is expected to happen eventually
        } catch (IOException error) {
            error.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        return object;
    }

    public void close() {
        try {
            instream.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
