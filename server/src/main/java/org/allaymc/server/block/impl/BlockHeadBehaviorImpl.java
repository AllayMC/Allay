package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockHeadBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockHeadBehaviorImpl extends BlockBehaviorImpl implements BlockHeadBehavior {
    public BlockHeadBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
