package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockAlliumBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockAlliumBehaviorImpl extends BlockBehaviorImpl implements BlockAlliumBehavior {
    public BlockAlliumBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
