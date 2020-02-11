package ru.denku.io;

import java.io.BufferedReader;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.denku.ApplicationException;

import static org.junit.Assert.*;

public class FileLoaderTest {


    @Test(expected = ApplicationException.class)
    public void thenFileIsNullShouldbeReturnException() {
        FileLoader loader = new FileLoader(null);
    }

    @Test(expected = ApplicationException.class)
    public void thenFileNotExistShouldbeReturnException() {
        FileLoader loader = new FileLoader("FileNotExist.txt");
    }

    private FileLoader loader = Mockito.spy(new FileLoader());

    private BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);

    @Before
    public void setUp() {
        Mockito.doReturn(bufferedReader).when(loader).getReader();
        Mockito.doCallRealMethod().when(loader).load();
    }

    @Test
    public void loadShouldbeReturnData() throws Exception {
        Mockito.when(bufferedReader.readLine()).thenReturn("line1").thenReturn("line2").thenReturn(null);

        String[] lines = new String[] {"line1", "line2"};
        String[] realLines = new String[0];
        realLines = loader.load().toArray(realLines);
        assertArrayEquals(lines, realLines);
    }

    @Test
    public void thenFileIsEmptyShouldbeReturnEmptyList() throws Exception {
        Mockito.when(bufferedReader.readLine()).thenReturn(null);

        List<String> list = loader.load();
        assertEquals(0, list.size());
    }
}