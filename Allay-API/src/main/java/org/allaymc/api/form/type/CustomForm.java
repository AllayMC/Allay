package org.allaymc.api.form.type;

import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.form.element.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Getter
@Slf4j
public final class CustomForm extends Form {
    @SuppressWarnings("unused")
    private final String type = "custom_form";
    private String title = "";
    private ImageData icon;
    private final List<CustomFormElement> content = new ArrayList<>();
    private transient Consumer<List<String>> onResponse = responses -> {};

    public CustomForm title(String title) {
        this.title = title;
        return this;
    }

    public CustomForm icon(ImageData icon) {
        this.icon = icon;
        return this;
    }

    public CustomForm icon(String imageType, String data) {
        return icon(new ImageData(imageType, data));
    }

    public CustomForm pathIcon(String path) {
        return icon(new ImageData(ImageData.PATH_TYPE, path));
    }

    public CustomForm urlIcon(String url) {
        return icon(new ImageData(ImageData.URL_TYPE, url));
    }

    public CustomForm element(CustomFormElement element) {
        content.add(element);
        return this;
    }

    public CustomForm dropdown(String text) {
        return dropdown(text, new ArrayList<>());
    }

    public CustomForm dropdown(String text, List<String> options) {
        return dropdown(text, options, 0);
    }

    public CustomForm dropdown(String text, String... options) {
        return dropdown(text, new ArrayList<>(Arrays.asList(options)));
    }

    public CustomForm dropdown(String text, List<String> options, int defaultOptionIndex) {
        return element(new Dropdown(text, options, defaultOptionIndex));
    }

    public CustomForm input(String text) {
        return input(text, "");
    }

    public CustomForm input(String text, String placeholder) {
        return input(text, placeholder, "");
    }

    public CustomForm input(String text, String placeholder, String defaultText) {
        return element(new Input(text, placeholder, defaultText));
    }

    public CustomForm label(String text) {
        return element(new Label(text));
    }

    public CustomForm slider(String text, float min, float max) {
        return slider(text, min, max, 1);
    }

    public CustomForm slider(String text, float min, float max, int step) {
        return slider(text, min, max, step, min);
    }

    public CustomForm slider(String text, float min, float max, int step, float defaultValue) {
        return element(new Slider(text, min, max, step, defaultValue));
    }

    public CustomForm stepSlider(String text) {
        return stepSlider(text, new ArrayList<>());
    }

    public CustomForm stepSlider(String text, String... steps) {
        return stepSlider(text, new ArrayList<>(Arrays.asList(steps)));
    }

    public CustomForm stepSlider(String text, List<String> steps) {
        return stepSlider(text, steps, 0);
    }

    public CustomForm stepSlider(String text, List<String> steps, int defaultStep) {
        return element(new StepSlider(text, steps, defaultStep));
    }

    public CustomForm toggle(String text) {
        return toggle(text, false);
    }

    public CustomForm toggle(String text, boolean defaultValue) {
        return element(new Toggle(text, defaultValue));
    }

    public CustomForm onResponse(Consumer<List<String>> onResponse) {
        this.onResponse = onResponse;
        return this;
    }

    @Override
    public void handleResponse(String data) {
        if (data == null) {
            onClose.run();
            return;
        }
        List<String> responses = GSON.fromJson(data, new TypeToken<List<String>>(){}.getType());
        onResponse.accept(responses);
    }
}
