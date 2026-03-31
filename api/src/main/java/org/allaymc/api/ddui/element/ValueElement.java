package org.allaymc.api.ddui.element;

/**
 * A DDUI element that exposes a runtime value.
 * <p>
 * Value elements are the controls that can be read and written through a
 * {@link org.allaymc.api.ddui.DDUIScreenSession}.
 *
 * @author daoge_cmd | SerenityJS
 */
public sealed interface ValueElement extends DDUIElement permits ValueElementBase {
}
