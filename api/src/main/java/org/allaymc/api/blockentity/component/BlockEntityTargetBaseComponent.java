package org.allaymc.api.blockentity.component;

import org.jetbrains.annotations.Range;

/**
 * Component for target block entity that stores the active power level.
 *
 * @author daoge_cmd
 */
public interface BlockEntityTargetBaseComponent extends BlockEntityBaseComponent {
    /**
     * Gets the active power level of the target block.
     *
     * @return the active power (0-15)
     */
    @Range(from = 0, to = 15)
    int getActivePower();

    /**
     * Sets the active power level of the target block.
     *
     * @param power the power level to set (0-15)
     */
    void setActivePower(@Range(from = 0, to = 15) int power);
}
