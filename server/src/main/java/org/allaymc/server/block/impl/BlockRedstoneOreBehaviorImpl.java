package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRedstoneOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockRedstoneOreBehaviorImpl extends BlockBehaviorImpl implements BlockRedstoneOreBehavior {
    public BlockRedstoneOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
