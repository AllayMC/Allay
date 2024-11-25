package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCocoaBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCocoaBehaviorImpl extends BlockBehaviorImpl implements BlockCocoaBehavior {
    public BlockCocoaBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
