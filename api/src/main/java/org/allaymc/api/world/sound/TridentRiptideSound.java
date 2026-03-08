package org.allaymc.api.world.sound;

/**
 * TridentRiptideSound is a sound played when a player uses the riptide enchantment on a trident.
 * The sound varies based on the riptide level.
 *
 * @param level the riptide enchantment level (1-3)
 * @author daoge_cmd
 */
public record TridentRiptideSound(int level) implements Sound {
    public TridentRiptideSound {
        if (level < 1 || level > 3) {
            throw new IllegalArgumentException("Riptide level must be between 1 and 3, got: " + level);
        }
    }
}
