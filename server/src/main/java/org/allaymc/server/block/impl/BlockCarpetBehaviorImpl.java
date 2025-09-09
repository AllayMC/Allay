package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCarpetBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCarpetBehaviorImpl extends BlockBehaviorImpl implements BlockCarpetBehavior {
    public BlockCarpetBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
