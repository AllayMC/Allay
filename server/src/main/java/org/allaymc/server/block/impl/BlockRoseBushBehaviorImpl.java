package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRoseBushBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockRoseBushBehaviorImpl extends BlockBehaviorImpl implements BlockRoseBushBehavior {
    public BlockRoseBushBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
