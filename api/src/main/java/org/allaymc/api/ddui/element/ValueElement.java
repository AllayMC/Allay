package org.allaymc.api.ddui.element;

/**
 * {@code ValueElement} identifies a DDUI control with a runtime value readable through a screen
 * session. The type parameter describes that value, and the original element handle associates
 * reads and writes with the correct compiled control in one session.
 *
 * @author daoge_cmd | SerenityJS
 */
public sealed interface ValueElement<T> extends DDUIElement permits ValueElementBase {
}
