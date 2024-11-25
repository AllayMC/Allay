package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockInvisibleBedrockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockInvisibleBedrockBehaviorImpl extends BlockBehaviorImpl implements BlockInvisibleBedrockBehavior {
    public BlockInvisibleBedrockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
