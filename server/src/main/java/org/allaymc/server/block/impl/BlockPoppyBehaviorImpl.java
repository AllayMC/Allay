package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPoppyBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPoppyBehaviorImpl extends BlockBehaviorImpl implements BlockPoppyBehavior {
    public BlockPoppyBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
