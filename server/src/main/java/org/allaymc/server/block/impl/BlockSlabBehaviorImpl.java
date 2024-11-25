package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSlabBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSlabBehaviorImpl extends BlockBehaviorImpl implements BlockSlabBehavior {
    public BlockSlabBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
