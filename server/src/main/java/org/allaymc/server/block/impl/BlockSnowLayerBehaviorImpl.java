package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.interfaces.BlockSnowLayerBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSnowLayerBehaviorImpl extends BlockBehaviorImpl implements BlockSnowLayerBehavior {
    @Delegate
    private BlockFallableComponent fallableComponent;

    public BlockSnowLayerBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
