package org.allaymc.api.form.type;

import com.google.gson.Gson;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.ModalFormCancelReason;

import java.util.function.Consumer;

/**
 * The base class for form.
 *
 * @author daoge_cmd
 */
public abstract sealed class Form permits SimpleForm, ModalForm, CustomForm {

    protected static final Gson GSON = new Gson();

    protected transient Consumer<ModalFormCancelReason> onClose = reason -> {
    };
    protected transient Object response;

    /**
     * Send this form to a specific player.
     *
     * @param player the player that the form will be sent to
     */
    public void sendTo(EntityPlayer player) {
        player.showForm(this);
    }

    /**
     * Handle the response from the player.
     * <p>
     * If the player just close the form due to some reason (e.g. the player is opening his inventory), {@link #handleClose(ModalFormCancelReason)}
     * will be called instead of this method.
     *
     * @param data the response data from the player
     */
    public abstract void handleResponse(String data);

    /**
     * Handle the close of the form. This method will be called when the player close the form.
     *
     * @param reason the reason why the form is closed
     */
    public abstract void handleClose(ModalFormCancelReason reason);

    /**
     * @see #onClose(Consumer)
     */
    public Form onClose(Runnable onClose) {
        return onClose(reason -> onClose.run());
    }

    /**
     * Add a callback that will be called when player only close the form without other actions.
     *
     * @param onClose the callback
     *
     * @return the form
     */
    public Form onClose(Consumer<ModalFormCancelReason> onClose) {
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

    /**
     * Get the response from the player.
     *
     * @return the response
     */
    public Object getResponse() {
        return this.response;
    }
}
