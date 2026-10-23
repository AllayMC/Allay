package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

/**
 * {@code Dropdown} represents a selection control whose runtime value is a dropdown item's numeric
 * value rather than its position. Construction copies the item list, and the initial selection uses
 * the first item value or zero for an empty list.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class Dropdown extends ValueElementBase<Dropdown, Long> {
    /**
     * {@code items} holds an immutable copy of the supplied entries in display order; callers
     * cannot change its structure after construction.
     */
    private final List<DropdownItem> items;

    /**
     * Creates a dropdown with a copied item list and the supplied label. The initial value is the
     * first item's numeric value, or {@code 0L} for an empty list; {@code null} items are rejected.
     */
    public Dropdown(String label, List<DropdownItem> items) {
        super(items.isEmpty() ? 0L : items.getFirst().getValue());
        this.items = List.copyOf(items);
        label(label);
    }

    /**
     * Creates a dropdown template using the supplied observable configuration. Construction
     * captures current source values; runtime sessions subscribe when compiled, and later literal
     * template changes do not replace those existing subscriptions.
     */
    public Dropdown(String label, List<DropdownItem> items, Observable<Long> value) {
        this(label, items);
        value(value);
    }

    /**
     * Returns the immutable item list copied when this dropdown was constructed. Entries retain
     * their configured labels, descriptions, and numeric values; changing the original input list
     * cannot alter this result.
     */
    @UnmodifiableView
    public List<DropdownItem> getItems() {
        return items;
    }

    /**
     * Returns this element using its concrete fluent configuration type.
     */
    @Override
    protected Dropdown self() {
        return this;
    }
}
