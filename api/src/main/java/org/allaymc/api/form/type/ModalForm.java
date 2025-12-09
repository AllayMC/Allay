package org.allaymc.api.form.type;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.allaymc.api.form.FormCancelReason;

import java.util.function.Consumer;

/**
 * ModalForm is a type of form that can have a title, content, and two buttons.
 * It is suitable for letting the viewer choose between two options.
 *
 * @author daoge_cmd
 */
@Getter
public class ModalForm extends Form {
    @SuppressWarnings("unused")
    private final String type = "modal";

    private String title = "";
    private String content = "";
    @SerializedName("button1")
    private String trueButton = "";
    @SerializedName("button2")
    private String falseButton = "";
    private transient Runnable onTrue = () -> {
    };
    private transient Runnable onFalse = () -> {
    };
    private transient Consumer<String> onResponse = type -> {
    };

    /**
     * Set the title of the form.
     *
     * @param title the title
     * @return the form
     */
    public ModalForm title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Set the content of the form.
     *
     * @param content the content
     * @return the form
     */
    public ModalForm content(String content) {
        this.content = content;
        return this;
    }

    /**
     * Set the text of the true button.
     *
     * @param trueButton the text of the true button
     * @return the form
     */
    public ModalForm trueButton(String trueButton) {
        this.trueButton = trueButton;
        return this;
    }

    /**
     * Set the text of the true button and the action to be performed when the viewer clicks the button.
     *
     * @param trueButton the text of the true button
     * @param onTrue     the action to be performed when the viewer clicks the button
     * @return the form
     */
    public ModalForm trueButton(String trueButton, Runnable onTrue) {
        this.trueButton = trueButton;
        this.onTrue = onTrue;
        return this;
    }

    /**
     * Set the action to be performed when the viewer clicks the true button.
     *
     * @param onTrue the action to be performed when the viewer clicks the true button
     * @return the form
     */
    public ModalForm onTrue(Runnable onTrue) {
        this.onTrue = onTrue;
        return this;
    }

    /**
     * Set the text of the false button.
     *
     * @param falseButton the text of the false button
     * @return the form
     */
    public ModalForm falseButton(String falseButton) {
        this.falseButton = falseButton;
        return this;
    }

    /**
     * Set the text of the false button and the action to be performed when the viewer clicks the button.
     *
     * @param falseButton the text of the false button
     * @param onFalse     the action to be performed when the viewer clicks the button
     * @return the form
     */
    public ModalForm falseButton(String falseButton, Runnable onFalse) {
        this.falseButton = falseButton;
        this.onFalse = onFalse;
        return this;
    }

    /**
     * Set the action to be performed when the viewer clicks the false button.
     *
     * @param onFalse the action to be performed when the viewer clicks the false button
     * @return the form
     */
    public ModalForm onFalse(Runnable onFalse) {
        this.onFalse = onFalse;
        return this;
    }

    /**
     * Set the action to be performed when the viewer clicks the true or false button.
     *
     * @param onResponse the action to be performed when the viewer clicks the true or false button
     * @return the form
     */
    public ModalForm onResponse(Consumer<String> onResponse) {
        this.onResponse = onResponse;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleResponse(String data) {
        response = data;
        onResponse.accept(data);
        if (data.equals("true")) onTrue.run();
        else onFalse.run();
    }

    @Override
    public void handleClose(FormCancelReason reason) {
        onClose.accept(reason);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModalForm onClose(Runnable onClose) {
        return (ModalForm) super.onClose(onClose);
    }

    @Override
    public String getResponse() {
        return response != null ? (String) response : "null";
    }
}
