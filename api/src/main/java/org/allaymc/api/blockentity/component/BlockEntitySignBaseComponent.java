package org.allaymc.api.blockentity.component;

import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
public interface BlockEntitySignBaseComponent extends BlockEntityBaseComponent {
    /**
     * Gets the front text.
     *
     * @return the front text
     */
    SignText getFrontText();

    /**
     * Gets the back text.
     *
     * @return the back text
     */
    SignText getBackText();

    /**
     * Checks if the sign is waxed.
     *
     * @return {@code true} if the sign is waxed, {@code false} otherwise
     */
    boolean isWaxed();

    /**
     * Sets whether the sign is waxed.
     *
     * @param waxed {@code true} if the sign is waxed, {@code false} otherwise
     */
    void setWaxed(boolean waxed);

    /**
     * Represents the text on a sign.
     */
    interface SignText {
        /**
         * Gets the text.
         *
         * @return the text
         */
        String[] getText();

        /**
         * Sets the text.
         *
         * @param text the text to set
         */
        void setText(String[] text);

        /**
         * Checks if the text is glowing.
         *
         * @return {@code true} if the text is glowing, {@code false} otherwise
         */
        boolean isGlowing();

        /**
         * Sets whether the text is glowing.
         *
         * @param glowing {@code true} if the text is glowing, {@code false} otherwise
         */
        void setGlowing(boolean glowing);

        /**
         * Flattens the text into a single string.
         *
         * @return the flattened text
         */
        String flattenText();

        /**
         * Saves the text to a NBT map.
         *
         * @return the NBT map
         */
        NbtMap saveNBT();
    }
}
