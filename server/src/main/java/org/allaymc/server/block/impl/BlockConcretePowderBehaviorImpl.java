package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockConcretePowderBaseComponent;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.interfaces.BlockConcretePowderBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockConcretePowderBehaviorImpl} implements server behavior for concrete
 * powder blocks through component delegation. {@code BlockBehaviorImpl} supplies
 * inherited block operations, while injected component fields expose falling block
 * impact and concrete powder hardening operations through the
 * {@code BlockConcretePowderBehavior} contract. Component providers determine the
 * implementations used by those delegates.
 */
public class BlockConcretePowderBehaviorImpl extends BlockBehaviorImpl implements BlockConcretePowderBehavior {
    // Superclass construction injects fallableComponent and
    // concretePowderBaseComponent from the supplied providers. Each field must match
    // exactly one instance of its declared raw component type before generated
    // delegates can be used.
    /**
     * Injected falling block impact delegate.
     */
    @Delegate
    private BlockFallableComponent fallableComponent;
    /**
     * Injected concrete powder hardening delegate.
     */
    @Delegate
    private BlockConcretePowderBaseComponent concretePowderBaseComponent;

    /**
     * Creates concrete powder behavior using the supplied component providers.
     * {@code componentProviders} must supply exactly one implementation for every
     * inherited and declared component field; missing or ambiguous implementations
     * abort component initialization.
     */
    public BlockConcretePowderBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
