package org.allaymc.api.i18n;

/**
 * Allay Project 2023/12/15
 *
 * @author daoge_cmd
 */
public interface TextReceiver {
    void sendText(String text);

    void sendTr(String tr, boolean forceTranslatedByClient, String... args);

    default void sendTr(String tr, String... args) {
        sendTr(tr, false, args);
    }

    void sendTr(String tr);
}
