package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.component.BlockLecternBaseComponent;
import org.allaymc.api.block.interfaces.BlockLecternBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityLectern;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockLecternBehaviorImpl extends BlockBehaviorImpl implements BlockLecternBehavior {

    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityLectern> blockEntityHolderComponent;

    @Delegate
    private BlockLecternBaseComponent lecternBaseComponent;

    public BlockLecternBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
