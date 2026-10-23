package org.allaymc.api.ddui;

import org.allaymc.api.ddui.element.DDUIElement;
import org.allaymc.api.ddui.element.ValueElement;
import org.allaymc.api.ddui.type.DDUIScreen;
import org.allaymc.api.player.Player;

/**
 * {@code DDUIScreenSession} represents one viewer's runtime instance of a DDUI screen template.
 * Custom-form values and element state belong to the session, so callers use handles from its own
 * screen to read values or send runtime updates.
 *
 * @author daoge_cmd | SerenityJS
 */
public interface DDUIScreenSession {
    /**
     * Returns the player whose client displays this screen session.
     */
    Player viewer();

    /**
     * Returns the protocol form identifier assigned to this session.
     */
    int formId();

    /**
     * Returns the screen template used to create this session.
     */
    DDUIScreen screen();

    /**
     * Returns whether this runtime session has already been closed.
     */
    boolean closed();

    /**
     * Returns the current runtime value for an element belonging to this session. A closed session
     * rejects access, and an element from another screen or an unregistered handle is rejected.
     *
     * @throws IllegalStateException if the session is closed
     * @throws IllegalArgumentException if {@code element} has no value binding in this session
     */
    <T> T get(ValueElement<T> element);

    /**
     * Updates this session's runtime value and sends the normalized value to the viewer. This
     * operation does not invoke the element's client-change callback or write the bound observable.
     *
     * @throws IllegalStateException if the session is closed
     * @throws IllegalArgumentException if {@code element} has no value binding in this session
     */
    <T> void set(ValueElement<T> element, T value);

    /**
     * Updates an attached element's runtime visibility and sends the change to the viewer. The
     * element must belong to this session, and this operation does not reconfigure its template
     * property.
     *
     * @throws IllegalStateException if the session is closed
     * @throws IllegalArgumentException if {@code element} does not belong to this session
     */
    void setVisible(DDUIElement element, boolean visible);

    /**
     * Updates an attached element's runtime disabled state when that element exposes a disabled
     * binding. Other attached elements ignore the change; closed sessions and elements belonging to
     * another session are rejected.
     */
    void setDisabled(DDUIElement element, boolean disabled);

    /**
     * Requests closure for this session and submits a close packet for its viewer. Repeated closure
     * has no further effect; a normal submission return permits subscription cleanup before the
     * programmatic-close callback, without confirming client receipt.
     */
    void close();
}
