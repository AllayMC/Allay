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
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Serial;
import java.net.URI;
import java.util.regex.Pattern;

/**
 * This class was based on this <a href="https://stackoverflow.com/a/6899478/5299903">code</a>
 *
 * @author daoge_cmd
 */
@Slf4j
public class ConsolePanel extends JTextPane {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final int MAX_DOCUMENT_CHARS = 200_000;
    private static final String LINK_ATTRIBUTE = "link";
    private static final Pattern LINK_PATTERN = Pattern.compile("(?i)\\bhttps?://[^\\s<>\"]+");

    private static Color colorCurrent = ANSIColor.RESET.getColor();
    int currentLength = 0; // Used to let ProgressBars work
    private String remaining = "";

    public ConsolePanel() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON1) {
                    return;
                }

                var uri = getLinkAt(e.getPoint());
                if (uri == null) {
                    return;
                }

                try {
                    openLink(uri);
                } catch (Exception ex) {
                    log.warn("Could not open link {}", uri, ex);
                }
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(getLinkAt(e.getPoint()) == null ? Cursor.TEXT_CURSOR : Cursor.HAND_CURSOR));
            }
        });
    }

    private static void openLink(URI uri) throws Exception {
        if (!Desktop.isDesktopSupported()) {
            return;
        }

        var desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            desktop.browse(uri);
        }
    }

    /**
     * Append the given string in the given color to the text pane
     *
     * @param color The color
     * @param text  The text
     */
    private void append(Color color, String text) {
        var attribute = new SimpleAttributeSet();
        StyleConstants.setForeground(attribute, color);
        StyleConstants.setBold(attribute, ANSIColor.isBoldColor(color));

        var len = getDocument().getLength();

        if (text.contains("\r")) {
            // We have a carriage, so we should be at the front of the line
            if (text.contains("\n")) {
                // We're good to normally add to the document
                try {
                    insertTextWithLinks(text, attribute);
                    trimDocument();
                } catch (BadLocationException e) {
                    log.error("Error while appending text to console", e);
                }

                currentLength = 0;
                return;
            }

            // There's no newline, we should erase our progress to the start
            try {
                getDocument().remove(len - currentLength, currentLength);
                insertTextWithLinks(text, attribute);
                trimDocument();
                currentLength = text.length();
            } catch (BadLocationException e) {
                log.error("Error while removing text from console, most likely has to do with printing weirdly", e);
            }
            return;
        }

        currentLength += text.length();

        try {
            insertTextWithLinks(text, attribute);
            trimDocument();
        } catch (BadLocationException e) {
            log.error("Error while appending text to console", e);
        }
    }

    private void insertTextWithLinks(String text, SimpleAttributeSet baseAttribute) throws BadLocationException {
        var matcher = LINK_PATTERN.matcher(text);
        int pos = 0;
        while (matcher.find()) {
            var linkStart = matcher.start();
            var linkEnd = trimLinkEnd(text, matcher.end());
            if (linkEnd <= linkStart) {
                continue;
            }

            insertText(text.substring(pos, linkStart), baseAttribute);
            var link = text.substring(linkStart, linkEnd);
            var linkAttribute = createLinkAttribute(baseAttribute, link);
            insertText(link, linkAttribute == null ? baseAttribute : linkAttribute);
            pos = linkEnd;
        }
        insertText(text.substring(pos), baseAttribute);
    }

    private void insertText(String text, SimpleAttributeSet attribute) throws BadLocationException {
        if (text.isEmpty()) {
            return;
        }

        getDocument().insertString(getDocument().getLength(), text, attribute);
    }

    private static SimpleAttributeSet createLinkAttribute(SimpleAttributeSet baseAttribute, String link) {
        var attribute = new SimpleAttributeSet(baseAttribute);
        try {
            attribute.addAttribute(LINK_ATTRIBUTE, URI.create(link));
        } catch (IllegalArgumentException e) {
            return null;
        }
        StyleConstants.setUnderline(attribute, true);
        return attribute;
    }

    private static int trimLinkEnd(String text, int end) {
        while (end > 0 && ".,;:!?)]}".indexOf(text.charAt(end - 1)) != -1) {
            end--;
        }
        return end;
    }

    private URI getLinkAt(Point point) {
        var position = viewToModel2D(point);
        if (position < 0) {
            return null;
        }

        var doc = (StyledDocument) getDocument();
        var element = doc.getCharacterElement(position);
        var attribute = element.getAttributes().getAttribute(LINK_ATTRIBUTE);
        return attribute instanceof URI uri ? uri : null;
    }

    private void trimDocument() {
        var doc = getDocument();
        var excess = doc.getLength() - MAX_DOCUMENT_CHARS;
        if (excess <= 0) return;
        try {
            doc.remove(0, excess);
        } catch (BadLocationException e) {
            log.warn("Error while trimming console output", e);
        }
    }

    /**
     * Extract the ANSI color codes from the string and add each part to the text pane
     *
     * @param text The text to parse
     */
    public void appendANSI(String text) { // convert ANSI color codes first
        int aPos = 0;   // current char position in addString
        int aIndex; // index of next Escape sequence
        int mIndex; // index of "m" terminating Escape sequence
        String tmpString;
        boolean stillSearching = true; // true until no more Escape sequences
        String addString = remaining + text;
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
                    tmpString = addString.substring(aPos, mIndex + 1);
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
