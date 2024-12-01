package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockQuartzPillarBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockQuartzPillarBehaviorImpl extends BlockBehaviorImpl implements BlockQuartzPillarBehavior {
    public BlockQuartzPillarBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
