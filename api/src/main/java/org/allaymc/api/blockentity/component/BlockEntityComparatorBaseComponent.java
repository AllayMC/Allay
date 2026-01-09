package org.allaymc.api.blockentity.component;

import org.jetbrains.annotations.Range;

/**
 * Component for comparator block entity that stores the output signal.
 *
 * @author daoge_cmd
 */
public interface BlockEntityComparatorBaseComponent extends BlockEntityBaseComponent {
    /**
     * Gets the output signal of the comparator.
     *
     * @return the output signal (0-15)
     */
    @Range(from = 0, to = 15)
    int getOutputSignal();

    /**
     * Sets the output signal of the comparator.
     *
     * @param outputSignal the output signal to set (0-15)
     */
    void setOutputSignal(@Range(from = 0, to = 15) int outputSignal);
}
