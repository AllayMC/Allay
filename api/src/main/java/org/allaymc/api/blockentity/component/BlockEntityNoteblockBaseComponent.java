package org.allaymc.api.blockentity.component;

import org.jetbrains.annotations.Range;

/**
 * {@code BlockEntityNoteblockBaseComponent} exposes the pitch and remembered
 * redstone state of a note block. Pitch advancement changes the retained note
 * before sound emission by block behavior, and the server persists both values
 * through the inherited block-entity NBT contract.
 *
 * @author daoge_cmd
 */
public interface BlockEntityNoteblockBaseComponent extends BlockEntityBaseComponent {
    /**
     * Returns the pitch currently stored for this note block.
     */
    @Range(from = 0, to = 24)
    int getPitch();

    /**
     * Replaces the retained pitch used by later note-block sound emission.
     * Callers supply zero through twenty-four; the server stores the integer
     * directly without range validation, sound emission, or a viewer update.
     */
    void setPitch(@Range(from = 0, to = 24) int pitch);

    /**
     * Advances the retained pitch and returns the value to play next. For
     * ordinary pitches from zero through twenty-four, the server wraps
     * twenty-four to zero without emitting sound or updating viewers itself.
     */
    @Range(from = 0, to = 24)
    int getNextPitch();

    /**
     * Returns whether this note block remembers an active redstone input.
     */
    boolean isPowered();

    /**
     * Replaces the remembered redstone-input flag for subsequent note-block
     * behavior.
     */
    void setPowered(boolean powered);
}
