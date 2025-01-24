package org.allaymc.api.form.type;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.form.element.Button;
import org.allaymc.api.form.element.ImageData;
import org.cloudburstmc.protocol.bedrock.data.ModalFormCancelReason;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Simple form is a type of form that can have a title, content, and a list of buttons.
 * It is suitable for letting the player choose something.
 *
 * @author daoge_cmd
 */
@Getter
@Slf4j
public final class SimpleForm extends Form {
    @SuppressWarnings("unused")
    private final String type = "form";

    private final List<Button> buttons = new ArrayList<>();
    private String title = "";
    private String content = "";
    private transient Consumer<Button> onResponse = button -> {};

    /**
     * Set the title of the form.
     *
     * @param title the title.
     *
     * @return the form.
     */
    public SimpleForm title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Set the content of the form.
     *
     * @param content the content.
     *
     * @return the form.
     */
    public SimpleForm content(String content) {
        this.content = content;
        return this;
    }

    /**
     * Add a button to the form.
     *
     * @param button the button.
     *
     * @return the button.
     */
    public Button button(Button button) {
        button.setForm(this);
        buttons.add(button);
        return button;
    }

    /**
     * Add a button to the form.
     *
     * @param text the text of the button.
     *
     * @return the button.
     */
    public Button button(String text) {
        return button(new Button(text));
    }

    /**
     * Add a button to the form.
     *
     * @param text      the text of the button.
     * @param imageType the type of the image.
     * @param data      the data of the image.
     *                  If the type is {@code ImageData.ImageType.PATH}, it should be the path of the image.
     *                  If the type is {@code ImageData.ImageType.URL}, it should be the URL of the image.
     *
     * @return the button.
     */
    public Button button(String text, ImageData.ImageType imageType, String data) {
        return button(new Button(text, new ImageData(imageType, data)));
    }

    /**
     * Add a button to the form.
     *
     * @param text the text of the button.
     * @param path the path of the image.
     *
     * @return the button.
     */
    public Button buttonWithPathImage(String text, String path) {
        return button(new Button(text, new ImageData(ImageData.ImageType.PATH, path)));
    }

    /**
     * Add a button to the form.
     *
     * @param text the text of the button.
     * @param url  the URL of the image.
     *
     * @return the button.
     */
    public Button buttonWithUrlImage(String text, String url) {
        return button(new Button(text, new ImageData(ImageData.ImageType.URL, url)));
    }

    /**
     * Add a callback that will be called when a button is clicked.
     *
     * @param onResponse the callback.
     *
     * @return the form.
     */
    public SimpleForm onResponse(Consumer<Button> onResponse) {
        this.onResponse = onResponse;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleForm onClose(Runnable onClose) {
        return (SimpleForm) super.onClose(onClose);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleResponse(String data) {
        int buttonIndex;
        try {
            buttonIndex = Integer.parseInt(data);
        } catch (Exception e) {
            log.warn("Invalid simple form response: {}", data);
            return;
        }
        if (buttonIndex >= this.buttons.size()) {
            log.warn("Button index out of range: {}", buttonIndex);
            return;
        }
        var button = this.buttons.get(buttonIndex);
        onResponse.accept(button);
        button.getOnClick().accept(button);
        response = button;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleClose(ModalFormCancelReason reason) {
        onClose.accept(reason);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Button getResponse() {
        return response != null ? (Button) response : null;
    }
}
