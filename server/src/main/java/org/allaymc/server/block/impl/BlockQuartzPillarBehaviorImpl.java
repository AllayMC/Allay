package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockQuartzPillarBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockQuartzPillarBehaviorImpl extends BlockBehaviorImpl implements BlockQuartzPillarBehavior {
    public BlockQuartzPillarBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
