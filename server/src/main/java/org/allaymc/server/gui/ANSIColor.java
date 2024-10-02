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
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum ANSIColor {
    // Normal colors
    BLACK("(0;)?30(0;)?m", Color.BLACK),
    RED("(0;)?31(0;)?m", new Color(0xfff0524f)),
    GREEN("(0;)?32(0;)?m", new Color(0xff5c962c)),
    YELLOW("(0;)?33(0;)?m", new Color(0xffa68a0d)),
    BLUE("(0;)?34(0;)?m", new Color(0xff6cb6ff)),
    MAGENTA("(0;)?35(0;)?m", new Color(0xffa771bf)),
    CYAN("(0;)?36(0;)?m", new Color(0xff96d0ff)),
    WHITE("(0;)?37(0;)?m", new Color(0xffbcbec4)),

    // Bold colors
    B_BLACK("(0;)?(1;30|30;1)m", Color.BLACK),
    B_RED("(0;)?(1;31|31;1)m", new Color(0xfff0524f)),
    B_GREEN("(0;)?(1;32|32;1)m", new Color(0xff5c962c)),
    B_YELLOW("(0;)?(1;33|33;1)m", new Color(0xffa68a0d)),
    B_BLUE("(0;)?(1;34|34;1)m", new Color(0xff3993d4)),
    B_MAGENTA("(0;)?(1;35|35;1)m", new Color(0xffa771bf)),
    B_CYAN("(0;)?(1;36|36;1)m", new Color(0xff00a3a3)),
    B_WHITE("(0;)?(1;37|37;1)m", new Color(0xff808080)),

    RESET("0m", WHITE.color);

    private static final ANSIColor[] VALUES = values();
    private static final String PREFIX = Pattern.quote("\u001B[");
    private static final Map<ANSIColor, Pattern> PATTERN_MAP = new HashMap<>();

    static {
        for (ANSIColor color : VALUES) {
            PATTERN_MAP.put(color, Pattern.compile(PREFIX + color.ANSICode));
        }
    }

    private final String ANSICode;
    private final Color color;

    public static boolean isBoldColor(Color color) {
        return color.equals(B_BLACK.color)
                || color.equals(B_RED.color)
                || color.equals(B_GREEN.color)
                || color.equals(B_YELLOW.color)
                || color.equals(B_BLUE.color)
                || color.equals(B_MAGENTA.color)
                || color.equals(B_CYAN.color)
                || color.equals(B_WHITE.color);
    }

    public static ANSIColor fromANSI(String code) {
        return Arrays.stream(VALUES)
                .filter(value -> PATTERN_MAP.get(value).matcher(code).matches())
                .findFirst()
                .orElse(RESET);
    }
}
