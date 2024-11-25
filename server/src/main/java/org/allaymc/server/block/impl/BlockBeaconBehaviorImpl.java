package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBeaconBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBeaconBehaviorImpl extends BlockBehaviorImpl implements BlockBeaconBehavior {
    public BlockBeaconBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
