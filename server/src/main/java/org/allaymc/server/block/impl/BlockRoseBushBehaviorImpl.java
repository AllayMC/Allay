package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRoseBushBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockRoseBushBehaviorImpl extends BlockBehaviorImpl implements BlockRoseBushBehavior {
    public BlockRoseBushBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
