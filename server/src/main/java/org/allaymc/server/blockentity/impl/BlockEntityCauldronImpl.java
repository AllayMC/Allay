package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityCauldronBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityCauldron;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * Implementation of the cauldron block entity.
 *
 * @author daoge_cmd
 */
public class BlockEntityCauldronImpl extends BlockEntityImpl implements BlockEntityCauldron {

    @Delegate
    private BlockEntityCauldronBaseComponent cauldronBaseComponent;

    public BlockEntityCauldronImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
