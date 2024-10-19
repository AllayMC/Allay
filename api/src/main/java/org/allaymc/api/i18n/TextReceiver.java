package org.allaymc.api.i18n;

import org.allaymc.api.command.CommandSender;
import org.allaymc.api.utils.Utils;

/**
 * Represents a text receiver.
 *
 * @author daoge_cmd
 */
public interface TextReceiver {
    /**
     * Send a text to the receiver.
     *
     * @param text the text.
     */
    void sendText(String text);

    /**
     * Send a text which may contains translation key to the receiver.
     *
     * @param key                     the text which may contains translation key.
     * @param forceTranslatedByClient whether the text should be translated by the client.
     * @param args                    the arguments used in the translation.
     */
    void sendTr(@MayContainTrKey String key, boolean forceTranslatedByClient, String... args);

    /**
     * Send a text which may contains translation key to the receiver.
     *
     * @param key  the text which may contains translation key.
     * @param args the arguments used in the translation.
     */
    default void sendTr(@MayContainTrKey String key, String... args) {
        sendTr(key, false, args);
    }

    /**
     * Send a text which may contains translation key to the receiver.
     *
     * @param key  the text which may contains translation key.
     * @param args the arguments used in the translation.
     */
    default void sendTr(@MayContainTrKey String key, Object... args) {
        sendTr(key, Utils.objectArrayToStringArray(args));
    }

    /**
     * Send a text which may contains translation key to the receiver.
     *
     * @param key the text which may contains translation key.
     */
    default void sendTr(@MayContainTrKey String key) {
        sendTr(key, false, Utils.EMPTY_STRING_ARRAY);
    }

    /**
     * Send the command outputs to the receiver.
     *
     * @param sender  the sender of the command.
     * @param status  the status of the command.
     * @param outputs the command outputs.
     */
    void sendCommandOutputs(CommandSender sender, int status, TrContainer... outputs);
}
