package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockStairsBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockStairsBehaviorImpl extends BlockBehaviorImpl implements BlockStairsBehavior {
    public BlockStairsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
