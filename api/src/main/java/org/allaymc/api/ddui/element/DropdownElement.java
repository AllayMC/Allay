package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.BindingScope;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.LongProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;
import org.allaymc.api.player.Player;

import java.util.List;

/**
 * DDUI dropdown element for selecting one item from a list.
 *
 * @author Miroshka
 */
public final class DropdownElement extends CustomFormElement {

    private static final String SELECTED_INDEX_BINDING = "binding:selectedIndex";
    private static final String DESCRIPTION_BINDING = "binding:description";

    /**
     * Immutable dropdown entry descriptor.
     *
     * @author Miroshka
     */
    public record Item(String label, String description) {
        public Item(String label) {
            this(label, "");
        }
    }

    private final Observable<Long> selectedIndex;

    public DropdownElement(String label, List<Item> items, Observable<Long> selectedIndex, ObjectProperty<?> parent) {
        super("dropdown", parent);
        this.selectedIndex = selectedIndex;
        var availableItems = List.copyOf(items);
        setLabel(label);
        setVisibility(true);
        setDisabled(false);
        setDescription("");
        buildItemsProperty(availableItems);
        setSelectedIndex(selectedIndex);
    }

    public long getSelectedIndex() {
        var property = getProperty("value");
        return property instanceof LongProperty longProperty ? longProperty.getValue() : 0L;
    }

    public DropdownElement setSelectedIndex(long index) {
        unbind(SELECTED_INDEX_BINDING);
        selectedIndexProperty().setValue(index);
        return this;
    }

    public DropdownElement setSelectedIndex(Observable<Long> selectedIndex) {
        var longProperty = selectedIndexProperty();
        longProperty.setValue(selectedIndex.getValue());
        bind(SELECTED_INDEX_BINDING, selectedIndex, longProperty::setValue);
        return this;
    }

    public DropdownElement setDescription(String description) {
        unbind(DESCRIPTION_BINDING);
        writeStringProperty("description", description);
        return this;
    }

    @Override
    protected void applyVisibility(boolean visible) {
        super.applyVisibility(visible);
        writeBooleanProperty("dropdown_visible", visible);
    }

    @Override
    public void triggerListeners(Player player, Object data) {
        super.triggerListeners(player, data);
        if (data instanceof Number number) {
            var value = number.longValue();
            setSelectedIndex(value);
            BindingScope.suppressed(() -> selectedIndex.setValue(value));
        }
    }

    private void buildItemsProperty(List<Item> items) {
        var itemsObject = new ObjectProperty<>("items", this);
        for (var index = 0; index < items.size(); index++) {
            var item = items.get(index);
            var itemObject = new ObjectProperty<>(String.valueOf(index), itemsObject);
            itemObject.setProperty(new StringProperty("label", item.label(), itemObject));
            itemObject.setProperty(new StringProperty("description", item.description(), itemObject));
            itemObject.setProperty(new LongProperty("value", index, itemObject));
            itemsObject.setProperty(itemObject);
        }

        itemsObject.setProperty(new LongProperty("length", items.size(), itemsObject));
        setProperty(itemsObject);
    }

    private LongProperty selectedIndexProperty() {
        return ensureProperty("value", () -> {
            var property = new LongProperty("value", 0L, this);
            property.addListener(this::triggerListeners);
            return property;
        });
    }
}
