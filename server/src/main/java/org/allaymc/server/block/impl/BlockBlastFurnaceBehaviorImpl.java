package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockBlastFurnaceBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityBlastFurnace;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBlastFurnaceBehaviorImpl extends BlockBehaviorImpl implements BlockBlastFurnaceBehavior {

    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityBlastFurnace> blockEntityHolderComponent;

    public BlockBlastFurnaceBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
