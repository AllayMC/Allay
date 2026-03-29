package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;

/**
 * DDUI label element for static or reactive text output.
 *
 * @author Miroshka
 */
public final class LabelElement extends CustomFormElement {

    private static final String TEXT_BINDING = "binding:text";

    public LabelElement(String text, ObjectProperty<?> parent) {
        super("label", parent);
        setText(text);
        setVisibility(true);
    }

    public LabelElement(Observable<String> text, ObjectProperty<?> parent) {
        this(text.getValue(), parent);
        setText(text);
    }

    public String getText() {
        var property = getProperty("text");
        return property instanceof StringProperty stringProperty ? stringProperty.getValue() : "";
    }

    public LabelElement setText(String text) {
        unbind(TEXT_BINDING);
        writeDisplayTextProperty("text", text);
        return this;
    }

    public LabelElement setText(Observable<String> text) {
        bindDisplayTextProperty(TEXT_BINDING, "text", text);
        return this;
    }

    @Override
    protected void applyVisibility(boolean visible) {
        super.applyVisibility(visible);
        writeBooleanProperty("label_visible", visible);
    }
}
