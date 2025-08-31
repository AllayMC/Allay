package org.allaymc.api.i18n;

import org.allaymc.api.command.CommandSender;

/**
 * Represents a text receiver.
 *
 * @author daoge_cmd
 */
public interface TextReceiver {
    /**
     * Send a text to the receiver.
     *
     * @param text the text
     */
    void sendText(String text);

    /**
     * @see #sendTr(String, boolean, Object...)
     */
    default void sendTr(@MayContainTrKey String key) {
        sendTr(key, new Object[0]);
    }

    /**
     * @see #sendTr(String, boolean, Object...)
     */
    default void sendTr(@MayContainTrKey String key, Object... args) {
        sendTr(key, false, args);
    }

    /**
     * Send a text which may contains translation key to the receiver.
     *
     * @param key                     the text which may contains translation key
     * @param forceTranslatedByClient whether the text should be translated by the client
     * @param args                    the arguments used in the translation
     */
    void sendTr(@MayContainTrKey String key, boolean forceTranslatedByClient, Object... args);

    /**
     * Send the command outputs to the receiver.
     *
     * @param sender  the sender of the command
     * @param status  the status of the command
     * @param outputs the command outputs
     */
    void sendCommandOutputs(CommandSender sender, int status, TrContainer... outputs);
}
