package org.allaymc.api.message;

/**
 * TrContainer is used to store the translation key and the arguments used in the translation.
 *
 * @author daoge_cmd
 */
public record TrContainer(@MayContainTrKey String str, Object... args) {
}
