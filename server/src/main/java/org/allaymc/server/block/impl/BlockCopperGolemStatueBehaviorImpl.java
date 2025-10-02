package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCopperGolemStatueBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCopperGolemStatueBehaviorImpl extends BlockBehaviorImpl implements BlockCopperGolemStatueBehavior {
    public BlockCopperGolemStatueBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
