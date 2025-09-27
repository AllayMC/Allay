package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockShortDryGrassBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockShortDryGrassBehaviorImpl extends BlockBehaviorImpl implements BlockShortDryGrassBehavior {
    public BlockShortDryGrassBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
