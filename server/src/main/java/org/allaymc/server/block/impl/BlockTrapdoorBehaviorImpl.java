package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTrapdoorBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockTrapdoorBehaviorImpl} implements server behavior for trapdoor blocks
 * through inherited component delegation. {@code BlockBehaviorImpl} supplies general
 * block operations through its base and state data components, while this type
 * exposes the {@code BlockTrapdoorBehavior} contract to callers.
 */
public class BlockTrapdoorBehaviorImpl extends BlockBehaviorImpl implements BlockTrapdoorBehavior {
    /**
     * Creates trapdoor behavior using the supplied component providers.
     * {@code componentProviders} must supply exactly one implementation for every
     * inherited and declared component field; missing or ambiguous implementations
     * abort component initialization.
     */
    public BlockTrapdoorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
        ;
    }
}
