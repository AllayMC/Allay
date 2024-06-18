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

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.Serial;

/**
 * Allay Project 2024/5/25
 *
 * @author daoge_cmd
 *
 * This class was based on this <a href="https://stackoverflow.com/a/6899478/5299903">code</a>
 */
@Slf4j
public class ConsolePanel extends JTextPane {

    @Serial
    private static final long serialVersionUID = 1L;

    private static Color colorCurrent = ANSIColor.RESET.getColor();
    private String remaining = "";
    int currentLength = 0; // Used to let ProgressBars work

    /**
     * Append the given string in the given color to the text pane
     *  @param c The color
     * @param s The text
     */
    private void append(Color c, String s) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
        int len = getDocument().getLength();

        if(s.contains("\r")) {
            // We have a carriage so we should be at the front of the line
            boolean haveNewline = s.contains("\n");
            if(haveNewline) {
                // We're good to normally add to the document
                try {
                    getDocument().insertString(len, s, aset);
                } catch (BadLocationException e) {
                    log.error("Error while appending text to console", e);
                }
                currentLength = 0;
                return;
            }

            // There's no newline, we should erase our progress to the start
            try {
                getDocument().remove(len-currentLength, currentLength);
                getDocument().insertString(len-currentLength, s, aset);
                currentLength = s.length();
            } catch (BadLocationException e) {
                log.error("Error while removing text from console, most likely has to do with printing weirdly", e);
            }
            return;
        }

        currentLength += s.length();

        try {
            getDocument().insertString(len, s, aset);
        } catch (BadLocationException e) {
            log.error("Error while appending text to console", e);
        }
    }

    /**
     * Extract the ANSI color codes from the string and add each part to the text pane
     *
     * @param s The text to parse
     */
    public void appendANSI(String s) { // convert ANSI color codes first
        int aPos = 0;   // current char position in addString
        int aIndex; // index of next Escape sequence
        int mIndex; // index of "m" terminating Escape sequence
        String tmpString;
        boolean stillSearching = true; // true until no more Escape sequences
        String addString = remaining + s;
        remaining = "";

        if (!addString.isEmpty()) {
            aIndex = addString.indexOf("\u001B"); // find first escape
            if (aIndex == -1) { // no escape/color change in this string, so just send it with current color
                append(colorCurrent, addString);
                return;
            }
            // otherwise There is an escape character in the string, so we must process it

            if (aIndex > 0) { // Escape is not first char, so send text up to first escape
                tmpString = addString.substring(0, aIndex);
                append(colorCurrent, tmpString);
                aPos = aIndex; // aPos is now at the beginning of the first escape sequence
            }


            // while there's text in the input buffer
            while (stillSearching) {
                mIndex = addString.indexOf("m", aPos); // find the end of the escape sequence
                if (mIndex < 0) { // the buffer ends halfway through the ansi string!
                    remaining = addString.substring(aPos);
                    stillSearching = false;
                    continue;
                } else {
                    tmpString = addString.substring(aPos, mIndex+1);
                    colorCurrent = ANSIColor.fromANSI(tmpString).getColor();
                }
                aPos = mIndex + 1;
                // now we have the color, send text that is in that color (up to next escape)

                aIndex = addString.indexOf("\u001B", aPos);

                if (aIndex == -1) { // if that was the last sequence of the input, send remaining text
                    tmpString = addString.substring(aPos);
                    append(colorCurrent, tmpString);
                    stillSearching = false;
                    continue; // jump out of loop early, as the whole string has been sent now
                }

                // there is another escape sequence, so send part of the string and prepare for the next
                tmpString = addString.substring(aPos, aIndex);
                aPos = aIndex;
                append(colorCurrent, tmpString);

            }
        }
    }
}
