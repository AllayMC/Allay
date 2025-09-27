package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockChalkboardBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockChalkboardBehaviorImpl extends BlockBehaviorImpl implements BlockChalkboardBehavior {
    public BlockChalkboardBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
