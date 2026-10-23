package org.allaymc.api.form.element;

/**
 * {@code Divider} represents a decorative separator in a JSON form. The serialized entry includes a
 * type and an empty text property, and custom-form default synchronization ignores its response
 * position because the divider has no editable value.
 *
 * @author daoge_cmd
 */
public final class Divider implements SimpleFormElement, CustomFormElement {

    /**
     * Serialized form element discriminator.
     */
    @SuppressWarnings("unused")
    private final String type = "divider";

    /**
     * {@code text} preserves an empty text member in the serialized divider payload even though
     * this decorative entry has no editable value.
     */
    private final String text = "";

    /**
     * Creates a decorative divider entry with an empty serialized text property.
     */
    public Divider() {
    }

    /**
     * Leaves this decorative entry unchanged because it has no editable default value.
     */
    @Override
    public void syncDefaultValueToResponse(String response) {
    }
}
