package org.allaymc.api.blockentity.component;

import org.jetbrains.annotations.Range;

/**
 * {@code BlockEntityTargetBaseComponent} exposes the active redstone power
 * stored for a target block. The server persists that value independently of
 * the surrounding block state, and target behavior uses the component to
 * remember the power produced by a hit.
 *
 * @author daoge_cmd
 */
public interface BlockEntityTargetBaseComponent extends BlockEntityBaseComponent {
    /**
     * Returns the active redstone power currently stored for this target.
     */
    @Range(from = 0, to = 15)
    int getActivePower();

    /**
     * Stores the active target power supplied by redstone processing. Callers
     * use values from zero through fifteen; the server's generated setter does
     * not clamp the value or send a viewer update.
     */
    void setActivePower(@Range(from = 0, to = 15) int power);
}
