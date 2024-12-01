package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockIronOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockIronOreBehaviorImpl extends BlockBehaviorImpl implements BlockIronOreBehavior {
    public BlockIronOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
