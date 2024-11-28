package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCopperOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCopperOreBehaviorImpl extends BlockBehaviorImpl implements BlockCopperOreBehavior {
    public BlockCopperOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
