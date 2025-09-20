package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPowderSnowBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPowderSnowBehaviorImpl extends BlockBehaviorImpl implements BlockPowderSnowBehavior {
    public BlockPowderSnowBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
