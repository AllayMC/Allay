package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.interfaces.BlockDragonEggBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockDragonEggBehaviorImpl} implements server behavior for dragon egg blocks
 * through component delegation. {@code BlockBehaviorImpl} supplies inherited block
 * operations, while injected component fields expose falling block impact operations
 * through the {@code BlockDragonEggBehavior} contract. Component providers determine
 * the implementations used by those delegates.
 */
public class BlockDragonEggBehaviorImpl extends BlockBehaviorImpl implements BlockDragonEggBehavior {

    /**
     * {@code fallableComponent} holds the injected falling block impact delegate.
     * Component initialization selects exactly one supplied instance assignable to
     * {@code BlockFallableComponent}.
     */
    @Delegate
    private BlockFallableComponent fallableComponent;

    /**
     * Creates dragon egg behavior using the supplied component providers.
     * {@code componentProviders} must supply exactly one implementation for every
     * inherited and declared component field; missing or ambiguous implementations
     * abort component initialization.
     */
    public BlockDragonEggBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
