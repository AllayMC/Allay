package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMossBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockMossBlockBehaviorImpl extends BlockBehaviorImpl implements BlockMossBlockBehavior {
    public BlockMossBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
