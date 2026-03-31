package org.allaymc.api.ddui;

import org.allaymc.api.ddui.element.DDUIElement;
import org.allaymc.api.ddui.element.ValueElement;
import org.allaymc.api.ddui.type.DDUIScreen;
import org.allaymc.api.player.Player;

/**
 * Runtime session for one opened DDUI screen and one viewer.
 * <p>
 * A session is created from a screen template after it is sent to a viewer. For custom forms, it exposes the
 * current server-side view of element values as well as runtime updates such as visibility and disabled state.
 *
 * @author daoge_cmd | SerenityJS
 */
public interface DDUIScreenSession {
    /**
     * Gets the viewer that owns this session.
     *
     * @return the viewer
     */
    Player viewer();

    /**
     * Gets the protocol form id assigned to this session.
     *
     * @return the form id
     */
    int formId();

    /**
     * Gets the screen template that created this session.
     *
     * @return the screen template
     */
    DDUIScreen screen();

    /**
     * Checks whether this session has already been closed.
     *
     * @return {@code true} if this session is closed
     */
    boolean closed();

    /**
     * Gets the current runtime value of a value element in this session.
     *
     * @param element the element handle from the screen bound to this session
     * @param <T>     the value type
     * @return the current value
     */
    <T> T get(ValueElement element);

    /**
     * Updates the current runtime value of a value element in this session.
     *
     * @param element the element handle from the screen bound to this session
     * @param value   the new value
     * @param <T>     the value type
     */
    <T> void set(ValueElement element, T value);

    /**
     * Updates the visibility of an element in this session.
     *
     * @param element the element handle
     * @param visible the new visibility
     */
    void setVisible(DDUIElement element, boolean visible);

    /**
     * Updates the disabled state of an element in this session.
     * <p>
     * Implementations may ignore this call for elements that do not expose a disabled property.
     *
     * @param element  the element handle
     * @param disabled the new disabled state
     */
    void setDisabled(DDUIElement element, boolean disabled);

    /**
     * Closes this DDUI session.
     */
    void close();
}
