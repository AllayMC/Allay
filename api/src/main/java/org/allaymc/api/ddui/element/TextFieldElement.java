package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.BindingScope;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BooleanProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;
import org.allaymc.api.player.Player;

/**
 * DDUI text field element for string input.
 *
 * @author Miroshka
 */
public final class TextFieldElement extends CustomFormElement {

    private static final String TEXT_BINDING = "binding:text";
    private static final String DESCRIPTION_BINDING = "binding:description";
    private final Observable<String> text;

    public TextFieldElement(String label, Observable<String> text, ObjectProperty<?> parent) {
        super("textField", parent);
        this.text = text;
        setLabel(label);
        setVisibility(true);
        setTextFieldVisible(true);
        setDisabled(false);
        setDescription("");
        setText(text);
    }

    public boolean isTextFieldVisible() {
        var property = getProperty("textfield_visible");
        return !(property instanceof BooleanProperty booleanProperty) || booleanProperty.getValue();
    }

    public TextFieldElement setTextFieldVisible(boolean visible) {
        writeBooleanProperty("textfield_visible", visible);
        return this;
    }

    public String getText() {
        var property = getProperty("text");
        return property instanceof StringProperty stringProperty ? stringProperty.getValue() : "";
    }

    public TextFieldElement setText(String text) {
        unbind(TEXT_BINDING);
        textProperty().setValue(text);
        return this;
    }

    public TextFieldElement setText(Observable<String> text) {
        var stringProperty = textProperty();
        stringProperty.setValue(text.getValue());
        bind(TEXT_BINDING, text, stringProperty::setValue);
        return this;
    }

    public String getDescription() {
        var property = getProperty("description");
        return property instanceof StringProperty stringProperty ? stringProperty.getValue() : "";
    }

    public TextFieldElement setDescription(String description) {
        unbind(DESCRIPTION_BINDING);
        writeStringProperty("description", description);
        return this;
    }

    @Override
    public void triggerListeners(Player player, Object data) {
        super.triggerListeners(player, data);
        if (data instanceof String string) {
            setText(string);
            BindingScope.suppressed(() -> text.setValue(string));
        }
    }

    @Override
    protected void applyVisibility(boolean visible) {
        super.applyVisibility(visible);
        writeBooleanProperty("textfield_visible", visible);
    }

    private StringProperty textProperty() {
        return ensureProperty("text", () -> {
            var property = new StringProperty("text", "", this);
            property.addListener(this::triggerListeners);
            return property;
        });
    }
}
