package org.allaymc.api.ddui.type;

import org.allaymc.api.ddui.DDUIScreenCloseReason;
import org.allaymc.api.ddui.DDUIScreenSession;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.Property;
import org.allaymc.api.ddui.element.*;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * A DDUI custom-form screen.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class CustomFormScreen extends DDUIScreen {
    private final List<DDUIElement> elements = new ArrayList<>();
    private final Property<String> title = new Property<>("");

    /**
     * Gets the bindable title property of this screen.
     *
     * @return the title property
     */
    public Property<String> getTitle() {
        return title;
    }

    /**
     * Gets the elements contained in this screen.
     *
     * @return an unmodifiable view of the screen elements
     */
    @UnmodifiableView
    public List<DDUIElement> getElements() {
        return Collections.unmodifiableList(elements);
    }

    /**
     * Sets the title text.
     *
     * @param title the title text
     * @return this screen
     */
    public CustomFormScreen title(String title) {
        this.title.set(title);
        return this;
    }

    /**
     * Binds the title text to an observable.
     *
     * @param title the title observable
     * @return this screen
     */
    public CustomFormScreen title(Observable<String> title) {
        this.title.bind(title);
        return this;
    }

    /**
     * Adds an element to this screen.
     *
     * @param element the element to add
     * @return this screen
     */
    public CustomFormScreen element(DDUIElement element) {
        element.bind(this, elements.size());
        elements.add(element);
        return this;
    }

    /**
     * Adds a label element with literal text.
     *
     * @param text the label text
     * @return this screen
     */
    public CustomFormScreen label(String text) {
        return element(new Label(text));
    }

    /**
     * Adds a label element bound to an observable.
     *
     * @param text the label observable
     * @return this screen
     */
    public CustomFormScreen label(Observable<String> text) {
        return element(new Label(text));
    }

    /**
     * Adds a header element with literal text.
     *
     * @param text the header text
     * @return this screen
     */
    public CustomFormScreen header(String text) {
        return element(new Header(text));
    }

    /**
     * Adds a header element bound to an observable.
     *
     * @param text the header observable
     * @return this screen
     */
    public CustomFormScreen header(Observable<String> text) {
        return element(new Header(text));
    }

    /**
     * Adds a spacer element.
     *
     * @return this screen
     */
    public CustomFormScreen spacer() {
        return element(new Spacer());
    }

    /**
     * Adds a divider element.
     *
     * @return this screen
     */
    public CustomFormScreen divider() {
        return element(new Divider());
    }

    /**
     * Adds a text-field element.
     *
     * @param label the field label
     * @return the created element handle
     */
    public TextField textField(String label) {
        var element = new TextField(label);
        this.element(element);
        return element;
    }

    /**
     * Adds a text-field element bound to an observable.
     *
     * @param label the field label
     * @param text  the text observable
     * @return the created element handle
     */
    public TextField textField(String label, Observable<String> text) {
        var element = new TextField(label, text);
        this.element(element);
        return element;
    }

    /**
     * Adds a toggle element.
     *
     * @param label the toggle label
     * @return the created element handle
     */
    public Toggle toggle(String label) {
        return toggle(label, false);
    }

    /**
     * Adds a toggle element with a literal default value.
     *
     * @param label        the toggle label
     * @param defaultValue the default value
     * @return the created element handle
     */
    public Toggle toggle(String label, boolean defaultValue) {
        var element = new Toggle(label).value(defaultValue);
        this.element(element);
        return element;
    }

    /**
     * Adds a toggle element bound to an observable.
     *
     * @param label   the toggle label
     * @param toggled the value observable
     * @return the created element handle
     */
    public Toggle toggle(String label, Observable<Boolean> toggled) {
        var element = new Toggle(label).value(toggled);
        this.element(element);
        return element;
    }

    /**
     * Adds a slider element.
     *
     * @param label the slider label
     * @param min   the minimum value
     * @param max   the maximum value
     * @return the created element handle
     */
    public Slider slider(String label, long min, long max) {
        return slider(label, min, max, min);
    }

    /**
     * Adds a slider element with a literal default value.
     *
     * @param label        the slider label
     * @param min          the minimum value
     * @param max          the maximum value
     * @param defaultValue the default value
     * @return the created element handle
     */
    public Slider slider(String label, long min, long max, long defaultValue) {
        var element = new Slider(label, min, max, defaultValue);
        this.element(element);
        return element;
    }

    /**
     * Adds a slider element bound to an observable value.
     *
     * @param label the slider label
     * @param value the value observable
     * @param min   the minimum value
     * @param max   the maximum value
     * @return the created element handle
     */
    public Slider slider(String label, Observable<Long> value, long min, long max) {
        var element = new Slider(label, value, min, max);
        this.element(element);
        return element;
    }

    /**
     * Adds a slider element whose value and range are bound to observables.
     *
     * @param label the slider label
     * @param value the value observable
     * @param min   the minimum-value observable
     * @param max   the maximum-value observable
     * @return the created element handle
     */
    public Slider slider(String label, Observable<Long> value, Observable<Long> min, Observable<Long> max) {
        var element = new Slider(label, value, min, max);
        this.element(element);
        return element;
    }

    /**
     * Adds a dropdown element.
     *
     * @param label the dropdown label
     * @param items the selectable items
     * @return the created element handle
     */
    public Dropdown dropdown(String label, List<DropdownItem> items) {
        return dropdown(label, items, 0);
    }

    /**
     * Adds a dropdown element with a default selection index.
     *
     * @param label        the dropdown label
     * @param items        the selectable items
     * @param defaultIndex the default selected index
     * @return the created element handle
     */
    public Dropdown dropdown(String label, List<DropdownItem> items, int defaultIndex) {
        var element = new Dropdown(label, items).value(items.get(defaultIndex).getValue());
        this.element(element);
        return element;
    }

    /**
     * Adds a dropdown element bound to an observable selected value.
     *
     * @param label the dropdown label
     * @param value the value observable
     * @param items the selectable items
     * @return the created element handle
     */
    public Dropdown dropdown(String label, Observable<Long> value, List<DropdownItem> items) {
        var element = new Dropdown(label, items).value(value);
        this.element(element);
        return element;
    }

    /**
     * Adds a button element.
     *
     * @param label the button label
     * @return the created element handle
     */
    public Button button(String label) {
        var element = new Button(label);
        this.element(element);
        return element;
    }

    /**
     * Adds a button element bound to an observable label.
     *
     * @param label the label observable
     * @return the created element handle
     */
    public Button button(Observable<String> label) {
        var element = new Button(label);
        this.element(element);
        return element;
    }

    /**
     * Adds a close-button element.
     *
     * @return this screen
     */
    public CustomFormScreen closeButton() {
        return element(new CloseButton());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomFormScreen onClose(BiConsumer<DDUIScreenSession, DDUIScreenCloseReason> onClose) {
        return (CustomFormScreen) super.onClose(onClose);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomFormScreen onClose(Runnable onClose) {
        return (CustomFormScreen) super.onClose(onClose);
    }
}
