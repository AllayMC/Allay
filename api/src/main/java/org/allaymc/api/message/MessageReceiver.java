package org.allaymc.api.message;

import org.allaymc.api.command.CommandSender;

/**
 * MessageReceiver represents an object that can receive messages.
 *
 * @author daoge_cmd
 */
public interface MessageReceiver {
    /**
     * Send a text to the receiver.
     *
     * @param message the text
     */
    void sendMessage(String message);

    /**
     * @see #sendTranslatable(String, Object...)
     */
    default void sendTranslatable(@MayContainTrKey String translatable) {
        sendTranslatable(translatable, new Object[0]);
    }

    /**
     * Send a text which may contain a translation key to the receiver.
     *
     * @param translatable the translatable text which may contain a translation key
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
