package org.allaymc.api.i18n;

import org.allaymc.api.command.CommandSender;

/**
 * TextReceiver represents an object that can receive messages.
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
     * @see #sendTranslatable(String, Object...)
     */
    default void sendTranslatable(@MayContainTrKey String translatable) {
        sendTranslatable(translatable, new Object[0]);
    }

    /**
     * Send a text which may contains translation key to the receiver.
     *
     * @param translatable the translatable text which may contains translation key
     * @param args         the arguments used in the translation
     */
    void sendTranslatable(@MayContainTrKey String translatable, Object... args);

    /**
     * Send the command outputs to the receiver.
     *
     * @param sender  the sender of the command
     * @param status  the status of the command
     * @param outputs the command outputs
     */
    void sendCommandOutputs(CommandSender sender, int status, TrContainer... outputs);
}
