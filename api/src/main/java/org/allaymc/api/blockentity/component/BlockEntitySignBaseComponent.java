package org.allaymc.api.blockentity.component;

import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code BlockEntitySignBaseComponent} exposes independent front and back text
 * faces and the sign's waxing flag. The server retains mutable text objects for
 * both faces and sends viewer updates when their ordinary setters change text
 * or glowing state.
 *
 * @author daoge_cmd
 */
public interface BlockEntitySignBaseComponent extends BlockEntityBaseComponent {
    /**
     * Returns the mutable text-face object currently assigned to the sign's
     * front. The server exposes the same object used by persistence and
     * interactions; loading a new face compound can replace that object.
     */
    SignText getFrontText();

    /**
     * Returns the mutable text-face object currently assigned to the sign's
     * back. The server exposes the same object used by persistence and
     * interactions; loading a new face compound can replace that object.
     */
    SignText getBackText();

    /**
     * Returns whether this sign retains the waxed flag for interactions.
     */
    boolean isWaxed();

    /**
     * Stores the waxing flag and sends updated state to viewers. The server's
     * direct setter does not require a waxing item or emit a wax event; those
     * rules belong to player interaction handling.
     */
    void setWaxed(boolean waxed);

    /**
     * {@code SignText} exposes one mutable sign face's line storage, glowing
     * flag, flattened text, and NBT representation.
     */
    interface SignText {
        /**
         * Returns the line array retained for this sign face. The server
         * exposes the backing array directly, so caller mutations bypass line
         * sanitization and the viewer updates performed by {@code setText}.
         */
        String[] getText();

        /**
         * Replaces line storage and sends updated sign state to viewers. The
         * server truncates non-{@code null} entries to 255 UTF-16 code units in
         * the supplied array and retains that same array without limiting its
         * line count.
         */
        void setText(String[] text);

        /**
         * Returns whether this sign face currently uses the glowing-text flag.
         */
        boolean isGlowing();

        /**
         * Replaces this face's glowing-text flag and updates the sign's
         * viewers.
         */
        void setGlowing(boolean glowing);

        /**
         * Returns the retained lines joined with newline separators. The server
         * does not trim lines or append a separate trailing separator;
         * {@code null} entries use {@code String.join}'s textual {@code "null"}
         * representation.
         */
        String flattenText();

        /**
         * Builds NBT for this face's flattened text and glowing state. The
         * server also writes fixed color, outline, formatting, and owner
         * entries whose values are not separately retained by the text-face
         * object.
         */
        NbtMap saveNBT();
    }
}
