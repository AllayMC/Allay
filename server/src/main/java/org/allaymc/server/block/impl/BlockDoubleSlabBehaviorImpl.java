package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockDoubleSlabBaseComponent;
import org.allaymc.api.block.interfaces.BlockDoubleSlabBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockDoubleSlabBehaviorImpl} implements server behavior for double slab
 * blocks through component delegation. {@code BlockBehaviorImpl} supplies inherited
 * block operations, while injected component fields expose single slab lookup
 * operations through the {@code BlockDoubleSlabBehavior} contract. Component
 * providers determine the implementations used by those delegates.
 */
public class BlockDoubleSlabBehaviorImpl extends BlockBehaviorImpl implements BlockDoubleSlabBehavior {

    /**
     * {@code doubleSlabBaseComponent} holds the injected single slab lookup delegate.
     * Component initialization selects exactly one supplied instance assignable to
     * {@code BlockDoubleSlabBaseComponent}.
     */
    @Delegate
    private BlockDoubleSlabBaseComponent doubleSlabBaseComponent;

    /**
     * Creates double slab behavior using the supplied component providers.
     * {@code componentProviders} must supply exactly one implementation for every
     * inherited and declared component field; missing or ambiguous implementations
     * abort component initialization.
     */
    public BlockDoubleSlabBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
