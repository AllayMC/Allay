package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockStructureVoidBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockStructureVoidBehaviorImpl extends BlockBehaviorImpl implements BlockStructureVoidBehavior {
    public BlockStructureVoidBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
