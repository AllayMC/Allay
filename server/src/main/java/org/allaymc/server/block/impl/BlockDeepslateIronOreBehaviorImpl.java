package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDeepslateIronOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDeepslateIronOreBehaviorImpl extends BlockBehaviorImpl implements BlockDeepslateIronOreBehavior {
    public BlockDeepslateIronOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
