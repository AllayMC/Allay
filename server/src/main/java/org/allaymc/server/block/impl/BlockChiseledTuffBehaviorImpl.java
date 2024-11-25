package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockChiseledTuffBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockChiseledTuffBehaviorImpl extends BlockBehaviorImpl implements BlockChiseledTuffBehavior {
    public BlockChiseledTuffBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
