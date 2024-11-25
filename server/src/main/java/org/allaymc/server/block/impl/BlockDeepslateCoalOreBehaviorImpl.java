package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDeepslateCoalOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDeepslateCoalOreBehaviorImpl extends BlockBehaviorImpl implements BlockDeepslateCoalOreBehavior {
    public BlockDeepslateCoalOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
