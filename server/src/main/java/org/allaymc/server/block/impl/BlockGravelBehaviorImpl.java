package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.interfaces.BlockGravelBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockGravelBehaviorImpl} implements server behavior for gravel blocks
 * through component delegation. {@code BlockBehaviorImpl} supplies inherited block
 * operations, while injected component fields expose falling block impact operations
 * through the {@code BlockGravelBehavior} contract. Component providers determine the
 * implementations used by those delegates.
 */
public class BlockGravelBehaviorImpl extends BlockBehaviorImpl implements BlockGravelBehavior {
    /**
     * {@code fallableComponent} holds the injected falling block impact delegate.
     * Component initialization selects exactly one supplied instance assignable to
     * {@code BlockFallableComponent}.
     */
    @Delegate
    private BlockFallableComponent fallableComponent;

    /**
     * Creates gravel behavior using the supplied component providers.
     * {@code componentProviders} must supply exactly one implementation for every
     * inherited and declared component field; missing or ambiguous implementations
     * abort component initialization.
     */
    public BlockGravelBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
