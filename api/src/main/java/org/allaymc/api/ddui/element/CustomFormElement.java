package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.CustomFormScreen;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.player.Player;

import java.util.List;
import java.util.function.Consumer;

/**
 * Base DDUI element type used inside custom form screens.
 *
 * @author Miroshka
 */
public abstract class CustomFormElement extends Element {

    protected CustomFormElement(String name, ObjectProperty<?> parent) {
        super(name, parent);
    }

    public CloseButtonElement closeButton() {
        return customFormScreen().closeButton();
    }

    public ButtonElement button(String label, Consumer<Player> listener) {
        return customFormScreen().button(label, listener);
    }

    public ButtonElement button(Observable<String> label, Consumer<Player> listener) {
        return customFormScreen().button(label, listener);
    }

    public TextFieldElement textField(String label, Observable<String> text) {
        return customFormScreen().textField(label, text);
    }

    public SliderElement slider(String label, long minValue, long maxValue, Observable<Long> currentValue) {
        return customFormScreen().slider(label, minValue, maxValue, currentValue);
    }

    public LabelElement label(String text) {
        return customFormScreen().label(text);
    }

    public LabelElement label(Observable<String> text) {
        return customFormScreen().label(text);
    }

    public SpacerElement spacer() {
        return customFormScreen().spacer();
    }

    public ToggleElement toggle(String label, Observable<Boolean> toggled) {
        return customFormScreen().toggle(label, toggled);
    }

    public HeaderElement header(String text) {
        return customFormScreen().header(text);
    }

    public HeaderElement header(Observable<String> text) {
        return customFormScreen().header(text);
    }

    public DropdownElement dropdown(String label, List<DropdownElement.Item> items, Observable<Long> selected) {
        return customFormScreen().dropdown(label, items, selected);
    }

    public CustomFormScreen screen() {
        return customFormScreen();
    }

    protected CustomFormScreen customFormScreen() {
        var screen = getRootScreen();
        if (screen instanceof CustomFormScreen customFormScreen) {
            return customFormScreen;
        }

        throw new IllegalStateException("Element is not attached to a custom form screen");
    }
}
