package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockRespawnPointComponent;
import org.allaymc.api.block.interfaces.BlockRespawnAnchorBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockRespawnAnchorBehaviorImpl} implements respawn anchor block behavior
 * through components supplied by its caller. Its constructor delegates
 * initialization to {@code BlockBehaviorImpl}, and Lombok-generated methods
 * forward the specialized {@code BlockRespawnAnchorBehavior} contracts to the
 * injected component fields.
 */
public class BlockRespawnAnchorBehaviorImpl extends BlockBehaviorImpl implements BlockRespawnAnchorBehavior {
    /**
     * {@code blockRespawnPointComponent} provides player respawn location
     * handling. Superclass construction injects this delegate from the supplied
     * providers before generated delegation methods can use it.
     */
    @Delegate
    private BlockRespawnPointComponent blockRespawnPointComponent;

    /**
     * Creates respawn anchor behavior from the supplied component providers.
     * {@code componentProviders} must supply exactly one compatible component for
     * every delegate field, including inherited fields. Missing or ambiguous
     * matches abort construction without rolling back initialization side effects
     * already performed.
     */
    public BlockRespawnAnchorBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
