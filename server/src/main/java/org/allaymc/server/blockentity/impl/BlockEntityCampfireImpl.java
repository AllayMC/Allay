package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityCampfireBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityCampfire;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class BlockEntityCampfireImpl extends BlockEntityImpl implements BlockEntityCampfire {
    @Delegate
    private BlockEntityCampfireBaseComponent campfireBaseComponent;

    public BlockEntityCampfireImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
