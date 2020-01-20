package com.ykbt.customcheckstyle.checks.exception;

import com.puppycrawl.tools.checkstyle.AbstractModuleTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NotThrownExceptionCheckTest extends AbstractModuleTestSupport {

    @Override
    protected String getPackageLocation() {
        return "com/ykbt/customcheckstyle/checks/exception/notthrownexception";
    }

    @Test
    public void testGetRequiredTokens() {
        final NotThrownExceptionCheck checkObj = new NotThrownExceptionCheck();
        final int[] expected = {TokenTypes.LITERAL_CATCH};
        Assertions.assertArrayEquals(expected, checkObj.getRequiredTokens(),"Default required tokens are invalid");
    }

    @Test
    public void testSpecified()
            throws Exception {
        final DefaultConfiguration checkConfig =
                createModuleConfig(NotThrownExceptionCheck.class);
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
                createModuleConfig(NotThrownExceptionCheck.class);
        checkConfig.addAttribute("format", "NullPointerException");

        final String[] expected = {
                "21:11: " + getCheckMessage(NotThrownExceptionCheck.MGS_NOT_THROWN, "NullPointerException"),
        };
        verify(checkConfig, getPath("SimpleThrownFormat.java"), expected);
    }
}
