package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPowderSnowBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPowderSnowBehaviorImpl extends BlockBehaviorImpl implements BlockPowderSnowBehavior {
    public BlockPowderSnowBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
