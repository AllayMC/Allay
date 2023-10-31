package cn.allay.api.utils;

import cn.allay.api.command.exception.ArgumentParseException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class QuotedStringTokenizer {

    private static final int BACKSLASH = '\\';
    private static final int SINGLE_QUOTE = '\'';
    private static final int DOUBLE_QUOTE = '"';

    public static List<String> parse(String arguments) throws ArgumentParseException {
        if (arguments.isEmpty()) return Collections.emptyList();

        var state = new TokenizerState(arguments);
        List<String> returnedArgs = new ArrayList<>();
        while (state.hasMore()) {
            skipWhiteSpace(state);
            returnedArgs.add(nextArg(state));
        }

        return returnedArgs;
    }

    private static void skipWhiteSpace(TokenizerState state) throws ArgumentParseException {
        if (!state.hasMore()) return;

        if (Character.isWhitespace(state.peek())) state.next();
    }

    private static String nextArg(TokenizerState state) throws ArgumentParseException {
        var argBuilder = new StringBuilder();
        if (state.hasMore()) {
            var codePoint = state.peek();
            if (codePoint == DOUBLE_QUOTE || codePoint == SINGLE_QUOTE) {
                parseQuotedString(state, codePoint, argBuilder);
            } else {
                parseUnquotedString(state, argBuilder);
            }
        }

        return argBuilder.toString();
    }

    private static void parseQuotedString(TokenizerState state, int startQuotation, StringBuilder builder) throws ArgumentParseException {
        var nextCodePoint = state.next();
        if (nextCodePoint != startQuotation) {
            throw new ArgumentParseException(
                    String.format("Actual next character '%c' did not match expected " +
                                  "quotation character '%c'", nextCodePoint, startQuotation),
                    state.buffer, state.index);
        }

        while (true) {
            if (!state.hasMore()) return;

            nextCodePoint = state.peek();
            if (nextCodePoint == startQuotation) {
                state.next();
                return;
            } else if (nextCodePoint == BACKSLASH) {
                parseEscape(state, builder);
            } else {
                builder.appendCodePoint(state.next());
            }
        }
    }

    private static void parseUnquotedString(TokenizerState state, StringBuilder builder) throws ArgumentParseException {
        while (state.hasMore()) {
            var nextCodePoint = state.peek();
            if (Character.isWhitespace(nextCodePoint)) {
                return;
            } else if (nextCodePoint == BACKSLASH) {
                parseEscape(state, builder);
            } else {
                builder.appendCodePoint(state.next());
            }
        }
    }

    private static void parseEscape(TokenizerState state, StringBuilder builder) throws ArgumentParseException {
        state.next(); // Consume \
        builder.appendCodePoint(state.next());
    }

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    private static class TokenizerState {

        private final String buffer;
        private int index = -1;

        public boolean hasMore() {
            return this.index + 1 < this.buffer.length();
        }

        public int peek() throws ArgumentParseException {
            if (!this.hasMore())
                throw new ArgumentParseException("Buffer overrun while parsing args", this.buffer, this.index);
            return this.buffer.codePointAt(this.index + 1);
        }

        public int next() throws ArgumentParseException {
            if (!this.hasMore())
                throw new ArgumentParseException("Buffer overrun while parsing args", this.buffer, this.index);
            return this.buffer.codePointAt(++this.index);
        }
    }
}
