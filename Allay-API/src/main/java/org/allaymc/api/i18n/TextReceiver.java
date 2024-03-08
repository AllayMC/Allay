package org.allaymc.api.i18n;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.common.utils.Utils;

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

    default void sendTr(@MayContainTrKey String key, Object... args) {
        sendTr(key, Utils.objectArrayToStringArray(args));
    }

    default void sendTr(@MayContainTrKey String key) {
        sendTr(key, false, Utils.EMPTY_STRING_ARRAY);
    }

    void sendCommandOutputs(CommandSender sender, int status, TrContainer... outputs);
}
