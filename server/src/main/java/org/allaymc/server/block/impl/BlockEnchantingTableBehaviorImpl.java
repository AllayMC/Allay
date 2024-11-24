package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockEnchantingTableBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityEnchantTable;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockEnchantingTableBehaviorImpl extends BlockBehaviorImpl implements BlockEnchantingTableBehavior {
    @Delegate
    protected BlockEntityHolderComponent<BlockEntityEnchantTable> blockEntityHolderComponent;

    public BlockEnchantingTableBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
