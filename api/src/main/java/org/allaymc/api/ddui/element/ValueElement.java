package org.allaymc.api.ddui.element;

/**
 * Represents a DDUI element that carries a value.
 *
 * @param <T> the value type
 *
 * @author OpenAI
 */
public sealed interface ValueElement<T> extends DDUIElement permits ValueElementBase {
}
