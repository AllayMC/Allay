package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockChiseledSulfurBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockChiseledSulfurBehaviorImpl extends BlockBehaviorImpl implements BlockChiseledSulfurBehavior {
    public BlockChiseledSulfurBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
