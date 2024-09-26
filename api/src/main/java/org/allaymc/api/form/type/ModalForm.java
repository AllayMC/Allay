package org.allaymc.api.form.type;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.function.Consumer;

/**
 * @author daoge_cmd
 */
@Getter
public final class ModalForm extends Form {
    @SuppressWarnings("unused")
    private final String type = "modal";

    private String title = "";
    private String content = "";
    @SerializedName("button1")
    private String trueButton = "";
    @SerializedName("button2")
    private String falseButton = "";
    private transient Runnable onTrue = () -> {};
    private transient Runnable onFalse = () -> {};
    private transient Consumer<String> onResponse = type -> {};

    public ModalForm title(String title) {
        this.title = title;
        return this;
    }

    public ModalForm content(String content) {
        this.content = content;
        return this;
    }

    public ModalForm trueButton(String trueButton) {
        this.trueButton = trueButton;
        return this;
    }

    public ModalForm trueButton(String trueButton, Runnable onTrue) {
        this.trueButton = trueButton;
        this.onTrue = onTrue;
        return this;
    }

    public ModalForm onTrue(Runnable onTrue) {
        this.onTrue = onTrue;
        return this;
    }

    public ModalForm falseButton(String falseButton) {
        this.falseButton = falseButton;
        return this;
    }

    public ModalForm falseButton(String falseButton, Runnable onFalse) {
        this.falseButton = falseButton;
        this.onFalse = onFalse;
        return this;
    }

    public ModalForm onFalse(Runnable onFalse) {
        this.onFalse = onFalse;
        return this;
    }

    public ModalForm onResponse(Consumer<String> onResponse) {
        this.onResponse = onResponse;
        return this;
    }

    @Override
    public void handleResponse(String data) {
        response = data;
        if (data == null) {
            onClose.run();
            return;
        }
        onResponse.accept(data);
        if (data.equals("true")) onTrue.run();
        else onFalse.run();
    }

    @Override
    public ModalForm onClose(Runnable runnable) {
        return (ModalForm) super.onClose(runnable);
    }

    @Override
    public String getResponse() {
        return response != null ? (String) response : "null";
    }
}
