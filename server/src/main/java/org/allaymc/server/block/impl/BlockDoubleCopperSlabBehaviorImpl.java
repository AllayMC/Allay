package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.interfaces.BlockDoubleCopperSlabBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockDoubleCopperSlabBehaviorImpl} implements server behavior for double
 * copper slab blocks through component delegation.
 * {@code BlockDoubleSlabBehaviorImpl} supplies inherited block operations, while
 * injected component fields expose oxidation and waxing operations through the
 * {@code BlockDoubleCopperSlabBehavior} contract. Component providers determine the
 * implementations used by those delegates.
 */
public class BlockDoubleCopperSlabBehaviorImpl extends BlockDoubleSlabBehaviorImpl implements BlockDoubleCopperSlabBehavior {
    /**
     * {@code oxidationComponent} holds the injected oxidation and waxing delegate.
     * Component initialization selects exactly one supplied instance assignable to
     * {@code BlockOxidationComponent}.
     */
    @Delegate
    private BlockOxidationComponent oxidationComponent;

    /**
     * Creates double copper slab behavior using the supplied component providers.
     * {@code componentProviders} must supply exactly one implementation for every
     * inherited and declared component field; missing or ambiguous implementations
     * abort component initialization.
     */
    public BlockDoubleCopperSlabBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
