package org.allaymc.api.world.sound;

/**
 * {@code ChiseledBookshelfSound} describes a book insertion or removal at a
 * chiseled bookshelf. The runtime combines {@code type} with {@code enchanted}
 * to select the named sound variant; the bookshelf inventory change and effect
 * position remain the caller's responsibility.
 *
 * @param type bookshelf interaction sound category
 *
 * @param enchanted whether to select the enchanted-book variant
 */
public record ChiseledBookshelfSound(Type type, boolean enchanted) implements Sound {
    /**
     * {@code Type} identifies whether a bookshelf sound represents insertion or removal.
     */
    public enum Type {
        /**
         * Book insertion sound category.
         */
        INSERT,
        /**
         * Book removal sound category.
         */
        PICKUP
    }
}
