package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCampfireBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCampfireBehaviorImpl extends BlockBehaviorImpl implements BlockCampfireBehavior {
    public BlockCampfireBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
