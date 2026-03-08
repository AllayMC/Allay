package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockWallBannerBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityBanner;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWallBannerBehaviorImpl extends BlockBehaviorImpl implements BlockWallBannerBehavior {
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityBanner> blockEntityHolderComponent;

    public BlockWallBannerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
