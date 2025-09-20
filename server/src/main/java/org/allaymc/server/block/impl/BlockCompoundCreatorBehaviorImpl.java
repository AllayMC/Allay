package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCompoundCreatorBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCompoundCreatorBehaviorImpl extends BlockBehaviorImpl implements BlockCompoundCreatorBehavior {
    public BlockCompoundCreatorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
