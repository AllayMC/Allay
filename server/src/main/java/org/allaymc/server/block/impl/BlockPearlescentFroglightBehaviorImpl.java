package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPearlescentFroglightBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPearlescentFroglightBehaviorImpl extends BlockBehaviorImpl implements BlockPearlescentFroglightBehavior {
    public BlockPearlescentFroglightBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
