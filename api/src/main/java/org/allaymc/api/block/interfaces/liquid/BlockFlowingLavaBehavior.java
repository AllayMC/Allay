package org.allaymc.api.block.interfaces.liquid;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockLiquidComponent;

public interface BlockFlowingLavaBehavior extends BlockBehavior, BlockLiquidComponent {
    @Override
    default boolean canResetFallDistance() {
        return true;
    }
}
