package org.allaymc.api.form.type;

import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.form.FormCancelReason;
import org.allaymc.api.form.element.*;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * {@code CustomForm} represents a JSON form containing an ordered list of editable controls and
 * decorative entries. Its response is a positional list of strings decoded by Gson, with numeric
 * and boolean primitives converted to their string forms.
 *
 * <p>
 * {@code content} and a successful stored {@code response} must keep matching positions when
 * server-settings defaults are synchronized. The response callback runs before the new response is
 * stored and can mutate that same list. {@code syncDefaultValueToResponse} applies entries in order
 * without a size check or rollback; conversion failures or short responses leave earlier defaults
 * changed. Cancellation preserves the last stored response rather than recording a new value.
 *
 * @author daoge_cmd
 */
@Getter
@Slf4j
public class CustomForm extends Form {
    /**
     * Serialized custom-form layout discriminator.
     */
    @SuppressWarnings("unused")
    private final String type = "custom_form";

    /**
     * {@code content} retains controls in response order. The generated getter exposes the mutable
     * list, so edits can change positional default synchronization.
     */
    private final List<CustomFormElement> content = new ArrayList<>();
    /**
     * Displayed form window title.
     */
    private String title = "";
    /**
     * Optional form icon reference.
     */
    private ImageData icon;
    /**
     * {@code onResponse} receives the parsed mutable list before {@code response} is replaced.
     * Callback failure preserves the previously stored response.
     */
    private transient Consumer<List<String>> onResponse = responses -> {
    };

    /**
     * Stores the window title and returns this form for further configuration.
     */
    public CustomForm title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Stores the supplied image reference and returns this form for configuration.
     */
    public CustomForm icon(ImageData icon) {
        this.icon = icon;
        return this;
    }

    /**
     * Creates an icon reference of the supplied type and returns this form.
     */
    public CustomForm icon(ImageData.ImageType imageType, String data) {
        return icon(new ImageData(imageType, data));
    }

    /**
     * Sets a resource-pack path icon and returns this form for configuration.
     */
    public CustomForm pathIcon(String path) {
        return icon(new ImageData(ImageData.ImageType.PATH, path));
    }

    /**
     * Sets a remote URL icon and returns this form for configuration.
     */
    public CustomForm urlIcon(String url) {
        return icon(new ImageData(ImageData.ImageType.URL, url));
    }

    /**
     * Appends the supplied element to the positional content list and returns this form.
     */
    public CustomForm element(CustomFormElement element) {
        content.add(element);
        return this;
    }

    /**
     * Appends a dropdown and returns this form. The new control starts with an empty mutable list;
     * the initial selection uses index zero without checking whether that index exists.
     */
    public CustomForm dropdown(String text) {
        return dropdown(text, new ArrayList<>());
    }

    /**
     * Appends a dropdown and returns this form. The supplied {@code options} list remains shared
     * with the control; the initial selection uses index zero without checking whether that index
     * exists.
     */
    public CustomForm dropdown(String text, List<String> options) {
        return dropdown(text, options, 0);
    }

    /**
     * Appends a dropdown and returns this form. The supplied entries are copied into a new mutable
     * list; the initial selection uses index zero without checking whether that index exists.
     */
    public CustomForm dropdown(String text, String... options) {
        return dropdown(text, new ArrayList<>(Arrays.asList(options)));
    }

    /**
     * Appends a dropdown and returns this form. The supplied {@code options} list remains shared
     * with the control; the initial selection uses the supplied default index without checking
     * whether that index exists.
     */
    public CustomForm dropdown(String text, List<String> options, int defaultOptionIndex) {
        return dropdown(text, options, defaultOptionIndex, null);
    }

    /**
     * Appends a dropdown and returns this form. The supplied {@code options} list remains shared
     * with the control; the initial selection uses the supplied default index without checking
     * whether that index exists.
     */
    public CustomForm dropdown(String text, List<String> options, int defaultOptionIndex, String tooltip) {
        return element(new Dropdown(text, options, defaultOptionIndex, tooltip));
    }

    /**
     * Appends a text input and returns this form for configuration. Missing placeholder and default
     * text use empty strings. The tooltip is omitted when {@code null} or when this overload does
     * not supply one.
     */
    public CustomForm input(String text) {
        return input(text, "");
    }

    /**
     * Appends a text input and returns this form for configuration. The default text is empty
     * unless explicitly supplied. The tooltip is omitted when {@code null} or when this overload
     * does not supply one.
     */
    public CustomForm input(String text, String placeholder) {
        return input(text, placeholder, "");
    }

    /**
     * Appends a text input and returns this form for configuration. The default text is empty
     * unless explicitly supplied. The tooltip is omitted when {@code null} or when this overload
     * does not supply one.
     */
    public CustomForm input(String text, String placeholder, String defaultText) {
        return input(text, placeholder, defaultText, null);
    }

    /**
     * Appends a text input and returns this form for configuration. The default text is empty
     * unless explicitly supplied. The tooltip is omitted when {@code null} or when this overload
     * does not supply one.
     */
    public CustomForm input(String text, String placeholder, String defaultText, String tooltip) {
        return element(new Input(text, placeholder, defaultText, tooltip));
    }

    /**
     * Appends a decorative label entry and returns this form for configuration.
     */
    public CustomForm label(String text) {
        return element(new Label(text));
    }

    /**
     * Appends a numeric slider and returns this form for configuration. The step defaults to one,
     * and the initial value uses {@code min}. The control normalizes finite bounds but does not
     * clamp its default.
     */
    public CustomForm slider(String text, float min, float max) {
        return slider(text, min, max, 1);
    }

    /**
     * Appends a numeric slider and returns this form for configuration. The initial value uses
     * {@code min} unless {@code defaultValue} is supplied. The control normalizes finite bounds but
     * does not clamp its default.
     */
    public CustomForm slider(String text, float min, float max, int step) {
        return slider(text, min, max, step, min);
    }

    /**
     * Appends a numeric slider and returns this form for configuration. The initial value uses
     * {@code min} unless {@code defaultValue} is supplied. The control normalizes finite bounds but
     * does not clamp its default.
     */
    public CustomForm slider(String text, float min, float max, int step, float defaultValue) {
        return slider(text, min, max, step, defaultValue, null);
    }

    /**
     * Appends a numeric slider and returns this form for configuration. The initial value uses
     * {@code min} unless {@code defaultValue} is supplied. The control normalizes finite bounds but
     * does not clamp its default.
     */
    public CustomForm slider(String text, float min, float max, int step, float defaultValue, String tooltip) {
        return element(new Slider(text, min, max, step, defaultValue, tooltip));
    }

    /**
     * Appends a step slider and returns this form. The new control starts with an empty mutable
     * list; the initial selection uses index zero without checking whether that index exists.
     */
    public CustomForm stepSlider(String text) {
        return stepSlider(text, new ArrayList<>());
    }

    /**
     * Appends a step slider and returns this form. The supplied entries are copied into a new
     * mutable list; the initial selection uses index zero without checking whether that index
     * exists.
     */
    public CustomForm stepSlider(String text, String... steps) {
        return stepSlider(text, new ArrayList<>(Arrays.asList(steps)));
    }

    /**
     * Appends a step slider and returns this form. The supplied {@code steps} list remains shared
     * with the control; the initial selection uses index zero without checking whether that index
     * exists.
     */
    public CustomForm stepSlider(String text, List<String> steps) {
        return stepSlider(text, steps, 0);
    }

    /**
     * Appends a step slider and returns this form. The supplied {@code steps} list remains shared
     * with the control; the initial selection uses the supplied default index without checking
     * whether that index exists.
     */
    public CustomForm stepSlider(String text, List<String> steps, int defaultStep) {
        return stepSlider(text, steps, defaultStep, null);
    }

    /**
     * Appends a step slider and returns this form. The supplied {@code steps} list remains shared
     * with the control; the initial selection uses the supplied default index without checking
     * whether that index exists.
     */
    public CustomForm stepSlider(String text, List<String> steps, int defaultStep, String tooltip) {
        return element(new StepSlider(text, steps, defaultStep, tooltip));
    }

    /**
     * Appends a toggle and returns this form for further configuration. Its default state is
     * {@code false}. A {@code null} or omitted tooltip is absent from serialized output.
     */
    public CustomForm toggle(String text) {
        return toggle(text, false);
    }

    /**
     * Appends a toggle and returns this form for further configuration. Its initial state uses the
     * supplied default value. A {@code null} or omitted tooltip is absent from serialized output.
     */
    public CustomForm toggle(String text, boolean defaultValue) {
        return toggle(text, defaultValue, null);
    }

    /**
     * Appends a toggle and returns this form for further configuration. Its initial state uses the
     * supplied default value. A {@code null} or omitted tooltip is absent from serialized output.
     */
    public CustomForm toggle(String text, boolean defaultValue, String tooltip) {
        return element(new Toggle(text, defaultValue, tooltip));
    }

    /**
     * Appends a decorative header entry and returns this form for configuration.
     */
    public CustomForm header(String text) {
        return element(new Header(text));
    }

    /**
     * Appends a decorative divider entry and returns this form for configuration.
     */
    public CustomForm divider() {
        return element(new Divider());
    }

    /**
     * Stores the callback receiving the decoded response list and returns this form. The callback
     * sees the same list later retained as the response, and its failure prevents replacement of
     * the previous response.
     */
    public CustomForm onResponse(Consumer<List<String>> onResponse) {
        this.onResponse = onResponse;
        return this;
    }

    /**
     * Stores the inherited cancellation callback and returns this custom form for configuration.
     * Cancellation leaves the previous response unchanged, and callback failures propagate to the
     * caller dispatching the cancellation.
     */
    @Override
    public CustomForm onClose(Runnable onClose) {
        return (CustomForm) super.onClose(onClose);
    }

    /**
     * Decodes JSON response data into a string list and passes it to the configured callback. Gson
     * converts number and boolean elements to strings and preserves {@code null} elements; strings
     * are decoded before delivery. The callback receives the same list that becomes
     * {@code response}, so its mutations are retained after success. Parsing or callback failure
     * prevents the final response assignment and leaves the previous stored value available.
     */
    @Override
    public void handleResponse(String data) {
        List<String> responses = GSON.fromJson(data, new TypeToken<List<String>>() {
        }.getType());
        this.onResponse.accept(responses);
        this.response = responses;
    }

    /**
     * Invokes the configured close callback with the supplied cancellation reason. The previously
     * stored response remains unchanged, and any callback exception propagates without replacing or
     * clearing that response.
     */
    @Override
    public void handleClose(FormCancelReason reason) {
        onClose.accept(reason);
    }

    /**
     * Updates control defaults from the last successfully stored positional response for server
     * settings. A missing response logs a warning and leaves every default unchanged.
     *
     * <p>
     * Each content position consumes the matching response entry, including decorative entries that
     * ignore their value. Callers must preserve matching list sizes and order. A short response or
     * control conversion failure propagates after earlier defaults have changed; this operation
     * neither rolls them back nor stores values on disk.
     */
    @ApiStatus.Internal
    public void syncDefaultValueToResponse() {
        var response = getResponse();
        // A settings form can close before submission. Keep its configured
        // defaults when no earlier successful response supplies positional values.
        if (response == null) {
            log.warn("syncDefaultValueToResponse() called before receive response is called");
            return;
        }
        for (var index = 0; index < content.size(); index++) {
            var element = content.get(index);
            element.syncDefaultValueToResponse(response.get(index));
        }
    }

    /**
     * Returns the same mutable string list stored after a successful response callback. The result
     * is {@code null} before any response is stored, and cancellation preserves the last stored
     * list.
     */
    @Override
    public List<String> getResponse() {
        return response != null ? (List<String>) response : null;
    }
}
