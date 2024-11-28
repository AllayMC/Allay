package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCopperGrateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCopperGrateBehaviorImpl extends BlockBehaviorImpl implements BlockCopperGrateBehavior {
    public BlockCopperGrateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
