package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMelonBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockMelonBlockBehaviorImpl extends BlockBehaviorImpl implements BlockMelonBlockBehavior {
    public BlockMelonBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
