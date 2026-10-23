package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockInfestedBlockBaseComponent;
import org.allaymc.api.block.interfaces.BlockInfestedBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockInfestedBlockBehaviorImpl} implements server behavior for infested
 * blocks through component delegation. {@code BlockBehaviorImpl} supplies inherited
 * block operations, while injected component fields expose imitated block lookup
 * operations through the {@code BlockInfestedBlockBehavior} contract. Component
 * providers determine the implementations used by those delegates.
 */
public class BlockInfestedBlockBehaviorImpl extends BlockBehaviorImpl implements BlockInfestedBlockBehavior {

    /**
     * {@code infestedBlockBaseComponent} holds the injected imitated block lookup
     * delegate. Component initialization selects exactly one supplied instance
     * assignable to {@code BlockInfestedBlockBaseComponent}.
     */
    @Delegate
    private BlockInfestedBlockBaseComponent infestedBlockBaseComponent;

    /**
     * Creates infested block behavior using the supplied component providers.
     * {@code componentProviders} must supply exactly one implementation for every
     * inherited and declared component field; missing or ambiguous implementations
     * abort component initialization.
     */
    public BlockInfestedBlockBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
