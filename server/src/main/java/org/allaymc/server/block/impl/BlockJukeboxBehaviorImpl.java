package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockJukeboxBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityJukebox;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockJukeboxBehaviorImpl} implements jukebox block behavior through
 * components supplied by its caller. Its constructor delegates initialization to
 * {@code BlockBehaviorImpl}, and Lombok-generated methods forward the specialized
 * {@code BlockJukeboxBehavior} contracts to the injected component fields.
 */
public class BlockJukeboxBehaviorImpl extends BlockBehaviorImpl implements BlockJukeboxBehavior {
    /**
     * {@code blockEntityHolderComponent} provides typed block entity access.
     * Superclass construction injects this delegate from the supplied providers
     * before generated delegation methods can use it.
     */
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityJukebox> blockEntityHolderComponent;

    /**
     * Creates jukebox behavior from the supplied component providers.
     * {@code componentProviders} must supply exactly one compatible component for
     * every delegate field, including inherited fields. Missing or ambiguous
     * matches abort construction without rolling back initialization side effects
     * already performed.
     */
    public BlockJukeboxBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
