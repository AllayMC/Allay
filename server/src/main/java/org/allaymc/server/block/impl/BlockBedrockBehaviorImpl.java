package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBedrockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBedrockBehaviorImpl extends BlockBehaviorImpl implements BlockBedrockBehavior {
    public BlockBedrockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
