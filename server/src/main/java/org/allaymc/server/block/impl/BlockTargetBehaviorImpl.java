package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTargetBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockTargetBehaviorImpl extends BlockBehaviorImpl implements BlockTargetBehavior {
    public BlockTargetBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}