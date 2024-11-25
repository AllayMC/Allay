package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDandelionBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDandelionBehaviorImpl extends BlockBehaviorImpl implements BlockDandelionBehavior {
    public BlockDandelionBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
