package org.allaymc.api.world.sound;

/**
 * {@code TridentRiptideSound} describes the sound of using a trident's Riptide
 * enchantment. Its constructor accepts only levels one through three, and the
 * runtime maps each validated level to its corresponding event when the caller
 * broadcasts this descriptor.
 *
 * @param level Riptide enchantment level from one through three
 *
 * @author daoge_cmd
 */
public record TridentRiptideSound(int level) implements Sound {
    /**
     * Creates a sound descriptor for Riptide levels one through three. Values
     * outside that inclusive range throw {@link IllegalArgumentException}
     * before a descriptor can be used by the runtime encoder.
     */
    public TridentRiptideSound {
        if (level < 1 || level > 3) {
            throw new IllegalArgumentException("Riptide level must be between 1 and 3, got: " + level);
        }
    }
}
