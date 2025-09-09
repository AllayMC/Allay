package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockChiseledTuffBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockChiseledTuffBehaviorImpl extends BlockBehaviorImpl implements BlockChiseledTuffBehavior {
    public BlockChiseledTuffBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
