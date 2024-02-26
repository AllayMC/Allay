package org.allaymc.api.form.type;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.function.Consumer;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Getter
public final class Modal extends Form {
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

    public Modal title(String title) {
        this.title = title;
        return this;
    }

    public Modal content(String content) {
        this.content = content;
        return this;
    }

    public Modal trueButton(String trueButton) {
        this.trueButton = trueButton;
        return this;
    }

    public Modal falseButton(String falseButton) {
        this.falseButton = falseButton;
        return this;
    }

    public Modal onTrue(Runnable onTrue) {
        this.onTrue = onTrue;
        return this;
    }

    public Modal onFalse(Runnable onFalse) {
        this.onFalse = onFalse;
        return this;
    }

    public Modal onResponse(Consumer<String> onResponse) {
        this.onResponse = onResponse;
        return this;
    }

    @Override
    public void handleResponse(String data) {
        if (data == null) {
            onClose.run();
        } else {
            onResponse.accept(data);
            if (data.equals("true")) {
                onTrue.run();
            } else {
                onFalse.run();
            }
        }
    }

    @Override
    public Modal onClose(Runnable runnable) {
        return (Modal) super.onClose(runnable);
    }
}
