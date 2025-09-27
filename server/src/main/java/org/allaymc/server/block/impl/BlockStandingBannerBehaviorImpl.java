package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockStandingBannerBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockStandingBannerBehaviorImpl extends BlockBehaviorImpl implements BlockStandingBannerBehavior {
    public BlockStandingBannerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
