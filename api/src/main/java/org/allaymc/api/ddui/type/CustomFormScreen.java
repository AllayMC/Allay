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
 * {@code CustomFormScreen} represents a DDUI custom-form template with ordered element handles and
 * a bindable title. Elements are attached once and keep their identity for later session
 * operations; literal configuration is compiled when a viewer opens the screen.
 *
 * <p>
 * Layout indexes count only ordinary elements. A {@code CloseButton} receives {@code -1} and
 * occupies the dedicated close-button property instead, so insertion of that control does not shift
 * normal layout paths. {@code element} attaches before appending, preserving the list when
 * attachment fails. The builder can collect multiple close buttons, but runtime session compilation
 * rejects that template rather than displaying an ambiguous dedicated control.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class CustomFormScreen extends DDUIScreen {
    /**
     * {@code elements} retains attached handles in insertion order. {@code getElements()} exposes a
     * live unmodifiable view, including any dedicated close button.
     */
    private final List<DDUIElement> elements = new ArrayList<>();
    /**
     * Bindable screen title property.
     */
    private final Property<String> title = new Property<>("");

    /**
     * Returns the mutable title property used when this template is compiled. An observable binding
     * can update opened sessions; later literal template changes do not reconfigure those already
     * compiled sessions.
     */
    public Property<String> getTitle() {
        return title;
    }

    /**
     * Returns a live unmodifiable view of the attached element handles in insertion order. Later
     * additions appear in the result, while existing sessions retain the layout compiled when they
     * opened.
     */
    @UnmodifiableView
    public List<DDUIElement> getElements() {
        return Collections.unmodifiableList(elements);
    }

    /**
     * Stores the literal screen title and returns this template for further configuration. This
     * clears the title's observable binding, and existing sessions retain their current compiled
     * title and source.
     */
    public CustomFormScreen title(String title) {
        this.title.set(title);
        return this;
    }

    /**
     * Binds the template title to an observable and returns this screen. Sessions subscribe when
     * compiled; changing this template binding does not replace the source already retained by an
     * existing session.
     */
    public CustomFormScreen title(Observable<String> title) {
        this.title.bind(title);
        return this;
    }

    /**
     * Attaches an element handle and appends it to this template. An existing owner rejects
     * reattachment before the list changes, while multiple dedicated close buttons are rejected
     * only during runtime compilation.
     *
     * <p>
     * The assigned layout index counts only non-close elements. A close button receives {@code -1}, preserving ordinary element positions because runtime compilation stores that button
     * separately from the indexed layout. Later template additions do not extend layouts already
     * compiled for active sessions.
     */
    public CustomFormScreen element(DDUIElement element) {
        var index = element instanceof CloseButton ? -1 : (int) elements.stream().filter(existing -> !(existing instanceof CloseButton)).count();
        element.bind(this, index);
        elements.add(element);
        return this;
    }

    /**
     * Appends a new label element and returns this screen template.
     */
    public CustomFormScreen label(String text) {
        return element(new Label(text));
    }

    /**
     * Appends a label whose text is bound to the supplied observable and returns this template. New
     * runtime sessions subscribe to that source; existing sessions do not acquire newly appended
     * elements.
     */
    public CustomFormScreen label(Observable<String> text) {
        return element(new Label(text));
    }

    /**
     * Appends a new header element and returns this screen template.
     */
    public CustomFormScreen header(String text) {
        return element(new Header(text));
    }

    /**
     * Appends a header whose text is bound to the supplied observable and returns this template.
     * New runtime sessions subscribe to that source; existing sessions do not acquire newly
     * appended elements.
     */
    public CustomFormScreen header(Observable<String> text) {
        return element(new Header(text));
    }

    /**
     * Appends a new spacer element and returns this screen template.
     */
    public CustomFormScreen spacer() {
        return element(new Spacer());
    }

    /**
     * Appends a new divider element and returns this screen template.
     */
    public CustomFormScreen divider() {
        return element(new Divider());
    }

    /**
     * Appends a text field with the supplied label and returns its attached handle.
     */
    public TextField textField(String label) {
        var element = new TextField(label);
        this.element(element);
        return element;
    }

    /**
     * Appends a text field using the supplied observable and returns its attached handle. Sessions
     * subscribe to that source when compiled; existing sessions do not acquire this newly appended
     * control.
     */
    public TextField textField(String label, Observable<String> text) {
        var element = new TextField(label, text);
        this.element(element);
        return element;
    }

    /**
     * Appends a toggle and returns its attached handle for further configuration. The initial value
     * is {@code false}; newly appended controls are included when subsequent runtime sessions
     * compile.
     */
    public Toggle toggle(String label) {
        return toggle(label, false);
    }

    /**
     * Appends a toggle and returns its attached handle for further configuration. The initial value
     * is {@code defaultValue}; newly appended controls are included when subsequent runtime
     * sessions compile.
     */
    public Toggle toggle(String label, boolean defaultValue) {
        var element = new Toggle(label).value(defaultValue);
        this.element(element);
        return element;
    }

    /**
     * Appends a toggle using the supplied observable configuration and returns its attached handle.
     * Sessions capture and subscribe to those properties when opened; existing sessions do not
     * acquire newly appended controls.
     */
    public Toggle toggle(String label, Observable<Boolean> toggled) {
        var element = new Toggle(label).value(toggled);
        this.element(element);
        return element;
    }

    /**
     * Appends an integer slider and returns its attached handle for further configuration. The
     * initial selection is {@code min}; the supplied bounds and selection are stored without range
     * validation.
     */
    public Slider slider(String label, long min, long max) {
        return slider(label, min, max, min);
    }

    /**
     * Appends an integer slider and returns its attached handle for further configuration. The
     * initial selection is {@code defaultValue}; the supplied bounds and selection are stored
     * without range validation.
     */
    public Slider slider(String label, long min, long max, long defaultValue) {
        var element = new Slider(label, min, max, defaultValue);
        this.element(element);
        return element;
    }

    /**
     * Appends a slider using the supplied observable configuration and returns its attached handle.
     * Sessions capture and subscribe to those properties when opened; existing sessions do not
     * acquire newly appended controls.
     */
    public Slider slider(String label, Observable<Long> value, long min, long max) {
        var element = new Slider(label, value, min, max);
        this.element(element);
        return element;
    }

    /**
     * Appends a slider using the supplied observable configuration and returns its attached handle.
     * Sessions capture and subscribe to those properties when opened; existing sessions do not
     * acquire newly appended controls.
     */
    public Slider slider(String label, Observable<Long> value, Observable<Long> min, Observable<Long> max) {
        var element = new Slider(label, value, min, max);
        this.element(element);
        return element;
    }

    /**
     * Appends a dropdown and returns its attached handle for further configuration. The initial
     * selection uses index zero, so the item list must not be empty.
     */
    public Dropdown dropdown(String label, List<DropdownItem> items) {
        return dropdown(label, items, 0);
    }

    /**
     * Appends a dropdown and returns its attached handle for further configuration. The initial
     * selection uses {@code defaultIndex}, which must identify an entry in the supplied non-{@code
     * null} item list.
     */
    public Dropdown dropdown(String label, List<DropdownItem> items, int defaultIndex) {
        var element = new Dropdown(label, items).value(items.get(defaultIndex).getValue());
        this.element(element);
        return element;
    }

    /**
     * Appends a dropdown bound to the supplied numeric selection observable and returns its
     * attached handle. Item values identify selections independently of indexes; an empty item list
     * is allowed for this overload.
     */
    public Dropdown dropdown(String label, Observable<Long> value, List<DropdownItem> items) {
        var element = new Dropdown(label, items).value(value);
        this.element(element);
        return element;
    }

    /**
     * Appends a button with the supplied label and returns its attached handle.
     */
    public Button button(String label) {
        var element = new Button(label);
        this.element(element);
        return element;
    }

    /**
     * Appends a button using the supplied observable configuration and returns its attached handle.
     * Sessions capture and subscribe to those properties when opened; existing sessions do not
     * acquire newly appended controls.
     */
    public Button button(Observable<String> label) {
        var element = new Button(label);
        this.element(element);
        return element;
    }

    /**
     * Appends a dedicated close button with the default label and returns this screen. The button
     * occupies no layout slot; runtime compilation rejects the template if another close button is
     * already present.
     */
    public CustomFormScreen closeButton() {
        return element(new CloseButton());
    }

    /**
     * Stores the inherited screen-close callback and returns this custom-form template for
     * configuration. The callback runs for closure without a normal response, and failures
     * propagate to the session operation invoking it.
     */
    @Override
    public CustomFormScreen onClose(BiConsumer<DDUIScreenSession, DDUIScreenCloseReason> onClose) {
        return (CustomFormScreen) super.onClose(onClose);
    }

    /**
     * Stores the inherited screen-close callback and returns this custom-form template for
     * configuration. The callback runs for closure without a normal response, and failures
     * propagate to the session operation invoking it.
     */
    @Override
    public CustomFormScreen onClose(Runnable onClose) {
        return (CustomFormScreen) super.onClose(onClose);
    }
}
