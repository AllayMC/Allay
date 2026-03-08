package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBannerBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityBanner;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * Implementation of the banner block entity.
 *
 * @author daoge_cmd
 */
public class BlockEntityBannerImpl extends BlockEntityImpl implements BlockEntityBanner {

    @Delegate
    private BlockEntityBannerBaseComponent bannerBaseComponent;

    public BlockEntityBannerImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
