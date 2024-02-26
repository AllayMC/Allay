package org.allaymc.api.form.type;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.form.element.Button;
import org.allaymc.api.form.element.ImageData;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Getter
@Slf4j
public final class SimpleForm extends Form {
    @SuppressWarnings("unused")
    private final String type = "form";
    private String title = "";
    private String content = "";
    private final List<Button> buttons = new ArrayList<>();
    private transient Consumer<Button> onResponse = button -> {};

    public SimpleForm title(String title) {
        this.title = title;
        return this;
    }

    public SimpleForm content(String content) {
        this.content = content;
        return this;
    }

    public Button button(Button button) {
        button.setForm(this);
        buttons.add(button);
        return button;
    }

    public Button button(String text) {
        return button(new Button(text));
    }

    public Button button(String text, String imageType, String data) {
        return button(new Button(text, new ImageData(imageType, data)));
    }

    public Button buttonWithPathImage(String text, String path) {
        return button(new Button(text, new ImageData(ImageData.PATH_TYPE, path)));
    }

    public Button buttonWithUrlImage(String text, String url) {
        return button(new Button(text, new ImageData(ImageData.URL_TYPE, url)));
    }

    public SimpleForm onResponse(Consumer<Button> onResponse) {
        this.onResponse = onResponse;
        return this;
    }

    @Override
    public void handleResponse(String data) {
        if (data == null) {
            response = null;
            onClose.run();
            return;
        }
        int buttonIndex;
        try {
            buttonIndex = Integer.parseInt(data);
        } catch (Exception e) {
            log.warn("Invalid simple form response: " + data);
            return;
        }
        if (buttonIndex >= this.buttons.size()) {
            log.warn("Button index out of range: " + buttonIndex);
            return;
        }
        var button = this.buttons.get(buttonIndex);
        onResponse.accept(button);
        button.getOnClick().accept(button);
        response = button;
    }

    @Override
    public Button getResponse() {
        return response != null ? (Button) response : null;
    }
}
