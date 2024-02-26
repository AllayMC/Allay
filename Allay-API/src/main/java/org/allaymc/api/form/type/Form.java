package org.allaymc.api.form.type;

import com.google.gson.Gson;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public abstract sealed class Form permits SimpleForm, ModalForm, CustomForm {

    protected static final Gson GSON = new Gson();
    protected transient Runnable onClose = () -> {};

    public void sendTo(EntityPlayer player) {
        player.showForm(this);
    }

    // TODO: make it abstract
    public void handleResponse(String data) {

    }

    public Form onClose(Runnable runnable) {
        this.onClose = runnable;
        return this;
    }

    public String toJson() {
        return GSON.toJson(this);
    }
}
