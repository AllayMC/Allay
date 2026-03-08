package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityDispenserBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityDropper;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class BlockEntityDropperImpl extends BlockEntityImpl implements BlockEntityDropper {

    @Delegate
    private BlockEntityContainerHolderComponent containerHolderComponent;
    @Delegate
    private BlockEntityDispenserBaseComponent dispenserBaseComponent;

    public BlockEntityDropperImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
