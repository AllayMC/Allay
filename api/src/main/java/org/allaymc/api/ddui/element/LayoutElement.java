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

    private int nextElementIndex;

    public LayoutElement(ObjectProperty<?> parent) {
        super("layout", parent);
    }

    @Override
    public void setProperty(DataDrivenProperty<?> property) {
        if ("length".equals(property.getName())) {
            super.setProperty(property);
            return;
        }

        property.setName(String.valueOf(nextElementIndex));
        nextElementIndex++;
        super.setProperty(property);
        refreshLength();
    }

    private void refreshLength() {
        var length = getProperty("length");
        if (length instanceof LongProperty longProperty) {
            longProperty.setValue((long) nextElementIndex);
            return;
        }

        super.setProperty(new LongProperty("length", nextElementIndex, this));
    }
}
