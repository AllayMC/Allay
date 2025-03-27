package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLeafLitterBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLeafLitterBehaviorImpl extends BlockBehaviorImpl implements BlockLeafLitterBehavior {
    public BlockLeafLitterBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
