package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockStandingBannerBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityBanner;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockStandingBannerBehaviorImpl extends BlockBehaviorImpl implements BlockStandingBannerBehavior {
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityBanner> blockEntityHolderComponent;

    public BlockStandingBannerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
