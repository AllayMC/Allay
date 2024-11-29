package org.allaymc.api.form.type;

import com.google.gson.Gson;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * The base class for form.
 *
 * @author daoge_cmd
 */
public abstract sealed class Form permits SimpleForm, ModalForm, CustomForm {

    protected static final Gson GSON = new Gson();

    protected transient Runnable onClose = () -> {};
    protected transient Object response;

    /**
     * Send this form to a specific player.
     *
     * @param player the player that the form will be sent to.
     */
    public void sendTo(EntityPlayer player) {
        player.showForm(this);
    }

    /**
     * Handle the response from the player.
     * <p>
     * This method will be called when server receive the response packet from client.
     * Usually the user won't need to use this method.
     *
     * @param data the response data from the player. Can be {@code null} if the player only close the form without other actions.
     */
    public abstract void handleResponse(String data);

    /**
     * Add a callback that will be called when player only close the form without other actions.
     *
     * @param onClose the callback.
     *
     * @return the form.
     */
    public Form onClose(Runnable onClose) {
        this.onClose = onClose;
        return this;
    }

    /**
     * Convert this form to a JSON string.
     * <p>
     * This method is used to serialize the form when sending it to the client.
     *
     * @return the JSON string.
     */
    public String toJson() {
        return GSON.toJson(this);
    }

    /**
     * Get the response from the player.
     *
     * @return the response.
     */
    public Object getResponse() {
        return this.response;
    }
}
