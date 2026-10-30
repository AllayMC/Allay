package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.interfaces.BlockSandBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockSandBehaviorImpl} implements server behavior for sand blocks through
 * component delegation. {@code BlockBehaviorImpl} supplies inherited block
 * operations, while injected component fields expose falling block impact operations
 * through the {@code BlockSandBehavior} contract. Component providers determine the
 * implementations used by those delegates.
 */
public class BlockSandBehaviorImpl extends BlockBehaviorImpl implements BlockSandBehavior {
    /**
     * {@code fallableComponent} holds the injected falling block impact delegate.
     * Component initialization selects exactly one supplied instance assignable to
     * {@code BlockFallableComponent}.
     */
    @Delegate
    private BlockFallableComponent fallableComponent;

    /**
     * Creates sand behavior using the supplied component providers.
     * {@code componentProviders} must supply exactly one implementation for every
     * inherited and declared component field; missing or ambiguous implementations
     * abort component initialization.
     */
    public BlockSandBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
