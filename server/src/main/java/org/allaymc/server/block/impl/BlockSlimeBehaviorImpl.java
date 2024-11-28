package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSlimeBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSlimeBehaviorImpl extends BlockBehaviorImpl implements BlockSlimeBehavior {
    public BlockSlimeBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
