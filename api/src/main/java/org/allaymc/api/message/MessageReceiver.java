package org.allaymc.api.message;

import org.allaymc.api.command.CommandSender;

import java.util.List;

/**
 * {@code MessageReceiver} accepts plain text, localized messages, and command output for one
 * delivery target. Receivers choose their presentation and localization behavior, while command
 * outputs carry the originating sender, result status, and permission requirements used by that
 * target.
 *
 * @author daoge_cmd
 */
public interface MessageReceiver {
    /**
     * Delivers the supplied plain text to this receiver's presentation target.
     */
    void sendMessage(String message);

    /**
     * Delivers translatable text without formatting arguments through the receiver's localization
     * operation. This convenience overload supplies an empty argument array and preserves the
     * receiver implementation's language selection and output behavior.
     */
    default void sendTranslatable(@MayContainTrKey String translatable) {
        sendTranslatable(translatable, new Object[0]);
    }

    /**
     * Delivers text that may contain a translation key using the supplied formatting arguments. The
     * receiver chooses its language and presentation; callers supply values compatible with that
     * receiver's localization implementation.
     */
    void sendTranslatable(@MayContainTrKey String translatable, Object... args);

    /**
     * Delivers command results with the originating sender, status, permission requirements, and
     * translatable output containers. Receiver implementations determine whether the target may
     * view the results and how each permitted message is presented.
     */
    void sendCommandOutputs(CommandSender sender, int status, List<String> permissions, TrContainer... outputs);
}
