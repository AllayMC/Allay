package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMyceliumBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockMyceliumBehaviorImpl extends BlockBehaviorImpl implements BlockMyceliumBehavior {
    public BlockMyceliumBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
