package org.allaymc.api.form.type;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.allaymc.api.form.FormCancelReason;

import java.util.function.Consumer;

/**
 * {@code ModalForm} represents a JSON form with two labeled choices, content, and a title. Response
 * handling stores the raw response first, invokes the shared callback, then selects the first
 * action only for exact {@code "true"}; every other non-{@code null} value selects the second
 * action.
 *
 * @author daoge_cmd
 */
@Getter
public class ModalForm extends Form {
    /**
     * Serialized modal-form layout discriminator.
     */
    @SuppressWarnings("unused")
    private final String type = "modal";

    /**
     * Displayed form window title.
     */
    private String title = "";
    /**
     * Displayed main body text.
     */
    private String content = "";
    /**
     * Label for the first choice.
     */
    @SerializedName("button1")
    private String trueButton = "";
    /**
     * Label for the second choice.
     */
    @SerializedName("button2")
    private String falseButton = "";
    /**
     * {@code onTrue} runs for exact {@code "true"} after the shared callback. A shared callback
     * failure prevents this action.
     */
    private transient Runnable onTrue = () -> {
    };
    /**
     * {@code onFalse} runs for other non-{@code null} responses after the shared callback. A shared
     * callback failure prevents this action.
     */
    private transient Runnable onFalse = () -> {
    };
    /**
     * {@code onResponse} receives the raw response after storage and before choice dispatch. Its
     * failure leaves the new response stored.
     */
    private transient Consumer<String> onResponse = type -> {
    };

    /**
     * Stores the title text and returns this modal form for configuration.
     */
    public ModalForm title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Stores the content text and returns this modal form for configuration.
     */
    public ModalForm content(String content) {
        this.content = content;
        return this;
    }

    /**
     * Stores the first choice label and returns this modal form for configuration.
     */
    public ModalForm trueButton(String trueButton) {
        this.trueButton = trueButton;
        return this;
    }

    /**
     * Stores the first-choice action and returns this modal form. Response storage precedes
     * callback dispatch, and a shared response callback failure prevents either choice action from
     * running.
     */
    public ModalForm trueButton(String trueButton, Runnable onTrue) {
        this.trueButton = trueButton;
        this.onTrue = onTrue;
        return this;
    }

    /**
     * Stores the first-choice action and returns this modal form. Response storage precedes
     * callback dispatch, and a shared response callback failure prevents either choice action from
     * running.
     */
    public ModalForm onTrue(Runnable onTrue) {
        this.onTrue = onTrue;
        return this;
    }

    /**
     * Stores the second choice label and returns this modal form for configuration.
     */
    public ModalForm falseButton(String falseButton) {
        this.falseButton = falseButton;
        return this;
    }

    /**
     * Stores the second-choice action and returns this modal form. Response storage precedes
     * callback dispatch, and a shared response callback failure prevents either choice action from
     * running.
     */
    public ModalForm falseButton(String falseButton, Runnable onFalse) {
        this.falseButton = falseButton;
        this.onFalse = onFalse;
        return this;
    }

    /**
     * Stores the second-choice action and returns this modal form. Response storage precedes
     * callback dispatch, and a shared response callback failure prevents either choice action from
     * running.
     */
    public ModalForm onFalse(Runnable onFalse) {
        this.onFalse = onFalse;
        return this;
    }

    /**
     * Stores the shared response callback and returns this modal form. Response storage precedes
     * callback dispatch, and a shared response callback failure prevents either choice action from
     * running.
     */
    public ModalForm onResponse(Consumer<String> onResponse) {
        this.onResponse = onResponse;
        return this;
    }

    /**
     * Stores the raw response and invokes the shared callback before dispatching the selected
     * action. Only exact {@code "true"} selects {@code onTrue}; other non-{@code null} strings
     * select {@code onFalse}, and callback failures leave the response stored.
     */
    @Override
    public void handleResponse(String data) {
        response = data;
        onResponse.accept(data);
        // The response comparison uses the protocol's exact true token; other
        // supplied strings follow the second-choice path without boolean parsing.
        if (data.equals("true")) onTrue.run();
        else onFalse.run();
    }

    /**
     * Invokes the configured cancellation callback without replacing the stored response. A
     * callback failure propagates, and callers can still retrieve a response from an earlier
     * completed response dispatch.
     */
    @Override
    public void handleClose(FormCancelReason reason) {
        onClose.accept(reason);
    }

    /**
     * Stores the inherited cancellation callback and returns this modal form for configuration.
     * Cancellation does not clear the previous response, and callback exceptions propagate to the
     * operation reporting closure.
     */
    @Override
    public ModalForm onClose(Runnable onClose) {
        return (ModalForm) super.onClose(onClose);
    }

    /**
     * Returns the raw response stored before the shared response callback runs. Before storage, or
     * after a {@code null} response, this accessor returns the literal string {@code "null"} rather
     * than {@code null}.
     */
    @Override
    public String getResponse() {
        return response != null ? (String) response : "null";
    }
}
