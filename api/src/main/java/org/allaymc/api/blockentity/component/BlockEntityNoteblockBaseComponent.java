package org.allaymc.api.blockentity.component;

import org.jetbrains.annotations.Range;

/**
 * @author daoge_cmd
 */
public interface BlockEntityNoteblockBaseComponent extends BlockEntityBaseComponent {
    /**
     * Retrieves the pitch of the noteblock.
     *
     * @return the pitch of the noteblock, represented as an integer ranging from {@code 0} to {@code 24}
     */
    @Range(from = 0, to = 24)
    int getPitch();

    /**
     * Sets the pitch of the noteblock.
     *
     * @param pitch the pitch of the noteblock to be set, represented as an integer in the range {@code 0} to {@code 24}
     */
    void setPitch(@Range(from = 0, to = 24) int pitch);

    /**
     * Increments the pitch and returns the new value. Wraps around from 24 to 0.
     *
     * @return the new pitch value after incrementing
     */
    @Range(from = 0, to = 24)
    int getNextPitch();

    /**
     * Checks if the noteblock is currently powered by redstone.
     *
     * @return {@code true} if powered, {@code false} otherwise
     */
    boolean isPowered();

    /**
     * Sets the powered state of the noteblock.
     *
     * @param powered the powered state to set
     */
    void setPowered(boolean powered);
}
