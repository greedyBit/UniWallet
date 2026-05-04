package interfaces;

import exceptions.FileIOException;

public interface Exportable {
    void exportToFile(String path) throws FileIOException;
}