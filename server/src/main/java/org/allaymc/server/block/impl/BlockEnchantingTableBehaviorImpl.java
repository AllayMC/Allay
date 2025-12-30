package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockEnchantingTableBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityEnchantTable;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockEnchantingTableBehaviorImpl extends BlockBehaviorImpl implements BlockEnchantingTableBehavior {
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityEnchantTable> blockEntityHolderComponent;

    public BlockEnchantingTableBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
