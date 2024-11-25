package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCrimsonFungusBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCrimsonFungusBehaviorImpl extends BlockBehaviorImpl implements BlockCrimsonFungusBehavior {
    public BlockCrimsonFungusBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
