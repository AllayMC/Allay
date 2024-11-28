package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMagmaBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockMagmaBehaviorImpl extends BlockBehaviorImpl implements BlockMagmaBehavior {
    public BlockMagmaBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
