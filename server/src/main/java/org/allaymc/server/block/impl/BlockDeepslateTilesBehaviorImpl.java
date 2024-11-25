package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDeepslateTilesBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDeepslateTilesBehaviorImpl extends BlockBehaviorImpl implements BlockDeepslateTilesBehavior {
    public BlockDeepslateTilesBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
