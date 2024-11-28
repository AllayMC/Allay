package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPeonyBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPeonyBehaviorImpl extends BlockBehaviorImpl implements BlockPeonyBehavior {
    public BlockPeonyBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
