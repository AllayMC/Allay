package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSoulCampfireBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSoulCampfireBehaviorImpl extends BlockBehaviorImpl implements BlockSoulCampfireBehavior {
    public BlockSoulCampfireBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
