package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockChiseledQuartzBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockChiseledQuartzBlockBehaviorImpl extends BlockBehaviorImpl implements BlockChiseledQuartzBlockBehavior {
    public BlockChiseledQuartzBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
