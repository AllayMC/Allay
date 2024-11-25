package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSeaLanternBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSeaLanternBehaviorImpl extends BlockBehaviorImpl implements BlockSeaLanternBehavior {
    public BlockSeaLanternBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
