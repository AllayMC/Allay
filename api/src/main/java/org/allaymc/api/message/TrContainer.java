package org.allaymc.api.message;

/**
 * {@code TrContainer} represents translatable text together with the arguments used when a receiver
 * localizes it. Construction retains the argument array and each contained reference, so callers
 * coordinate mutations when the same container is passed to multiple receivers.
 *
 * @param str source text that may contain a translation key
 * @param args retained formatting-argument array; the generated accessor returns this same array
 * without copying it
 *
 * @author daoge_cmd
 */
public record TrContainer(@MayContainTrKey String str, Object... args) {
}
