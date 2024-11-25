package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGoldenRailBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockGoldenRailBehaviorImpl extends BlockBehaviorImpl implements BlockGoldenRailBehavior {
    public BlockGoldenRailBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
