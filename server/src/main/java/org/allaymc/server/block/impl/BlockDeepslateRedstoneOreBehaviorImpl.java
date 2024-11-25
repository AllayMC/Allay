package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDeepslateRedstoneOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDeepslateRedstoneOreBehaviorImpl extends BlockBehaviorImpl implements BlockDeepslateRedstoneOreBehavior {
    public BlockDeepslateRedstoneOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
