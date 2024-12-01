package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockStructureVoidBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockStructureVoidBehaviorImpl extends BlockBehaviorImpl implements BlockStructureVoidBehavior {
    public BlockStructureVoidBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
