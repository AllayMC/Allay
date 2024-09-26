package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;

public interface BlockHayBlockBehavior extends BlockBehavior {
    @Override
    default float getFallDamageReductionFactor() {
        return 0.8f;
    }
}
