package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockBlastFurnaceBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityBlastFurnace;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockBlastFurnaceBehaviorImpl} implements blast furnace block behavior
 * through components supplied by its caller. Its constructor delegates
 * initialization to {@code BlockBehaviorImpl}, and Lombok-generated methods
 * forward the specialized {@code BlockBlastFurnaceBehavior} contracts to the
 * injected component fields.
 */
public class BlockBlastFurnaceBehaviorImpl extends BlockBehaviorImpl implements BlockBlastFurnaceBehavior {

    /**
     * {@code blockEntityHolderComponent} provides typed block entity access.
     * Superclass construction injects this delegate from the supplied providers
     * before generated delegation methods can use it.
     */
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityBlastFurnace> blockEntityHolderComponent;

    /**
     * Creates blast furnace behavior from the supplied component providers.
     * {@code componentProviders} must supply exactly one compatible component for
     * every delegate field, including inherited fields. Missing or ambiguous
     * matches abort construction without rolling back initialization side effects
     * already performed.
     */
    public BlockBlastFurnaceBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
