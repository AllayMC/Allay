package org.allaymc.server.block.impl.furnace;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.furnace.BlockFurnaceBehavior;
import org.allaymc.api.block.interfaces.furnace.BlockLitFurnaceBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityFurnace;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockFurnaceBehaviorImpl extends BlockBehaviorImpl implements
        BlockFurnaceBehavior, BlockLitFurnaceBehavior {

    @Delegate
    protected BlockEntityHolderComponent<BlockEntityFurnace> blockEntityHolderComponent;

    public BlockFurnaceBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
