package org.allaymc.api.ddui;

import org.allaymc.api.ddui.element.ButtonElement;
import org.allaymc.api.ddui.element.CloseButtonElement;
import org.allaymc.api.ddui.element.DropdownElement;
import org.allaymc.api.ddui.element.HeaderElement;
import org.allaymc.api.ddui.element.LabelElement;
import org.allaymc.api.ddui.element.SliderElement;
import org.allaymc.api.ddui.element.SpacerElement;
import org.allaymc.api.ddui.element.TextFieldElement;
import org.allaymc.api.ddui.element.ToggleElement;
import org.allaymc.api.ddui.internal.StringProperty;
import org.allaymc.api.player.Player;

import java.util.List;
import java.util.function.Consumer;

/**
 * DDUI implementation of the Bedrock custom form screen.
 *
 * @author Miroshka
 * @author xRookieFight
 */
public final class CustomFormScreen extends DataDrivenScreen {

    @Override
    public String getIdentifier() {
        return "minecraft:custom_form";
    }

    @Override
    public String getPropertyName() {
        return "custom_form_data";
    }

    /**
     * Sets a static title for the custom form.
     *
     * @param title screen title
     *
     * @return this screen
     */
    public CustomFormScreen title(String title) {
        setProperty(new StringProperty("title", title, this));
        return this;
    }

    /**
     * Binds the custom form title to an observable value.
     *
     * @param title observable title
     *
     * @return this screen
     */
    public CustomFormScreen title(Observable<String> title) {
        var property = new StringProperty("title", title.getValue(), this);
        title.subscribe(value -> {
            if (!Observable.bindingsSuppressed()) {
                property.setValue(value);
            }
        });
        setProperty(property);
        return this;
    }

    /**
     * Adds a DDUI close button to the screen.
     *
     * @return created close button element
     */
    public CloseButtonElement closeButton() {
        var element = new CloseButtonElement(this);
        element.addListener(Player::closeDataDrivenScreens);
        setProperty(element);
        return element;
    }

    /**
     * Adds a button element to the layout.
     *
     * @param label button label
     * @param listener click listener
     *
     * @return created button element
     */
    public ButtonElement button(String label, Consumer<Player> listener) {
        var element = new ButtonElement(label, layout);
        element.addListener(listener);
        layout.setProperty(element);
        return element;
    }

    /**
     * Adds a button element with an observable label.
     *
     * @param label observable label
     * @param listener click listener
     *
     * @return created button element
     */
    public ButtonElement button(Observable<String> label, Consumer<Player> listener) {
        var element = new ButtonElement(label.getValue(), layout);
        label.subscribe(value -> {
            if (!Observable.bindingsSuppressed()) {
                element.setLabel(value);
            }
        });
        element.addListener(listener);
        layout.setProperty(element);
        return element;
    }

    /**
     * Adds a text field element to the layout.
     *
     * @param label text field label
     * @param text bound text observable
     *
     * @return created text field element
     */
    public TextFieldElement textField(String label, Observable<String> text) {
        var element = new TextFieldElement(label, text, layout);
        layout.setProperty(element);
        return element;
    }

    /**
     * Adds a slider element to the layout.
     *
     * @param label slider label
     * @param minValue minimum value
     * @param maxValue maximum value
     * @param currentValue bound current value
     *
     * @return created slider element
     */
    public SliderElement slider(String label, long minValue, long maxValue, Observable<Long> currentValue) {
        var element = new SliderElement(label, currentValue, minValue, maxValue, layout);
        layout.setProperty(element);
        return element;
    }

    /**
     * Adds a static label element to the layout.
     *
     * @param text label text
     *
     * @return created label element
     */
    public LabelElement label(String text) {
        var element = new LabelElement(text, layout);
        layout.setProperty(element);
        return element;
    }

    /**
     * Adds a label element bound to an observable value.
     *
     * @param text observable label text
     *
     * @return created label element
     */
    public LabelElement label(Observable<String> text) {
        var element = new LabelElement(text, layout);
        layout.setProperty(element);
        return element;
    }

    /**
     * Adds a spacer element to the layout.
     *
     * @return created spacer element
     */
    public SpacerElement spacer() {
        var element = new SpacerElement(layout);
        layout.setProperty(element);
        return element;
    }

    /**
     * Adds a toggle element to the layout.
     *
     * @param label toggle label
     * @param toggled bound toggle state
     *
     * @return created toggle element
     */
    public ToggleElement toggle(String label, Observable<Boolean> toggled) {
        var element = new ToggleElement(label, toggled, layout);
        layout.setProperty(element);
        return element;
    }

    /**
     * Adds a static header element to the layout.
     *
     * @param text header text
     *
     * @return created header element
     */
    public HeaderElement header(String text) {
        var element = new HeaderElement(text, layout);
        layout.setProperty(element);
        return element;
    }

    /**
     * Adds a header element bound to an observable value.
     *
     * @param text observable header text
     *
     * @return created header element
     */
    public HeaderElement header(Observable<String> text) {
        var element = new HeaderElement(text, layout);
        layout.setProperty(element);
        return element;
    }

    /**
     * Adds a dropdown element to the layout.
     *
     * @param label dropdown label
     * @param items available items
     * @param selected bound selected index
     *
     * @return created dropdown element
     */
    public DropdownElement dropdown(String label, List<DropdownElement.Item> items, Observable<Long> selected) {
        var element = new DropdownElement(label, items, selected, layout);
        layout.setProperty(element);
        return element;
    }
}
