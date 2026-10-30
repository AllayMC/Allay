package org.allaymc.api.command.selector;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@code SelectorType} identifies the candidate-selection modes encoded by
 * supported selector letters. The selector implementation applies each mode's
 * initial entity scope and final reduction after argument filtering;
 * {@code parseSelectorType} accepts the letter alone, without the leading
 * {@code @}.
 */
@Getter
@AllArgsConstructor
public enum SelectorType {
    /**
     * All players in the execution dimension.
     */
    ALL_PLAYERS("a"),
    /**
     * All entities in the execution dimension.
     */
    ALL_ENTITIES("e"),
    /**
     * Random surviving entity; the default type argument restricts ordinary use
     * to players.
     */
    RANDOM_PLAYER("r"),
    /**
     * Entity represented by the command sender.
     */
    SELF("s"),
    /**
     * Nearest surviving player to the adjusted selection origin.
     */
    NEAREST_PLAYER("p");

    /**
     * Selector letter without the leading {@code @} character.
     */
    private final String token;

    /**
     * Returns the selector mode for an exact, lowercase selector letter.
     * Unsupported letters raise {@code SelectorSyntaxException}; callers remove
     * the leading {@code @} and any argument suffix before invoking this
     * parser.
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
