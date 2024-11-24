package org.allaymc.server.block.impl.shulkerbox;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.shulkerbox.*;
import org.allaymc.api.blockentity.interfaces.BlockEntityShulkerBox;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockShulkerBoxBehaviorImpl extends BlockBehaviorImpl implements
        BlockYellowShulkerBoxBehavior, BlockWhiteShulkerBoxBehavior, BlockUndyedShulkerBoxBehavior, BlockRedShulkerBoxBehavior, BlockPurpleShulkerBoxBehavior,
        BlockPinkShulkerBoxBehavior, BlockOrangeShulkerBoxBehavior, BlockMagentaShulkerBoxBehavior, BlockLimeShulkerBoxBehavior, BlockLightGrayShulkerBoxBehavior,
        BlockLightBlueShulkerBoxBehavior, BlockGreenShulkerBoxBehavior, BlockGrayShulkerBoxBehavior, BlockCyanShulkerBoxBehavior, BlockBrownShulkerBoxBehavior,
        BlockBlueShulkerBoxBehavior, BlockBlackShulkerBoxBehavior {
    @Delegate
    protected BlockEntityHolderComponent<BlockEntityShulkerBox> blockEntityHolderComponent;

    public BlockShulkerBoxBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
