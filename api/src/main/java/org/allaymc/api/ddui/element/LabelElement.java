package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BooleanProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;

/**
 * DDUI label element for static or reactive text output.
 *
 * @author Miroshka
 */
public final class LabelElement extends CustomFormElement {

    public LabelElement(String text, ObjectProperty<?> parent) {
        super("label", parent);
        setText(text);
        setVisibility(true);
    }

    public LabelElement(Observable<String> text, ObjectProperty<?> parent) {
        this(text.getValue(), parent);
        text.subscribe(value -> {
            if (!Observable.bindingsSuppressed()) {
                setText(value);
            }
        });
    }

    public String getText() {
        var property = getProperty("text");
        return property instanceof StringProperty stringProperty ? stringProperty.getValue() : "";
    }

    public LabelElement setText(String text) {
        var property = getProperty("text");
        if (property instanceof StringProperty stringProperty) {
            stringProperty.setValue(text);
            return this;
        }

        setProperty(new StringProperty("text", text, this));
        return this;
    }

    @Override
    public LabelElement setVisibility(boolean visible) {
        super.setVisibility(visible);
        var property = getProperty("label_visible");
        if (property instanceof BooleanProperty booleanProperty) {
            booleanProperty.setValue(visible);
            return this;
        }

        setProperty(new BooleanProperty("label_visible", visible, this));
        return this;
    }

    @Override
    public LabelElement setVisibility(Observable<Boolean> visible) {
        super.setVisibility(visible);
        var property = getProperty("label_visible");
        var booleanProperty = property instanceof BooleanProperty existing
                ? existing
                : new BooleanProperty("label_visible", visible.getValue(), this);
        booleanProperty.setValue(visible.getValue());
        visible.subscribe(value -> {
            if (!Observable.bindingsSuppressed()) {
                booleanProperty.setValue(value);
            }
        });
        setProperty(booleanProperty);
        return this;
    }
}
