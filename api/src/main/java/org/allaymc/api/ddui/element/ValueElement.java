package org.allaymc.api.ddui.element;

/**
 * Represents a DDUI element that carries a value.
 *
 * @param <T> the value type
 *
 * @author daoge_cmd | SerenityJS
 */
public sealed interface ValueElement<T> extends DDUIElement permits ValueElementBase {
}
