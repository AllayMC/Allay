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
 * {@code ConsolePanel} renders console text with ANSI foreground colors, progress-line replacement,
 * and clickable HTTP links. Callers append on the Swing event thread; the dashboard queues output
 * there before mutating the document. The document retains only a bounded trailing character
 * history.
 *
 * <p>
 * {@code remaining} buffers an unfinished escape sequence until another append supplies its
 * terminating character, preserving color controls split across output fragments.
 * {@code currentLength} tracks the replaceable progress suffix for carriage-return output.
 * {@code colorCurrent} is static, so foreground state is shared across panel instances rather than
 * isolated with the buffered fragment. Link matching runs separately on each inserted text fragment
 * and does not rebuild URLs split between appends.
 *
 * <p>
 * This class was based on this <a href="https://stackoverflow.com/a/6899478/5299903">code</a>.
 *
 * @author daoge_cmd
 */
@Slf4j
public class ConsolePanel extends JTextPane {

    /**
     * Swing serialization compatibility identifier.
     */
    @Serial
    // serialVersionUID preserves Swing serialization identity; MAX_DOCUMENT_CHARS
    // bounds retained history, while LINK_PATTERN and LINK_ATTRIBUTE identify
    // candidate URLs and carry parsed targets in the styled document.
    private static final long serialVersionUID = 1L;

    /**
     * Maximum retained document character count.
     */
    private static final int MAX_DOCUMENT_CHARS = 200_000;
    /**
     * Document attribute storing a link URI.
     */
    private static final String LINK_ATTRIBUTE = "link";
    /**
     * HTTP and HTTPS link candidate pattern.
     */
    private static final Pattern LINK_PATTERN = Pattern.compile("(?i)\\bhttps?://[^\\s<>\"]+");

    /**
     * {@code colorCurrent} retains the foreground across append calls and all panel instances. ANSI
     * escape processing replaces it before later text is inserted.
     */
    private static Color colorCurrent = ANSIColor.RESET.getColor();
    /**
     * {@code currentLength} tracks the replaceable progress suffix length. Carriage-return text
     * without a newline removes that suffix before inserting its replacement.
     */
    int currentLength = 0; // Used to let ProgressBars work
    /**
     * {@code remaining} holds an incomplete escape fragment until the next append. Its contents are
     * prepended before scanning, preserving a control sequence split across calls.
     */
    private String remaining = "";

    /**
     * Creates a styled console pane with link opening and hover-cursor listeners. Left-click opens
     * a stored URI when desktop browsing is available, while opening failures are logged without
     * propagating through the mouse event.
     */
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
                    // Browser failures leave the console usable; report the target
                    // instead of propagating an external desktop action through Swing dispatch.
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

    /**
     * Requests desktop browsing for the supplied URI when the platform supports it. Unsupported
     * desktop or browse capability does nothing; browser-launch failures propagate for the mouse
     * listener to report.
     */
    private static void openLink(URI uri) throws Exception {
        // Headless or unsupported desktop environments cannot launch a browser;
        // leave the displayed link intact and return without attempting desktop access.
        if (!Desktop.isDesktopSupported()) {
            return;
        }

        var desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            desktop.browse(uri);
        }
    }

    /**
     * Appends styled text or replaces the tracked progress suffix when a carriage return has no
     * newline. Carriage-return text containing a newline is appended normally and resets progress
     * tracking.
     *
     * <p>
     * The method inserts links and trims history after each document mutation. Location failures
     * are logged rather than propagated, so output or tracking can be partially changed. Callers
     * use the Swing event thread and preserve a valid progress suffix; trimming does not adjust
     * {@code currentLength}, so a later replacement can request a range already removed from the
     * document.
     */
    private void append(Color color, String text) {
        var attribute = new SimpleAttributeSet();
        StyleConstants.setForeground(attribute, color);
        StyleConstants.setBold(attribute, ANSIColor.isBoldColor(color));

        var len = getDocument().getLength();

        if (text.contains("\r")) {
            // Carriage-return output distinguishes progress replacement from a
            // completed line; newline-bearing fragments retain their full text instead.
            if (text.contains("\n")) {
                try {
                    insertTextWithLinks(text, attribute);
                    trimDocument();
                } catch (BadLocationException e) {
                    // Newline-bearing progress output resets tracking below even if
                    // insertion fails; report the partial edit without aborting the producer.
                    log.error("Error while appending text to console", e);
                }

                currentLength = 0;
                return;
            }

            // Replace only the tracked progress suffix so earlier log history
            // survives updates that redraw the current carriage-return status.
            try {
                getDocument().remove(len - currentLength, currentLength);
                insertTextWithLinks(text, attribute);
                trimDocument();
                currentLength = text.length();
            } catch (BadLocationException e) {
                // Document edits may already be partial; report the location failure
                // without discarding earlier console history or aborting the output caller.
                log.error("Error while removing text from console, most likely has to do with printing weirdly", e);
            }
            return;
        }

        currentLength += text.length();

        try {
            insertTextWithLinks(text, attribute);
            trimDocument();
        } catch (BadLocationException e) {
            // Progress tracking has already advanced, so insertion failure can
            // leave it ahead of the document; preserve earlier output and report the failure.
            log.error("Error while appending text to console", e);
        }
    }

    /**
     * Splits one text fragment into plain and link-styled spans without dropping trailing
     * punctuation. Each valid URI receives a target attribute, while invalid URI syntax keeps the
     * original foreground styling.
     *
     * <p>
     * The cursor advances to the trimmed link end rather than the regex match end, so punctuation
     * excluded from a link remains in the next plain span. Matching covers only this fragment;
     * separately appended URL pieces are not joined. Document insertion failures propagate after
     * any earlier spans have already been inserted.
     */
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
            // Invalid URI syntax still contributes its original text, so browser
            // targeting can fail without dropping the candidate from console output.
            insertText(link, linkAttribute == null ? baseAttribute : linkAttribute);
            pos = linkEnd;
        }
        insertText(text.substring(pos), baseAttribute);
    }

    /**
     * Appends nonempty text with the supplied attributes to the document end. Empty strings do
     * nothing, and location failures propagate to the enclosing append operation after any earlier
     * inserted spans.
     */
    private void insertText(String text, SimpleAttributeSet attribute) throws BadLocationException {
        if (text.isEmpty()) {
            return;
        }

        getDocument().insertString(getDocument().getLength(), text, attribute);
    }

    /**
     * Copies the base styling and adds an underlined URI target for a link candidate. Invalid URI
     * syntax returns {@code null}, allowing the caller to retain the candidate as plain styled
     * text.
     */
    private static SimpleAttributeSet createLinkAttribute(SimpleAttributeSet baseAttribute, String link) {
        var attribute = new SimpleAttributeSet(baseAttribute);
        try {
            attribute.addAttribute(LINK_ATTRIBUTE, URI.create(link));
        } catch (IllegalArgumentException e) {
            // A regex candidate need not be a valid URI; let the caller
            // preserve its text without installing a clickable document attribute.
            return null;
        }
        StyleConstants.setUnderline(attribute, true);
        return attribute;
    }

    /**
     * Returns an exclusive end position after trimming common trailing prose punctuation from a
     * link candidate. The caller retains that punctuation as ordinary text rather than including it
     * in the clickable URI.
     */
    private static int trimLinkEnd(String text, int end) {
        while (end > 0 && ".,;:!?)]}".indexOf(text.charAt(end - 1)) != -1) {
            end--;
        }
        return end;
    }

    /**
     * Returns the URI attribute at the document position under the supplied point. Missing
     * positions or characters without a URI attribute return {@code null}, allowing ordinary text
     * cursor and click behavior.
     */
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

    /**
     * Removes the oldest characters beyond the retained-history limit without changing progress
     * tracking. A location failure is logged and suppressed, leaving the document potentially
     * larger than the intended bound.
     */
    private void trimDocument() {
        var doc = getDocument();
        var excess = doc.getLength() - MAX_DOCUMENT_CHARS;
        if (excess <= 0) return;
        try {
            // Keep the newest character tail when output exceeds the history
            // bound; truncation can cut a line rather than preserving whole records.
            doc.remove(0, excess);
        } catch (BadLocationException e) {
            // Retain whatever text remains after the failed trim and let future
            // output continue instead of failing the console producer.
            log.warn("Error while trimming console output", e);
        }
    }

    /**
     * Parses streamed ANSI foreground controls and appends visible spans on the Swing event thread.
     * Plain text continues using the shared current foreground, and unsupported complete sequences
     * select the reset color.
     *
     * <p>
     * A fragment ending before the next escape terminator is buffered in {@code remaining} and
     * prepended to the next call. This prevents a split control sequence from appearing as text or
     * losing its color selection. The buffered fragment has no independent size limit, and callers
     * coordinate access because parser state and styled-document edits are unsynchronized.
     */
    public void appendANSI(String text) {
        int aPos = 0;
        int aIndex;
        int mIndex;
        String tmpString;
        boolean stillSearching = true;
        String addString = remaining + text;
        remaining = "";

        if (!addString.isEmpty()) {
            aIndex = addString.indexOf("\u001B");
            if (aIndex == -1) {
                append(colorCurrent, addString);
                return;
            }

            if (aIndex > 0) {
                tmpString = addString.substring(0, aIndex);
                append(colorCurrent, tmpString);
                aPos = aIndex;
            }
            while (stillSearching) {
                mIndex = addString.indexOf("m", aPos);
                // An escape can end in a later output fragment. Retain its
                // unfinished tail so the next append can complete the same color control.
                if (mIndex < 0) {
                    remaining = addString.substring(aPos);
                    stillSearching = false;
                    continue;
                } else {
                    tmpString = addString.substring(aPos, mIndex + 1);
                    colorCurrent = ANSIColor.fromANSI(tmpString).getColor();
                }
                aPos = mIndex + 1;

                aIndex = addString.indexOf("\u001B", aPos);

                if (aIndex == -1) {
                    tmpString = addString.substring(aPos);
                    append(colorCurrent, tmpString);
                    stillSearching = false;
                    continue;
                }
                tmpString = addString.substring(aPos, aIndex);
                aPos = aIndex;
                append(colorCurrent, tmpString);
            }
        }
    }
}
