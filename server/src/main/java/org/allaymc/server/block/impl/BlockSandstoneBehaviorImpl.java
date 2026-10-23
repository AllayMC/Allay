package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSandstoneBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockSandstoneBehaviorImpl} implements server behavior for sandstone blocks
 * through inherited component delegation. {@code BlockBehaviorImpl} supplies general
 * block operations through its base and state data components, while this type
 * exposes the {@code BlockSandstoneBehavior} contract to callers.
 */
public class BlockSandstoneBehaviorImpl extends BlockBehaviorImpl implements BlockSandstoneBehavior {
    /**
     * Creates sandstone behavior using the supplied component providers.
     * {@code componentProviders} must supply exactly one implementation for every
     * inherited and declared component field; missing or ambiguous implementations
     * abort component initialization.
     */
    public BlockSandstoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
        ;
    }
}
