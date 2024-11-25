package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDeepslateGoldOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDeepslateGoldOreBehaviorImpl extends BlockBehaviorImpl implements BlockDeepslateGoldOreBehavior {
    public BlockDeepslateGoldOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
