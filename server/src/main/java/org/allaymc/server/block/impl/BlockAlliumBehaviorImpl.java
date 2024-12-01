package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockAlliumBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockAlliumBehaviorImpl extends BlockBehaviorImpl implements BlockAlliumBehavior {
    public BlockAlliumBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
