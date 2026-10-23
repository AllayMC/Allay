package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockComparatorBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityComparator;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockComparatorBehaviorImpl} composes comparator behavior from the supplied
 * components and exposes {@link BlockComparatorBehavior}. Inherited delegates provide
 * base block operations, while {@code blockEntityHolderComponent} supplies access to
 * typed comparator block entities at world positions through generated delegation
 * methods.
 */
public class BlockComparatorBehaviorImpl extends BlockBehaviorImpl implements BlockComparatorBehavior {

    /**
     * {@code blockEntityHolderComponent} is injected from the supplied components
     * during superclass construction. Generated delegation methods forward typed
     * comparator block entity access to that retained component.
     */
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityComparator> blockEntityHolderComponent;

    /**
     * Creates the composed behavior from providers initialized by superclass
     * construction. The provider list must supply exactly one implementation for each
     * delegated component type, including inherited base and state-data delegates.
     */
    public BlockComparatorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
