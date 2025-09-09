package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSculkCatalystBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSculkCatalystBehaviorImpl extends BlockBehaviorImpl implements BlockSculkCatalystBehavior {
    public BlockSculkCatalystBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
