package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCreakingHeartBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCreakingHeartBehaviorImpl extends BlockBehaviorImpl implements BlockCreakingHeartBehavior {
    public BlockCreakingHeartBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
