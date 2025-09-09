package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockUnknownBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockUnknownBehaviorImpl extends BlockBehaviorImpl implements BlockUnknownBehavior {
    public BlockUnknownBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
