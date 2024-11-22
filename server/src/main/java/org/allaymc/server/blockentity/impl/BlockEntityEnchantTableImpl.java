package org.allaymc.server.blockentity.impl;

import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityEnchantTable;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class BlockEntityEnchantTableImpl extends BlockEntityImpl implements BlockEntityEnchantTable {
    public BlockEntityEnchantTableImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
