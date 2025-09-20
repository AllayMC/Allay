package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLightBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockLightBlockBehaviorImpl extends BlockBehaviorImpl implements BlockLightBlockBehavior {
    public BlockLightBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
