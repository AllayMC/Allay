package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLilacBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockLilacBehaviorImpl extends BlockBehaviorImpl implements BlockLilacBehavior {
    public BlockLilacBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
