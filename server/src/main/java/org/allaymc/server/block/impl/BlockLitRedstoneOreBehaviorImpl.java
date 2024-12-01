package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLitRedstoneOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLitRedstoneOreBehaviorImpl extends BlockBehaviorImpl implements BlockLitRedstoneOreBehavior {
    public BlockLitRedstoneOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
