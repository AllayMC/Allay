package org.allaymc.api.command.selector;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * All possible selector types
 */
@Getter
@AllArgsConstructor
public enum SelectorType {
    ALL_PLAYERS("a"),
    ALL_ENTITIES("e"),
    // Can choose random non-player entities by specifying type
    // https://zh.minecraft.wiki/w/%E7%9B%AE%E6%A0%87%E9%80%89%E6%8B%A9%E5%99%A8
    RANDOM_PLAYER("r"),
    SELF("s"),
    NEAREST_PLAYER("p");

    private final String token;

    /**
     * Parses a string to determine the corresponding {@code SelectorType}.
     *
     * @param type The string representation of the selector type
     * @return The corresponding {@code SelectorType}
     * @throws SelectorSyntaxException If the provided type does not match any known selector type
     */
    public static SelectorType parseSelectorType(String type) throws SelectorSyntaxException {
        return switch (type) {
            case "a" -> SelectorType.ALL_PLAYERS;
            case "e" -> SelectorType.ALL_ENTITIES;
            case "r" -> SelectorType.RANDOM_PLAYER;
            case "s" -> SelectorType.SELF;
            case "p" -> SelectorType.NEAREST_PLAYER;
            default -> throw new SelectorSyntaxException("Unknown selector type: " + type);
        };
    }
}
