package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLitDeepslateRedstoneOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLitDeepslateRedstoneOreBehaviorImpl extends BlockBehaviorImpl implements BlockLitDeepslateRedstoneOreBehavior {
    public BlockLitDeepslateRedstoneOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
