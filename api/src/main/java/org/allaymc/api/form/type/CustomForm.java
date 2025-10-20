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
 * CustomForm is a type of form that can have a variety of elements and a submit button.
 * It is suitable when you want the viewer to fill and submit some information.
 *
 * @author daoge_cmd
 */
@Getter
@Slf4j
public class CustomForm extends Form {
    @SuppressWarnings("unused")
    private final String type = "custom_form";

    private final List<CustomFormElement> content = new ArrayList<>();
    private String title = "";
    private ImageData icon;
    private transient Consumer<List<String>> onResponse = responses -> {
    };

    /**
     * Set the title of the form.
     *
     * @param title the title
     * @return the form
     */
    public CustomForm title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Set the icon of the form.
     *
     * @param icon the icon
     * @return the form
     */
    public CustomForm icon(ImageData icon) {
        this.icon = icon;
        return this;
    }

    /**
     * Set the icon of the form.
     *
     * @param imageType the type of the image
     * @param data      the data of the image
     * @return the form
     */
    public CustomForm icon(ImageData.ImageType imageType, String data) {
        return icon(new ImageData(imageType, data));
    }

    /**
     * Set the icon of the form.
     *
     * @param path the path of the image
     * @return the form
     */
    public CustomForm pathIcon(String path) {
        return icon(new ImageData(ImageData.ImageType.PATH, path));
    }

    /**
     * Set the icon of the form.
     *
     * @param url the URL of the image
     * @return the form
     */
    public CustomForm urlIcon(String url) {
        return icon(new ImageData(ImageData.ImageType.URL, url));
    }

    /**
     * Add an element to the form.
     *
     * @param element the element
     * @return the form
     */
    public CustomForm element(CustomFormElement element) {
        content.add(element);
        return this;
    }

    /**
     * Add a dropdown element to the form.
     *
     * @param text the text of the element
     * @return the form
     */
    public CustomForm dropdown(String text) {
        return dropdown(text, new ArrayList<>());
    }

    /**
     * Add a dropdown element to the form.
     *
     * @param text    the text of the element
     * @param options the options of the element
     * @return the form
     */
    public CustomForm dropdown(String text, List<String> options) {
        return dropdown(text, options, 0);
    }

    /**
     * Add a dropdown element to the form.
     *
     * @param text    the text of the element
     * @param options the options of the element
     * @return the form
     */
    public CustomForm dropdown(String text, String... options) {
        return dropdown(text, new ArrayList<>(Arrays.asList(options)));
    }

    /**
     * Add a dropdown element to the form.
     *
     * @param text               the text of the element
     * @param options            the options of the element
     * @param defaultOptionIndex the index of the default option
     * @return the form
     */
    public CustomForm dropdown(String text, List<String> options, int defaultOptionIndex) {
        return dropdown(text, options, defaultOptionIndex, null);
    }

    /**
     * Add a dropdown element to the form.
     *
     * @param text               the text of the element
     * @param options            the options of the element
     * @param defaultOptionIndex the index of the default option
     * @param tooltip            the tooltip of the element. Can be {@code null}
     * @return the form
     */
    public CustomForm dropdown(String text, List<String> options, int defaultOptionIndex, String tooltip) {
        return element(new Dropdown(text, options, defaultOptionIndex, tooltip));
    }

    /**
     * Add an input element to the form.
     *
     * @param text the text of the element
     * @return the form
     */
    public CustomForm input(String text) {
        return input(text, "");
    }

    /**
     * Add an input element to the form.
     *
     * @param text        the text of the element
     * @param placeholder the placeholder of the element
     * @return the form
     */
    public CustomForm input(String text, String placeholder) {
        return input(text, placeholder, "");
    }

    /**
     * Add an input element to the form.
     *
     * @param text        the text of the element
     * @param placeholder the placeholder of the element
     * @param defaultText the default text of the element
     * @return the form
     */
    public CustomForm input(String text, String placeholder, String defaultText) {
        return input(text, placeholder, defaultText, null);
    }

    /**
     * Add an input element to the form.
     *
     * @param text        the text of the element
     * @param placeholder the placeholder of the element
     * @param defaultText the default text of the element
     * @param tooltip     the tooltip of the element. Can be {@code null}
     * @return the form
     */
    public CustomForm input(String text, String placeholder, String defaultText, String tooltip) {
        return element(new Input(text, placeholder, defaultText, tooltip));
    }

    /**
     * Add a label element to the form.
     *
     * @param text the text of the element
     * @return the form
     */
    public CustomForm label(String text) {
        return element(new Label(text));
    }

    /**
     * Add a slider element to the form.
     *
     * @param text the text of the element
     * @return the form
     */
    public CustomForm slider(String text, float min, float max) {
        return slider(text, min, max, 1);
    }

    /**
     * Add a slider element to the form.
     *
     * @param text the text of the element
     * @param min  the minimum value of the element
     * @param max  the maximum value of the element
     * @param step the step of the element
     * @return the form
     */
    public CustomForm slider(String text, float min, float max, int step) {
        return slider(text, min, max, step, min);
    }

    /**
     * Add a slider element to the form.
     *
     * @param text         the text of the element
     * @param min          the minimum value of the element
     * @param max          the maximum value of the element
     * @param step         the step of the element
     * @param defaultValue the default value of the element
     * @return the form
     */
    public CustomForm slider(String text, float min, float max, int step, float defaultValue) {
        return slider(text, min, max, step, defaultValue, null);
    }

    /**
     * Add a slider element to the form.
     *
     * @param text         the text of the element
     * @param min          the minimum value of the element
     * @param max          the maximum value of the element
     * @param step         the step of the element
     * @param defaultValue the default value of the element
     * @param tooltip      the tooltip of the element. Can be {@code null}
     * @return the form
     */
    public CustomForm slider(String text, float min, float max, int step, float defaultValue, String tooltip) {
        return element(new Slider(text, min, max, step, defaultValue, tooltip));
    }

    /**
     * Add a step slider element to the form.
     *
     * @param text the text of the element
     * @return the form
     */
    public CustomForm stepSlider(String text) {
        return stepSlider(text, new ArrayList<>());
    }

    /**
     * Add a step slider element to the form.
     *
     * @param text  the text of the element
     * @param steps the steps of the element
     * @return the form
     */
    public CustomForm stepSlider(String text, String... steps) {
        return stepSlider(text, new ArrayList<>(Arrays.asList(steps)));
    }

    /**
     * Add a step slider element to the form.
     *
     * @param text  the text of the element
     * @param steps the steps of the element
     * @return the form
     */
    public CustomForm stepSlider(String text, List<String> steps) {
        return stepSlider(text, steps, 0);
    }

    /**
     * Add a step slider element to the form.
     *
     * @param text        the text of the element
     * @param steps       the steps of the element
     * @param defaultStep the default step of the element
     * @return the form
     */
    public CustomForm stepSlider(String text, List<String> steps, int defaultStep) {
        return stepSlider(text, steps, defaultStep, null);
    }

    /**
     * Add a step slider element to the form.
     *
     * @param text        the text of the element
     * @param steps       the steps of the element
     * @param defaultStep the default step of the element
     * @param tooltip     the tooltip of the element. Can be {@code null}
     * @return the form
     */
    public CustomForm stepSlider(String text, List<String> steps, int defaultStep, String tooltip) {
        return element(new StepSlider(text, steps, defaultStep, tooltip));
    }

    /**
     * Add a toggle element to the form.
     *
     * @param text the text of the element
     * @return the form
     */
    public CustomForm toggle(String text) {
        return toggle(text, false);
    }

    /**
     * Add a toggle element to the form.
     *
     * @param text         the text of the element
     * @param defaultValue the default value of the element
     * @return the form
     */
    public CustomForm toggle(String text, boolean defaultValue) {
        return toggle(text, defaultValue, null);
    }

    /**
     * Add a toggle element to the form.
     *
     * @param text         the text of the element
     * @param defaultValue the default value of the element
     * @param tooltip      the tooltip of the element. Can be {@code null}
     * @return the form
     */
    public CustomForm toggle(String text, boolean defaultValue, String tooltip) {
        return element(new Toggle(text, defaultValue, tooltip));
    }

    /**
     * Add a header element to the form.
     *
     * @param text the text of the element
     * @return the form
     */
    public CustomForm header(String text) {
        return element(new Header(text));
    }

    /**
     * Add a divider element to the form.
     *
     * @return the form
     */
    public CustomForm divider() {
        return element(new Divider());
    }

    /**
     * Set the action to be performed when the form is submitted.
     *
     * @param onResponse the action to be performed when the form is submitted
     * @return the form
     */
    public CustomForm onResponse(Consumer<List<String>> onResponse) {
        this.onResponse = onResponse;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomForm onClose(Runnable onClose) {
        return (CustomForm) super.onClose(onClose);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleResponse(String data) {
        List<String> responses = GSON.fromJson(data, new TypeToken<List<String>>() {
        }.getType());
        this.onResponse.accept(responses);
        this.response = responses;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleClose(FormCancelReason reason) {
        onClose.accept(reason);
    }

    /**
     * This method is only used for server settings form.
     */
    @ApiStatus.Internal
    public void syncDefaultValueToResponse() {
        var response = getResponse();
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
     * {@inheritDoc}
     */
    @Override
    public List<String> getResponse() {
        return response != null ? (List<String>) response : null;
    }
}
