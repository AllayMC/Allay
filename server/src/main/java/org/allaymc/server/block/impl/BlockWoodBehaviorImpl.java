package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockStrippableComponent;
import org.allaymc.api.block.interfaces.BlockWoodBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockWoodBehaviorImpl} implements server behavior for wood blocks through
 * component delegation. {@code BlockBehaviorImpl} supplies inherited block
 * operations, while injected component fields expose wood stripping operations
 * through the {@code BlockWoodBehavior} contract. Component providers determine the
 * implementations used by those delegates.
 */
public class BlockWoodBehaviorImpl extends BlockBehaviorImpl implements BlockWoodBehavior {
    /**
     * {@code strippableComponent} holds the injected wood stripping delegate.
     * Component initialization selects exactly one supplied instance assignable to
     * {@code BlockStrippableComponent}.
     */
    @Delegate
    private BlockStrippableComponent strippableComponent;

    /**
     * Creates wood behavior using the supplied component providers.
     * {@code componentProviders} must supply exactly one implementation for every
     * inherited and declared component field; missing or ambiguous implementations
     * abort component initialization.
     */
    public BlockWoodBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
