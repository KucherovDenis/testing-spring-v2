package ru.denku.io;

import ru.denku.ApplicationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileLoader implements Loader {

    private static final String EXCEPTION_MESSAGE = "Не удалось загрузить файл ";

    private String fileName;

    private final File file;

    private final BufferedReader reader;

    public FileLoader(String fileName) {
        this.fileName = fileName;
        try {
            file = new File(getClass().getClassLoader().getResource(fileName).getFile());
        } catch (NullPointerException e) {
            throw new ApplicationException(EXCEPTION_MESSAGE + fileName, e);
        }

        BufferedReader tmp = null;
        try {
            tmp = new BufferedReader(new FileReader(getFile()));
        } catch (FileNotFoundException e) {
            throw new ApplicationException(EXCEPTION_MESSAGE + fileName, e);
        }
        reader = tmp;
    }

    public FileLoader() {
        file = null;
        reader = null;
    }

    protected File getFile() {
        return file;
    }

    protected BufferedReader getReader() {
        return reader;
    }

    @Override
    public List<String> load() {
        List<String> lines = new ArrayList<>();

        try {
            String line;
            while ((line = getReader().readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            throw new ApplicationException(EXCEPTION_MESSAGE + fileName, e);
        }
    }
}
