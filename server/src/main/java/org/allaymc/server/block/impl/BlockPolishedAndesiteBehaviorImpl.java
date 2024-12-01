package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPolishedAndesiteBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPolishedAndesiteBehaviorImpl extends BlockBehaviorImpl implements BlockPolishedAndesiteBehavior {
    public BlockPolishedAndesiteBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
