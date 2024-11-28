package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMangroveRootsBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockMangroveRootsBehaviorImpl extends BlockBehaviorImpl implements BlockMangroveRootsBehavior {
    public BlockMangroveRootsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
