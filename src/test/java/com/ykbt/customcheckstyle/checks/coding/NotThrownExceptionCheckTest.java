package com.ykbt.customcheckstyle.checks.coding;

import com.puppycrawl.tools.checkstyle.BaseCheckTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class NotThrownExceptionCheckTest extends BaseCheckTestSupport {

    @Override
    protected String getPath(String filename) throws IOException {
        return (new File("src/test/resources/com/ykbt/customcheckstyle/checks/coding/notthrownexception/" + filename)).getCanonicalPath();
    }

    @Test
    public void testGetRequiredTokens() {
        final NotThrownExceptionCheck checkObj = new NotThrownExceptionCheck();
        final int[] expected = {TokenTypes.LITERAL_CATCH};
        Assertions.assertArrayEquals(expected, checkObj.getRequiredTokens(), "Default required tokens are invalid");
    }

    @Test
    public void testSpecified()
            throws Exception {
        final DefaultConfiguration checkConfig =
                createCheckConfig(NotThrownExceptionCheck.class);
        checkConfig.addAttribute("format", ".*");

        final String[] expected = {
                "21:11: " + getCheckMessage(NotThrownExceptionCheck.MGS_NOT_THROWN, "NullPointerException"),
        };
        verify(checkConfig, getPath("SimpleThrown.java"), expected);
    }

    @Test
    public void testSpecifiedFormat()
            throws Exception {
        final DefaultConfiguration checkConfig =
                createCheckConfig(NotThrownExceptionCheck.class);
        checkConfig.addAttribute("format", "NullPointerException");

        final String[] expected = {
                "21:11: " + getCheckMessage(NotThrownExceptionCheck.MGS_NOT_THROWN, "NullPointerException"),
        };
        verify(checkConfig, getPath("SimpleThrownFormat.java"), expected);
    }
}
