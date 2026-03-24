package org.allaymc.api.world.sound;

/**
 * Sound played when a book is inserted into or removed from a chiseled bookshelf.
 *
 * @param type      the interaction type
 * @param enchanted whether the interacted book should use the enchanted variant
 */
public record ChiseledBookshelfSound(Type type, boolean enchanted) implements Sound {
    /**
     * The chiseled bookshelf interaction type.
     */
    public enum Type {
        INSERT,
        PICKUP
    }
}
