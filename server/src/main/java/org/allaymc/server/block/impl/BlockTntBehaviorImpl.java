package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockTntBaseComponent;
import org.allaymc.api.block.interfaces.BlockTntBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockTntBehaviorImpl} implements server behavior for TNT blocks through
 * component delegation. {@code BlockBehaviorImpl} supplies inherited block
 * operations, while injected component fields expose TNT entity priming operations
 * through the {@code BlockTntBehavior} contract. Component providers determine the
 * implementations used by those delegates.
 */
public class BlockTntBehaviorImpl extends BlockBehaviorImpl implements BlockTntBehavior {

    /**
     * {@code tntBaseComponent} holds the injected TNT entity priming delegate.
     * Component initialization selects exactly one supplied instance assignable to
     * {@code BlockTntBaseComponent}.
     */
    @Delegate
    private BlockTntBaseComponent tntBaseComponent;

    /**
     * Creates TNT behavior using the supplied component providers.
     * {@code componentProviders} must supply exactly one implementation for every
     * inherited and declared component field; missing or ambiguous implementations
     * abort component initialization.
     */
    public BlockTntBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
