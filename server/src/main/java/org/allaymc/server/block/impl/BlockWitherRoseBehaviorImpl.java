package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWitherRoseBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWitherRoseBehaviorImpl extends BlockBehaviorImpl implements BlockWitherRoseBehavior {
    public BlockWitherRoseBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
