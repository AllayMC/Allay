package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMossBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockMossBlockBehaviorImpl extends BlockBehaviorImpl implements BlockMossBlockBehavior {
    public BlockMossBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
