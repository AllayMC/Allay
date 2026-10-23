package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockIronTrapdoorBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockIronTrapdoorBehaviorImpl} implements server behavior for iron trapdoor
 * blocks through inherited component delegation. {@code BlockBehaviorImpl} supplies
 * general block operations through its base and state data components, while this
 * type exposes the {@code BlockIronTrapdoorBehavior} contract to callers.
 */
public class BlockIronTrapdoorBehaviorImpl extends BlockBehaviorImpl implements BlockIronTrapdoorBehavior {
    /**
     * Creates iron trapdoor behavior using the supplied component providers.
     * {@code componentProviders} must supply exactly one implementation for every
     * inherited and declared component field; missing or ambiguous implementations
     * abort component initialization.
     */
    public BlockIronTrapdoorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
        ;
    }
}
