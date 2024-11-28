package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCrackedDeepslateTilesBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCrackedDeepslateTilesBehaviorImpl extends BlockBehaviorImpl implements BlockCrackedDeepslateTilesBehavior {
    public BlockCrackedDeepslateTilesBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
