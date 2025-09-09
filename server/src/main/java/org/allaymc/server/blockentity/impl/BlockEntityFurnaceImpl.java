package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityFurnaceBaseComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityBlastFurnace;
import org.allaymc.api.blockentity.interfaces.BlockEntityFurnace;
import org.allaymc.api.blockentity.interfaces.BlockEntitySmoker;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class BlockEntityFurnaceImpl extends BlockEntityImpl implements BlockEntityFurnace, BlockEntitySmoker, BlockEntityBlastFurnace {
    @Delegate
    protected BlockEntityContainerHolderComponent containerHolderComponent;

    public BlockEntityFurnaceImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public BlockEntityFurnaceBaseComponent getBaseComponent() {
        return (BlockEntityFurnaceBaseComponent) super.getBaseComponent();
    }
}
