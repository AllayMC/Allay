package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDeepslateEmeraldOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDeepslateEmeraldOreBehaviorImpl extends BlockBehaviorImpl implements BlockDeepslateEmeraldOreBehavior {
    public BlockDeepslateEmeraldOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
