package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityDispenserBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityDispenser;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class BlockEntityDispenserImpl extends BlockEntityImpl implements BlockEntityDispenser {

    @Delegate
    private BlockEntityContainerHolderComponent containerHolderComponent;
    @Delegate
    private BlockEntityDispenserBaseComponent dispenserBaseComponent;

    public BlockEntityDispenserImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
