package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.interfaces.BlockSnowLayerBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockSnowLayerBehaviorImpl} composes ordinary block behavior with a falling-block
 * component for snow layers. Construction resolves the fallable delegate from the supplied
 * providers, and generated methods expose that component through the snow-layer behavior
 * contract alongside inherited block operations.
 */
public class BlockSnowLayerBehaviorImpl extends BlockBehaviorImpl implements BlockSnowLayerBehavior {
    /**
     * {@code fallableComponent} holds the injected falling-block delegate. Component
     * initialization requires one matching supplied instance before generated snow-layer
     * falling methods can forward their calls.
     */
    @Delegate
    private BlockFallableComponent fallableComponent;

    /**
     * Creates snow-layer behavior from the supplied component providers. Construction must
     * resolve the inherited delegates and one fallable component; provider or injection
     * failures abort without rolling back previously performed provider effects.
     */
    public BlockSnowLayerBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
