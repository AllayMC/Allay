package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockQuartzOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockQuartzOreBehaviorImpl extends BlockBehaviorImpl implements BlockQuartzOreBehavior {
    public BlockQuartzOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
