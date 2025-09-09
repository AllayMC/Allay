package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMagmaBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockMagmaBehaviorImpl extends BlockBehaviorImpl implements BlockMagmaBehavior {
    public BlockMagmaBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
