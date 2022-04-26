package file;

import util.Response;

import java.io.File;
import java.io.IOException;

public interface FileWorkerInterface {
    Response saveToCSV();
    boolean getFromCSV();
    void setPath(String path);
    boolean create(File file) throws IOException;
    boolean changeReadPermissions(File file);
    void changeWritePermissions(File file);
}
