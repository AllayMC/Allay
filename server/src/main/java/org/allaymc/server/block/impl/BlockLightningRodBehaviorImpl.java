package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.interfaces.BlockLightningRodBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockLightningRodBehaviorImpl} composes lightning rod behavior from the
 * supplied components and exposes {@link BlockLightningRodBehavior}. Inherited
 * delegates provide base block operations, while {@code oxidationComponent} supplies
 * oxidation and waxing variant lookup through generated delegation methods.
 */
public class BlockLightningRodBehaviorImpl extends BlockBehaviorImpl implements BlockLightningRodBehavior {
    /**
     * {@code oxidationComponent} is injected from the supplied components during
     * superclass construction. Generated delegation methods forward oxidation and
     * waxing variant lookup to that retained component.
     */
    @Delegate
    private BlockOxidationComponent oxidationComponent;

    /**
     * Creates the composed behavior from providers initialized by superclass
     * construction. The provider list must supply exactly one implementation for each
     * delegated component type, including inherited base and state-data delegates.
     */
    public BlockLightningRodBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
