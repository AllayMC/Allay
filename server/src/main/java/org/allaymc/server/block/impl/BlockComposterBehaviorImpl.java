package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockComposterBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockComposterBehaviorImpl extends BlockBehaviorImpl implements BlockComposterBehavior {
    public BlockComposterBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
