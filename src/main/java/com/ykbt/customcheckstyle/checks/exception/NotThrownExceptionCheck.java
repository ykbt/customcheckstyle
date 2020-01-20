package com.ykbt.customcheckstyle.checks.exception;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import lombok.Setter;

import java.util.regex.Pattern;

/**
 * Exceptionをcatchしているのに、
 * throwしていない場合をチェックする
 */
public class NotThrownExceptionCheck extends AbstractCheck {

    public static final String MGS_NOT_THROWN = "not.throw.even.after.catched";

    @Setter
    Pattern format;

    public NotThrownExceptionCheck() {
        // default .*
        format = Pattern.compile(".*");
    }

    @Override
    public int[] getDefaultTokens() {
        return this.getRequiredTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return this.getRequiredTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[]{TokenTypes.LITERAL_CATCH};
    }

    @Override
    public void visitToken(DetailAST ast) {
        // 対象となるexceptionか判定する
        DetailAST type = ast.findFirstToken(TokenTypes.PARAMETER_DEF)
                .findFirstToken(TokenTypes.TYPE)
                .getFirstChild();
        if (type == null) return;

        // 対象となるExceptionをキャッチしたのみに絞る
        if (this.format.matcher(type.getText()).find()) {
            // throwがあるかないかを確認する
            if (ast.findFirstToken(TokenTypes.SLIST)
                    .findFirstToken(TokenTypes.LITERAL_THROW) == null) {
                log(ast, MGS_NOT_THROWN, type.getText());
            }
        }
    }
}
