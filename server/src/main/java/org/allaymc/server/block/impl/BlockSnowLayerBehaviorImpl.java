package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSnowLayerBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSnowLayerBehaviorImpl extends BlockBehaviorImpl implements BlockSnowLayerBehavior {
    public BlockSnowLayerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
