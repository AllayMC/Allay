package org.allaymc.api.ddui.session;

import org.allaymc.api.ddui.element.DDUIElement;
import org.allaymc.api.ddui.element.ValueElement;
import org.allaymc.api.ddui.type.DDUIScreen;
import org.allaymc.api.player.Player;

/**
 * Represents one opened DDUI screen instance for one viewer.
 *
 * @author OpenAI
 */
public interface DDUIScreenSession {
    /**
     * Gets the viewer that owns this session.
     *
     * @return the viewer
     */
    Player viewer();

    /**
     * Gets the form id assigned to this session.
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
     * Gets the current value of a DDUI value element in this session.
     *
     * @param element the element handle
     * @param <T>     the value type
     * @return the current value
     */
    <T> T get(ValueElement<T> element);

    /**
     * Updates the current value of a DDUI value element in this session.
     *
     * @param element the element handle
     * @param value   the new value
     * @param <T>     the value type
     */
    <T> void set(ValueElement<T> element, T value);

    /**
     * Updates the visibility of a DDUI element in this session.
     *
     * @param element the element handle
     * @param visible the new visibility
     */
    void setVisible(DDUIElement element, boolean visible);

    /**
     * Updates the disabled state of a DDUI element in this session.
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
