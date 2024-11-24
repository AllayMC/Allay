package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.hangingsign.*;
import org.allaymc.api.blockentity.interfaces.BlockEntityHangingSign;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockHangingSignBehaviorImpl extends BlockBehaviorImpl implements BlockAcaciaHangingSignBehavior, BlockBambooHangingSignBehavior, BlockBirchHangingSignBehavior, BlockCherryHangingSignBehavior, BlockCrimsonHangingSignBehavior, BlockDarkOakHangingSignBehavior, BlockJungleHangingSignBehavior, BlockMangroveHangingSignBehavior, BlockOakHangingSignBehavior, BlockSpruceHangingSignBehavior, BlockWarpedHangingSignBehavior {
    @Delegate
    protected BlockEntityHolderComponent<BlockEntityHangingSign> blockEntityHolderComponent;

    public BlockHangingSignBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
