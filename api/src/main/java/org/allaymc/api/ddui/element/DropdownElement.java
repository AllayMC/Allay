package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BooleanProperty;
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

    public record Item(String label, String description) {
        public Item(String label) {
            this(label, "");
        }
    }

    private final Observable<Long> selectedIndex;
    private final List<Item> items;

    public DropdownElement(String label, List<Item> items, Observable<Long> selectedIndex, ObjectProperty<?> parent) {
        super("dropdown", parent);
        this.selectedIndex = selectedIndex;
        this.items = List.copyOf(items);
        setLabel(label);
        setVisibility(true);
        setDisabled(false);
        setDescription("");
        buildItemsProperty();
        setSelectedIndex(selectedIndex.getValue());
        selectedIndex.subscribe(value -> {
            if (!Observable.bindingsSuppressed()) {
                setSelectedIndex(value);
            }
        });
    }

    public long getSelectedIndex() {
        var property = getProperty("value");
        return property instanceof LongProperty longProperty ? longProperty.getValue() : 0L;
    }

    public DropdownElement setSelectedIndex(long index) {
        var property = getProperty("value");
        if (property instanceof LongProperty longProperty) {
            longProperty.setValue(index);
            return this;
        }

        var longProperty = new LongProperty("value", index, this);
        longProperty.addListener(this::triggerListeners);
        setProperty(longProperty);
        return this;
    }

    public DropdownElement setDescription(String description) {
        var property = getProperty("description");
        if (property instanceof StringProperty stringProperty) {
            stringProperty.setValue(description);
            return this;
        }

        setProperty(new StringProperty("description", description, this));
        return this;
    }

    @Override
    public DropdownElement setVisibility(boolean visible) {
        super.setVisibility(visible);
        var property = getProperty("dropdown_visible");
        if (property instanceof BooleanProperty booleanProperty) {
            booleanProperty.setValue(visible);
            return this;
        }

        setProperty(new BooleanProperty("dropdown_visible", visible, this));
        return this;
    }

    @Override
    public void triggerListeners(Player player, Object data) {
        super.triggerListeners(player, data);
        if (data instanceof Number number) {
            var value = number.longValue();
            setSelectedIndex(value);
            Observable.withBindingsSuppressed(() -> selectedIndex.setValue(value));
        }
    }

    private void buildItemsProperty() {
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
}
