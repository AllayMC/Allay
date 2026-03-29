package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;

/**
 * DDUI header element for prominent section titles inside custom forms.
 *
 * @author Miroshka
 */
public final class HeaderElement extends CustomFormElement {

    private static final String TEXT_BINDING = "binding:text";

    public HeaderElement(String text, ObjectProperty<?> parent) {
        super("header", parent);
        setText(text);
        setVisibility(true);
    }

    public HeaderElement(Observable<String> text, ObjectProperty<?> parent) {
        this(text.getValue(), parent);
        setText(text);
    }

    public String getText() {
        var property = getProperty("text");
        return property instanceof StringProperty stringProperty ? stringProperty.getValue() : "";
    }

    public HeaderElement setText(String text) {
        unbind(TEXT_BINDING);
        writeDisplayTextProperty("text", text);
        return this;
    }

    public HeaderElement setText(Observable<String> text) {
        bindDisplayTextProperty(TEXT_BINDING, "text", text);
        return this;
    }

    @Override
    protected void applyVisibility(boolean visible) {
        super.applyVisibility(visible);
        writeBooleanProperty("header_visible", visible);
    }
}
