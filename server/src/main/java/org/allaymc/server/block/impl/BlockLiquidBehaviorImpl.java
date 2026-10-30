package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockLiquidBaseComponent;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockLiquidBehaviorImpl} composes ordinary block behavior with a liquid component
 * selected from the supplied providers. Component initialization injects the liquid delegate
 * before construction returns, and generated delegation exposes its depth, flow, containment,
 * and hardening contract through {@code BlockLiquidBehavior}.
 */
public class BlockLiquidBehaviorImpl extends BlockBehaviorImpl implements BlockLiquidBehavior {

    /**
     * {@code liquidBaseComponent} holds the injected liquid delegate. Component construction
     * requires one matching supplied component, and generated methods forward liquid
     * operations to that same instance.
     */
    @Delegate
    private BlockLiquidBaseComponent liquidBaseComponent;

    /**
     * Creates the composed liquid behavior and initializes its supplied components. The
     * provider list must resolve the inherited block delegates and one liquid delegate;
     * injection failures abort construction without rolling back earlier provider effects.
     */
    public BlockLiquidBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
