package org.allaymc.api.form.type;

import com.google.gson.Gson;
import lombok.Getter;
import org.allaymc.api.form.FormCancelReason;
import org.allaymc.api.form.FormViewer;

import java.util.function.Consumer;

/**
 * The base class for form.
 *
 * @author daoge_cmd
 */
public abstract class Form {

    protected static final Gson GSON = new Gson();

    protected transient Consumer<FormCancelReason> onClose = reason -> {
    };
    /**
     * The response from the viewer.
     */
    @Getter
    protected transient Object response;

    /**
     * Send this form to a specific viewer.
     *
     * @param viewer the viewer that the form will be sent to
     */
    public void sendTo(FormViewer viewer) {
        viewer.viewForm(this);
    }

    /**
     * Handle the response from the viewer.
     * <p>
     * If the viewer just closes the form due to some reason (e.g. the viewer is opening his inventory), {@link #handleClose(FormCancelReason)}
     * will be called instead of this method.
     *
     * @param data the response data from the viewer
     */
    public abstract void handleResponse(String data);

    /**
     * Handle the close of the form. This method will be called when the viewer closes the form.
     *
     * @param reason the reason why the form is closed
     */
    public abstract void handleClose(FormCancelReason reason);

    /**
     * @see #onClose(Consumer)
     */
    public Form onClose(Runnable onClose) {
        return onClose(reason -> onClose.run());
    }

    /**
     * Add a callback that will be called when viewer only close the form without other actions.
     *
     * @param onClose the callback
     * @return the form
     */
    public Form onClose(Consumer<FormCancelReason> onClose) {
        this.onClose = onClose;
        return this;
    }


    /**
     * Convert this form to a JSON string.
     * <p>
     * This method is used to serialize the form when sending it to the client.
     *
     * @return the JSON string
     */
    public String toJson() {
        return GSON.toJson(this);
    }
}
