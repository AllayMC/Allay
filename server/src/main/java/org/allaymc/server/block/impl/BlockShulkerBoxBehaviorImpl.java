package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockShulkerBoxBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityShulkerBox;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockShulkerBoxBehaviorImpl} implements shulker box block behavior
 * through components supplied by its caller. Its constructor delegates
 * initialization to {@code BlockBehaviorImpl}, and Lombok-generated methods
 * forward the specialized {@code BlockShulkerBoxBehavior} contracts to the
 * injected component fields.
 */
public class BlockShulkerBoxBehaviorImpl extends BlockBehaviorImpl implements BlockShulkerBoxBehavior {
    /**
     * {@code blockEntityHolderComponent} provides typed block entity access.
     * Superclass construction injects this delegate from the supplied providers
     * before generated delegation methods can use it.
     */
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityShulkerBox> blockEntityHolderComponent;

    /**
     * Creates shulker box behavior from the supplied component providers.
     * {@code componentProviders} must supply exactly one compatible component for
     * every delegate field, including inherited fields. Missing or ambiguous
     * matches abort construction without rolling back initialization side effects
     * already performed.
     */
    public BlockShulkerBoxBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
