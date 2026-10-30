package org.allaymc.api.ddui.element;

/**
 * {@code DropdownItem} represents one selectable entry with display text, optional descriptive
 * text, and a numeric value. A dropdown sends this value back when the entry is selected, so
 * callers can choose stable values independent of display ordering.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class DropdownItem {
    /**
     * Primary item display text.
     */
    private final String label;
    /**
     * Secondary item display text.
     */
    private final String description;
    /**
     * Numeric client selection value.
     */
    private final long value;

    /**
     * Creates a dropdown entry with a label, numeric value, and empty description.
     */
    public DropdownItem(String label, long value) {
        this(label, "", value);
    }

    /**
     * Creates a dropdown entry storing its display text and numeric selection value.
     */
    public DropdownItem(String label, String description, long value) {
        this.label = label;
        this.description = description;
        this.value = value;
    }

    /**
     * Returns the primary display text stored for this dropdown item.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the secondary display text stored for this dropdown item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the numeric value reported when this item is selected.
     */
    public long getValue() {
        return value;
    }
}
