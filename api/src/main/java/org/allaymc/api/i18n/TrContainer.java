package org.allaymc.api.i18n;

/**
 * TrContainer is used to store the translation key and the arguments used in the translation.
 *
 * @author daoge_cmd
 */
public record TrContainer(String str, Object... args) {
}
