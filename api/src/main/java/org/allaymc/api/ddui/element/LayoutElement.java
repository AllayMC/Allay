package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.internal.DataDrivenProperty;
import org.allaymc.api.ddui.internal.LongProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;

/**
 * Internal layout container that stores ordered custom form elements.
 *
 * @author Miroshka
 */
public final class LayoutElement extends ObjectProperty<Object> {

    public LayoutElement(ObjectProperty<?> parent) {
        super("layout", parent);
    }

    @Override
    public void setProperty(DataDrivenProperty<?> property) {
        var count = childCount();
        property.setName(String.valueOf(count));
        super.setProperty(property);

        var length = getProperty("length");
        if (length instanceof LongProperty longProperty) {
            longProperty.setValue(count + 1L);
        } else {
            super.setProperty(new LongProperty("length", count + 1L, this));
        }
    }

    private int childCount() {
        var size = getValue().size();
        return getValue().containsKey("length") ? size - 1 : size;
    }
}
