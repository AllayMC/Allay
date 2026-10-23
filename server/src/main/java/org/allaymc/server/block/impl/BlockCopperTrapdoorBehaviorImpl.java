package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.interfaces.BlockCopperTrapdoorBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockCopperTrapdoorBehaviorImpl} implements server behavior for copper
 * trapdoor blocks through component delegation. {@code BlockBehaviorImpl} supplies
 * inherited block operations, while injected component fields expose oxidation and
 * waxing operations through the {@code BlockCopperTrapdoorBehavior} contract.
 * Component providers determine the implementations used by those delegates.
 */
public class BlockCopperTrapdoorBehaviorImpl extends BlockBehaviorImpl implements BlockCopperTrapdoorBehavior {
    /**
     * {@code oxidationComponent} holds the injected oxidation and waxing delegate.
     * Component initialization selects exactly one supplied instance assignable to
     * {@code BlockOxidationComponent}.
     */
    @Delegate
    private BlockOxidationComponent oxidationComponent;

    /**
     * Creates copper trapdoor behavior using the supplied component providers.
     * {@code componentProviders} must supply exactly one implementation for every
     * inherited and declared component field; missing or ambiguous implementations
     * abort component initialization.
     */
    public BlockCopperTrapdoorBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
