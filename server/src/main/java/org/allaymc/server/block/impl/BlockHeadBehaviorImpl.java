package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockHeadBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityHead;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockHeadBehaviorImpl} implements server behavior for head blocks through
 * component delegation. {@code BlockBehaviorImpl} supplies inherited block
 * operations, while injected component fields expose block entity access operations
 * through the {@code BlockHeadBehavior} contract. Component providers determine the
 * implementations used by those delegates.
 */
public class BlockHeadBehaviorImpl extends BlockBehaviorImpl implements BlockHeadBehavior {
    /**
     * {@code blockEntityHolderComponent} provides typed block entity access.
     * Initialization matches only the erased {@code BlockBlockEntityHolderComponent}
     * type, so providers must supply a holder for {@code BlockEntityHead}.
     */
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityHead> blockEntityHolderComponent;

    /**
     * Creates head behavior using the supplied component providers.
     * {@code componentProviders} must supply exactly one implementation for every
     * inherited and declared component field; missing or ambiguous implementations
     * abort component initialization.
     */
    public BlockHeadBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
