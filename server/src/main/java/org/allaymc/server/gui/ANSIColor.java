/*
 * Copyright (c) 2019-2022 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.allaymc.server.gui;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * {@code ANSIColor} maps supported ANSI foreground sequences to the Swing colors used by the
 * console. Lookup matches the complete escape sequence and falls back to reset for unsupported
 * input; bold detection compares color values, so equal colors share that presentation decision
 * regardless of their originating code.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum ANSIColor {
    // Normal foreground constants select the palette used by plain ANSI codes;
    // their color values are also compared by isBoldColor when rendering console text.
    /**
     * Normal black foreground color.
     */
    BLACK("(0;)?30(0;)?m", Color.BLACK),
    /**
     * Normal red foreground color.
     */
    RED("(0;)?31(0;)?m", new Color(0xfff0524f)),
    /**
     * Normal green foreground color.
     */
    GREEN("(0;)?32(0;)?m", new Color(0xff5c962c)),
    /**
     * Normal yellow foreground color.
     */
    YELLOW("(0;)?33(0;)?m", new Color(0xffa68a0d)),
    /**
     * Normal blue foreground color.
     */
    BLUE("(0;)?34(0;)?m", new Color(0xff6cb6ff)),
    /**
     * Normal magenta foreground color.
     */
    MAGENTA("(0;)?35(0;)?m", new Color(0xffa771bf)),
    /**
     * Normal cyan foreground color.
     */
    CYAN("(0;)?36(0;)?m", new Color(0xff96d0ff)),
    /**
     * Normal white foreground color.
     */
    WHITE("(0;)?37(0;)?m", new Color(0xffbcbec4)),

    // Bold foreground constants provide the palette compared by isBoldColor;
    // equal RGB values can therefore classify normal-code colors as bold too.
    /**
     * Bold black foreground color.
     */
    B_BLACK("(0;)?(1;30|30;1)m", Color.BLACK),
    /**
     * Bold red foreground color.
     */
    B_RED("(0;)?(1;31|31;1)m", new Color(0xfff0524f)),
    /**
     * Bold green foreground color.
     */
    B_GREEN("(0;)?(1;32|32;1)m", new Color(0xff5c962c)),
    /**
     * Bold yellow foreground color.
     */
    B_YELLOW("(0;)?(1;33|33;1)m", new Color(0xffa68a0d)),
    /**
     * Bold blue foreground color.
     */
    B_BLUE("(0;)?(1;34|34;1)m", new Color(0xff3993d4)),
    /**
     * Bold magenta foreground color.
     */
    B_MAGENTA("(0;)?(1;35|35;1)m", new Color(0xffa771bf)),
    /**
     * Bold cyan foreground color.
     */
    B_CYAN("(0;)?(1;36|36;1)m", new Color(0xff00a3a3)),
    /**
     * Bold white foreground color.
     */
    B_WHITE("(0;)?(1;37|37;1)m", new Color(0xff808080)),

    /**
     * Console reset foreground color.
     */
    RESET("0m", WHITE.color);

    /**
     * Supported colors in enum order.
     */
    // VALUES supplies lookup order, PREFIX escapes the shared control prefix,
    // and PATTERN_MAP retains one compiled matcher for every supported foreground code.
    private static final ANSIColor[] VALUES = values();
    /**
     * Quoted ANSI escape sequence prefix.
     */
    private static final String PREFIX = Pattern.quote("\u001B[");
    /**
     * Compiled complete escape-sequence matchers.
     */
    private static final Map<ANSIColor, Pattern> PATTERN_MAP = new HashMap<>();

    // Compile all foreground-code matchers once so streamed console rendering
    // reuses the same patterns instead of compiling each incoming escape sequence.
    static {
        for (ANSIColor color : VALUES) {
            PATTERN_MAP.put(color, Pattern.compile(PREFIX + color.ANSICode));
        }
    }

    /**
     * Supported ANSI suffix regular expression.
     */
    private final String ANSICode;
    /**
     * Swing foreground color value.
     */
    private final Color color;

    /**
     * Returns whether the supplied color equals a bold palette color by RGB value. Equal
     * normal-palette values also return {@code true}; {@code null} fails because this operation
     * invokes {@code equals} on its argument.
     */
    public static boolean isBoldColor(Color color) {
        return color.equals(B_BLACK.color) ||
               color.equals(B_RED.color) ||
               color.equals(B_GREEN.color) ||
               color.equals(B_YELLOW.color) ||
               color.equals(B_BLUE.color) ||
               color.equals(B_MAGENTA.color) ||
               color.equals(B_CYAN.color) ||
               color.equals(B_WHITE.color);
    }

    /**
     * Returns the first palette entry whose complete escape-sequence pattern matches the supplied
     * code. Unsupported sequences return {@code RESET}; a {@code null} code is rejected by the
     * pattern matcher.
     */
    public static ANSIColor fromANSI(String code) {
        return Arrays.stream(VALUES)
                .filter(value -> PATTERN_MAP.get(value).matcher(code).matches())
                .findFirst()
                // Unsupported ANSI sequences reset the foreground instead of
                // keeping an unrecognized palette state for subsequent console text.
                .orElse(RESET);
    }
}
