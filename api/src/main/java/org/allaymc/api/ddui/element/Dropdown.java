package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.DropdownItem;
import org.allaymc.api.ddui.Observable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

/**
 * A DDUI dropdown element.
 *
 * @author OpenAI
 */
public final class Dropdown extends ValueElementBase<Dropdown, Long> {
    private final List<DropdownItem> items;

    /**
     * Creates a dropdown whose default selected value is the first item value.
     *
     * @param label the dropdown label
     * @param items the selectable items
     */
    public Dropdown(String label, List<DropdownItem> items) {
        super(items.isEmpty() ? 0L : items.getFirst().getValue());
        this.items = List.copyOf(items);
        label(label);
    }

    /**
     * Creates a dropdown bound to an observable selected value.
     *
     * @param label the dropdown label
     * @param items the selectable items
     * @param value the selected-value observable
     */
    public Dropdown(String label, List<DropdownItem> items, Observable<Long> value) {
        this(label, items);
        value(value);
    }

    /**
     * Gets the selectable dropdown items.
     *
     * @return an unmodifiable view of the selectable items
     */
    @UnmodifiableView
    public List<DropdownItem> getItems() {
        return items;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Dropdown self() {
        return this;
    }
}
