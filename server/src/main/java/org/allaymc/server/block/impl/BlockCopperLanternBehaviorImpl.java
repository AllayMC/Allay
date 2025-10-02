package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCopperLanternBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCopperLanternBehaviorImpl extends BlockBehaviorImpl implements BlockCopperLanternBehavior {
    public BlockCopperLanternBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
