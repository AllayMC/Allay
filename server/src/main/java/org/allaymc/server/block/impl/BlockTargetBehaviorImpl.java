package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockTargetBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityTarget;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockTargetBehaviorImpl} composes target behavior from the supplied components
 * and exposes {@link BlockTargetBehavior}. Inherited delegates provide base block
 * operations, while {@code blockEntityHolderComponent} supplies access to typed target
 * block entities at world positions through generated delegation methods.
 */
public class BlockTargetBehaviorImpl extends BlockBehaviorImpl implements BlockTargetBehavior {

    /**
     * {@code blockEntityHolderComponent} is injected from the supplied components
     * during superclass construction. Generated delegation methods forward typed target
     * block entity access to that retained component.
     */
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityTarget> blockEntityHolderComponent;

    /**
     * Creates the composed behavior from providers initialized by superclass
     * construction. The provider list must supply exactly one implementation for each
     * delegated component type, including inherited base and state-data delegates.
     */
    public BlockTargetBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
