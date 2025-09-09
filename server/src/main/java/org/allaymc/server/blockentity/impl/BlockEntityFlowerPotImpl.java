package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.component.BlockEntityFlowerPotBaseComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityFlowerPot;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author Cdm2883
 */
public class BlockEntityFlowerPotImpl extends BlockEntityImpl implements BlockEntityFlowerPot {
    public BlockEntityFlowerPotImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public BlockEntityFlowerPotBaseComponent getBaseComponent() {
        return (BlockEntityFlowerPotBaseComponent) super.getBaseComponent();
    }
}
