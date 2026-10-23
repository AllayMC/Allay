package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockSlabBaseComponent;
import org.allaymc.api.block.interfaces.BlockSlabBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockSlabBehaviorImpl} implements server behavior for slab blocks through
 * component delegation. {@code BlockBehaviorImpl} supplies inherited block
 * operations, while injected component fields expose double slab lookup operations
 * through the {@code BlockSlabBehavior} contract. Component providers determine the
 * implementations used by those delegates.
 */
public class BlockSlabBehaviorImpl extends BlockBehaviorImpl implements BlockSlabBehavior {

    /**
     * {@code slabBaseComponent} holds the injected double slab lookup delegate.
     * Component initialization selects exactly one supplied instance assignable to
     * {@code BlockSlabBaseComponent}.
     */
    @Delegate
    private BlockSlabBaseComponent slabBaseComponent;

    /**
     * Creates slab behavior using the supplied component providers.
     * {@code componentProviders} must supply exactly one implementation for every
     * inherited and declared component field; missing or ambiguous implementations
     * abort component initialization.
     */
    public BlockSlabBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
