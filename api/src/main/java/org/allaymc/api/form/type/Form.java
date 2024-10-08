package org.allaymc.api.form.type;

import com.google.gson.Gson;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author daoge_cmd
 */
public abstract sealed class Form permits SimpleForm, ModalForm, CustomForm {

    protected static final Gson GSON = new Gson();

    protected transient Runnable onClose = () -> {};
    @Getter
    protected transient Object response;

    public void sendTo(EntityPlayer player) {
        player.showForm(this);
    }

    public abstract void handleResponse(String data);

    public Form onClose(Runnable runnable) {
        this.onClose = runnable;
        return this;
    }

    public String toJson() {
        return GSON.toJson(this);
    }
}
