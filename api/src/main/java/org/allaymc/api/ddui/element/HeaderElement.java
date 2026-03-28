package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BooleanProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;

/**
 * DDUI header element for prominent section titles inside custom forms.
 *
 * @author Miroshka
 */
public final class HeaderElement extends CustomFormElement {

    public HeaderElement(String text, ObjectProperty<?> parent) {
        super("header", parent);
        setText(text);
        setVisibility(true);
    }

    public HeaderElement(Observable<String> text, ObjectProperty<?> parent) {
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

    public HeaderElement setText(String text) {
        var property = getProperty("text");
        if (property instanceof StringProperty stringProperty) {
            stringProperty.setValue(text);
            return this;
        }

        setProperty(new StringProperty("text", text, this));
        return this;
    }

    @Override
    public HeaderElement setVisibility(boolean visible) {
        super.setVisibility(visible);
        var property = getProperty("header_visible");
        if (property instanceof BooleanProperty booleanProperty) {
            booleanProperty.setValue(visible);
            return this;
        }

        setProperty(new BooleanProperty("header_visible", visible, this));
        return this;
    }

    @Override
    public HeaderElement setVisibility(Observable<Boolean> visible) {
        super.setVisibility(visible);
        var property = getProperty("header_visible");
        var booleanProperty = property instanceof BooleanProperty existing
                ? existing
                : new BooleanProperty("header_visible", visible.getValue(), this);
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
