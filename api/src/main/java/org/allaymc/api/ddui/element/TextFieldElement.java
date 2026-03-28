package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BooleanProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;
import org.allaymc.api.player.Player;

/**
 * DDUI text field element for string input.
 *
 * @author xRookieFight
 */
public final class TextFieldElement extends CustomFormElement {

    private final Observable<String> text;

    public TextFieldElement(String label, Observable<String> text, ObjectProperty<?> parent) {
        super("textField", parent);
        this.text = text;
        setLabel(label);
        setVisibility(true);
        setTextFieldVisible(true);
        setDisabled(false);
        setDescription("");
        setText(text.getValue());
        text.subscribe(value -> {
            if (!Observable.bindingsSuppressed()) {
                setText(value);
            }
        });
    }

    public boolean isTextFieldVisible() {
        var property = getProperty("textfield_visible");
        return !(property instanceof BooleanProperty booleanProperty) || booleanProperty.getValue();
    }

    public TextFieldElement setTextFieldVisible(boolean visible) {
        var property = getProperty("textfield_visible");
        if (property instanceof BooleanProperty booleanProperty) {
            booleanProperty.setValue(visible);
            return this;
        }

        setProperty(new BooleanProperty("textfield_visible", visible, this));
        return this;
    }

    public String getText() {
        var property = getProperty("text");
        return property instanceof StringProperty stringProperty ? stringProperty.getValue() : "";
    }

    public TextFieldElement setText(String text) {
        var property = getProperty("text");
        if (property instanceof StringProperty stringProperty) {
            stringProperty.setValue(text);
            return this;
        }

        var stringProperty = new StringProperty("text", text, this);
        stringProperty.addListener(this::triggerListeners);
        setProperty(stringProperty);
        return this;
    }

    public String getDescription() {
        var property = getProperty("description");
        return property instanceof StringProperty stringProperty ? stringProperty.getValue() : "";
    }

    public TextFieldElement setDescription(String description) {
        var property = getProperty("description");
        if (property instanceof StringProperty stringProperty) {
            stringProperty.setValue(description);
            return this;
        }

        setProperty(new StringProperty("description", description, this));
        return this;
    }

    @Override
    public void triggerListeners(Player player, Object data) {
        super.triggerListeners(player, data);
        if (data instanceof String string) {
            setText(string);
            Observable.withBindingsSuppressed(() -> text.setValue(string));
        }
    }
}
