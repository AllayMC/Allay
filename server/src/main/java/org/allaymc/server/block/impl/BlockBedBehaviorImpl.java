package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.component.BlockRespawnPointComponent;
import org.allaymc.api.block.interfaces.BlockBedBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityBed;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockBedBehaviorImpl} implements bed block behavior through components
 * supplied by its caller. Its constructor delegates initialization to
 * {@code BlockBehaviorImpl}, and Lombok-generated methods forward the specialized
 * {@code BlockBedBehavior} contracts to the injected component fields.
 */
public class BlockBedBehaviorImpl extends BlockBehaviorImpl implements BlockBedBehavior {

    // Superclass construction injects blockEntityHolderComponent and
    // blockRespawnPointComponent from the supplied providers. Each field must
    // match exactly one compatible component before the generated delegation
    // methods can use it.
    /**
     * Typed bed entity access delegate.
     */
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityBed> blockEntityHolderComponent;
    /**
     * Player respawn-location handling delegate.
     */
    @Delegate
    private BlockRespawnPointComponent blockRespawnPointComponent;

    /**
     * Creates bed behavior from the supplied component providers.
     * {@code componentProviders} must supply exactly one compatible component for
     * every delegate field, including inherited fields. Missing or ambiguous
     * matches abort construction without rolling back initialization side effects
     * already performed.
     */
    public BlockBedBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
