package com.ykbt.customcheckstyle.checks.exception.notthrownexception;

import java.io.FileReader;
import java.io.IOException;

public class SimpleThrownFormat
{
    private void throwIoException() {
        try {
            FileReader in = new FileReader("abc.txt");
        } catch (IOException ioe) {
            System.out.println(ioe.getStackTrace());
        } finally {
        }
    }

    private void throwNullPointerException() {
        try {
            Object obj = null;
            obj.toString();
        } catch (NullPointerException npe) {
            System.out.println(npe.getStackTrace());
        } finally {
        }
    }
}
