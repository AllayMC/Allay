package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockInvisibleBedrockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockInvisibleBedrockBehaviorImpl extends BlockBehaviorImpl implements BlockInvisibleBedrockBehavior {
    public BlockInvisibleBedrockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
