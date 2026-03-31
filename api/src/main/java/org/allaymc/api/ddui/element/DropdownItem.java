package org.allaymc.api.ddui.element;

/**
 * One selectable entry in a {@link Dropdown}.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class DropdownItem {
    private final String label;
    private final String description;
    private final long value;

    /**
     * Creates a dropdown item without a description.
     *
     * @param label the label shown to the viewer
     * @param value the value written back when this item is selected
     */
    public DropdownItem(String label, long value) {
        this(label, "", value);
    }

    /**
     * Creates a dropdown item.
     *
     * @param label       the label shown to the viewer
     * @param description the secondary text shown for the item
     * @param value       the value written back when this item is selected
     */
    public DropdownItem(String label, String description, long value) {
        this.label = label;
        this.description = description;
        this.value = value;
    }

    /**
     * Gets the item label.
     *
     * @return the item label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gets the item description.
     *
     * @return the item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the runtime value written back when this item is selected.
     *
     * @return the item value
     */
    public long getValue() {
        return value;
    }
}
