package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLargeFernBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLargeFernBehaviorImpl extends BlockBehaviorImpl implements BlockLargeFernBehavior {
    public BlockLargeFernBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
