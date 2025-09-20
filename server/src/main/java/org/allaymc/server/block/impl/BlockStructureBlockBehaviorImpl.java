package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockStructureBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockStructureBlockBehaviorImpl extends BlockBehaviorImpl implements BlockStructureBlockBehavior {
    public BlockStructureBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
