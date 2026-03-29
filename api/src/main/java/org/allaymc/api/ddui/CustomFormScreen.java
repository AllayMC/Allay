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
import org.allaymc.api.ddui.internal.DataDrivenProperty;
import org.allaymc.api.player.Player;

import java.util.List;
import java.util.function.Consumer;

/**
 * DDUI implementation of the Bedrock custom form screen.
 *
 * @author Miroshka
 */
public final class CustomFormScreen extends DataDrivenScreen {

    private static final String TITLE_BINDING = "binding:title";

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
        unbind(TITLE_BINDING);
        writeStringProperty("title", title);
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
        bindStringProperty(TITLE_BINDING, "title", title);
        return this;
    }

    /**
     * Adds a DDUI close button to the screen.
     *
     * @return created close button element
     */
    public CloseButtonElement closeButton() {
        var element = attachToScreen(new CloseButtonElement(this));
        element.addListener(Player::closeDataDrivenScreens);
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
        var element = attachToLayout(new ButtonElement(label, layout));
        element.addListener(listener);
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
        var element = attachToLayout(new ButtonElement(label.getValue(), layout));
        element.setLabel(label);
        element.addListener(listener);
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
        return attachToLayout(new TextFieldElement(label, text, layout));
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
        return attachToLayout(new SliderElement(label, currentValue, minValue, maxValue, layout));
    }

    /**
     * Adds a static label element to the layout.
     *
     * @param text label text
     *
     * @return created label element
     */
    public LabelElement label(String text) {
        return attachToLayout(new LabelElement(text, layout));
    }

    /**
     * Adds a label element bound to an observable value.
     *
     * @param text observable label text
     *
     * @return created label element
     */
    public LabelElement label(Observable<String> text) {
        return attachToLayout(new LabelElement(text, layout));
    }

    /**
     * Adds a spacer element to the layout.
     *
     * @return created spacer element
     */
    public SpacerElement spacer() {
        return attachToLayout(new SpacerElement(layout));
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
        return attachToLayout(new ToggleElement(label, toggled, layout));
    }

    /**
     * Adds a static header element to the layout.
     *
     * @param text header text
     *
     * @return created header element
     */
    public HeaderElement header(String text) {
        return attachToLayout(new HeaderElement(text, layout));
    }

    /**
     * Adds a header element bound to an observable value.
     *
     * @param text observable header text
     *
     * @return created header element
     */
    public HeaderElement header(Observable<String> text) {
        return attachToLayout(new HeaderElement(text, layout));
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
        return attachToLayout(new DropdownElement(label, items, selected, layout));
    }

    private <T extends DataDrivenProperty<?>> T attachToLayout(T property) {
        layout.setProperty(property);
        return property;
    }

    private <T extends DataDrivenProperty<?>> T attachToScreen(T property) {
        setProperty(property);
        return property;
    }
}
