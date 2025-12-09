package org.allaymc.api.form.type;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.form.FormCancelReason;
import org.allaymc.api.form.element.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Simple form is a type of form that can have a title, content, and a list of buttons.
 * It is suitable for letting the viewer choose something.
 *
 * @author daoge_cmd
 */
@Getter
@Slf4j
public class SimpleForm extends Form {
    @SuppressWarnings("unused")
    private final String type = "form";

    private final List<SimpleFormElement> elements = new ArrayList<>();
    private String title = "";
    private String content = "";
    private transient Consumer<Button> onResponse = button -> {
    };

    /**
     * Set the title of the form.
     *
     * @param title the title
     * @return the form
     */
    public SimpleForm title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Set the content of the form.
     *
     * @param content the content
     * @return the form
     */
    public SimpleForm content(String content) {
        this.content = content;
        return this;
    }

    /**
     * Add a button to the form.
     *
     * @param button the button
     * @return the button
     */
    public Button button(Button button) {
        button.setForm(this);
        elements.add(button);
        return button;
    }

    /**
     * Add a button to the form.
     *
     * @param text the text of the button
     * @return the button
     */
    public Button button(String text) {
        return button(new Button(text));
    }

    /**
     * Add a button to the form.
     *
     * @param text      the text of the button
     * @param imageType the type of the image
     * @param data      the data of the image
     *                  If the type is {@code ImageData.ImageType.PATH}, it should be the path of the image
     *                  If the type is {@code ImageData.ImageType.URL}, it should be the URL of the image
     * @return the button
     */
    public Button button(String text, ImageData.ImageType imageType, String data) {
        return button(new Button(text, new ImageData(imageType, data)));
    }

    /**
     * Add a button to the form.
     *
     * @param text the text of the button
     * @param path the path of the image
     * @return the button
     */
    public Button buttonWithPathImage(String text, String path) {
        return button(new Button(text, new ImageData(ImageData.ImageType.PATH, path)));
    }

    /**
     * Add a button to the form.
     *
     * @param text the text of the button
     * @param url  the URL of the image
     * @return the button
     */
    public Button buttonWithUrlImage(String text, String url) {
        return button(new Button(text, new ImageData(ImageData.ImageType.URL, url)));
    }

    /**
     * Add a label to the form.
     *
     * @param text the text of the label
     * @return the form
     */
    public SimpleForm label(String text) {
        this.elements.add(new Label(text));
        return this;
    }

    /**
     * Add a header to the form.
     *
     * @param text the text of the header
     * @return the form
     */
    public SimpleForm header(String text) {
        this.elements.add(new Header(text));
        return this;
    }

    /**
     * Add a divider to the form.
     *
     * @return the form
     */
    public SimpleForm divider() {
        this.elements.add(new Divider());
        return this;
    }

    /**
     * Add a callback that will be called when a button is clicked.
     *
     * @param onResponse the callback
     * @return the form
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
        var button = getButton(buttonIndex);
        if (button != null) {
            onResponse.accept(button);
            button.getOnClick().accept(button);
            response = button;
        } else {
            log.warn("Viewer clicked an invalid simple form element: {}", data);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleClose(FormCancelReason reason) {
        onClose.accept(reason);
    }

    @Override
    public Button getResponse() {
        return response != null ? (Button) response : null;
    }

    private Button getButton(int buttonIndex) {
        for (var element : elements) {
            if (element instanceof Button button) {
                if (buttonIndex == 0) {
                    return button;
                }

                buttonIndex--;
            }
        }

        return null;
    }
}
