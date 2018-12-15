package store.scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class StoreScannerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setIn(System.in);
        System.setOut(originalOut);
        System.setErr(originalErr);
    }



    @Test
    public void scenario1() {

        ByteArrayInputStream in = new ByteArrayInputStream("atv, atv, atv, vga".getBytes());
        System.setIn(in);

        StoreScanner.main(new String[1]);
        assertEquals("SKUs Scanned: \nTotal expected: $249.00\n",outContent.toString());
    }

    @Test
    public void scenario2() {

        ByteArrayInputStream in = new ByteArrayInputStream("atv, ipd, ipd, atv, ipd, ipd, ipd".getBytes());
        System.setIn(in);

        StoreScanner.main(new String[1]);
        assertEquals("SKUs Scanned: \nTotal expected: $2718.95\n",outContent.toString());
    }

    @Test
    public void scenario3() {

        ByteArrayInputStream in = new ByteArrayInputStream("mbp, vga, ipd".getBytes());
        System.setIn(in);

        StoreScanner.main(new String[1]);
        assertEquals("SKUs Scanned: \nTotal expected: $1949.98\n",outContent.toString());
    }
}