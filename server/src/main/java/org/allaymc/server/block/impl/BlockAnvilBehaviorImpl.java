package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockAnvilBaseComponent;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.interfaces.BlockAnvilBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockAnvilBehaviorImpl} implements anvil block behavior through
 * components supplied by its caller. Its constructor delegates initialization to
 * {@code BlockBehaviorImpl}, and Lombok-generated methods forward the specialized
 * {@code BlockAnvilBehavior} contracts to the injected component fields.
 */
public class BlockAnvilBehaviorImpl extends BlockBehaviorImpl implements BlockAnvilBehavior {
    // Superclass construction injects fallableComponent and anvilBaseComponent
    // from the supplied providers. Each field must match exactly one compatible
    // component before the generated delegation methods can use it.
    /**
     * Anvil falling behavior delegate.
     */
    @Delegate
    private BlockFallableComponent fallableComponent;
    /**
     * Anvil damage-transition delegate.
     */
    @Delegate
    private BlockAnvilBaseComponent anvilBaseComponent;

    /**
     * Creates anvil behavior from the supplied component providers.
     * {@code componentProviders} must supply exactly one compatible component for
     * every delegate field, including inherited fields. Missing or ambiguous
     * matches abort construction without rolling back initialization side effects
     * already performed.
     */
    public BlockAnvilBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
