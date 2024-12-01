package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCompoundCreatorBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCompoundCreatorBehaviorImpl extends BlockBehaviorImpl implements BlockCompoundCreatorBehavior {
    public BlockCompoundCreatorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
