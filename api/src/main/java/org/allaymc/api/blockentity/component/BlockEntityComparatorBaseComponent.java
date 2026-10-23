package org.allaymc.api.blockentity.component;

import org.jetbrains.annotations.Range;

/**
 * {@code BlockEntityComparatorBaseComponent} exposes the output signal retained
 * for a redstone comparator. Redstone processing can read and replace this
 * value independently of the surrounding block properties, and the server
 * includes it in the block entity's persisted NBT.
 *
 * @author daoge_cmd
 */
public interface BlockEntityComparatorBaseComponent extends BlockEntityBaseComponent {
    /**
     * Returns the output signal currently stored for this redstone comparator.
     */
    @Range(from = 0, to = 15)
    int getOutputSignal();

    /**
     * Stores the comparator output signal supplied by redstone processing.
     * Callers use values from zero through fifteen; the server's generated
     * setter does not clamp the value or send a viewer update.
     */
    void setOutputSignal(@Range(from = 0, to = 15) int outputSignal);
}
