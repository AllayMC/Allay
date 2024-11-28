package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMangrovePressurePlateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockMangrovePressurePlateBehaviorImpl extends BlockBehaviorImpl implements BlockMangrovePressurePlateBehavior {
    public BlockMangrovePressurePlateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
