package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockChiseledCinnabarBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockChiseledCinnabarBehaviorImpl extends BlockBehaviorImpl implements BlockChiseledCinnabarBehavior {
    public BlockChiseledCinnabarBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
