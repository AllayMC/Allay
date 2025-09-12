package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMyceliumBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockMyceliumBehaviorImpl extends BlockBehaviorImpl implements BlockMyceliumBehavior {
    public BlockMyceliumBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
