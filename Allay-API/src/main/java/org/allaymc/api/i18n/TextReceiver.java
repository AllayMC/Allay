package org.allaymc.api.i18n;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Allay Project 2023/12/15
 *
 * @author daoge_cmd
 */
public interface TextReceiver {
    void sendText(String text);

    void sendTr(@MayContainTrKey String key, boolean forceTranslatedByClient, String... args);

    default void sendTr(@MayContainTrKey String key, String... args) {
        sendTr(key, false, args);
    }

    default void sendTr(@MayContainTrKey String key) {
        sendTr(key, false);
    }

    void sendCommandOutputs(CommandSender sender, TrContainer... outputs);
}
