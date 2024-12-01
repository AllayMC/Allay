package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSnifferEggBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSnifferEggBehaviorImpl extends BlockBehaviorImpl implements BlockSnifferEggBehavior {
    public BlockSnifferEggBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
