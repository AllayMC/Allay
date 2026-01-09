package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityComparatorBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityComparator;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * Implementation of the comparator block entity.
 *
 * @author daoge_cmd
 */
public class BlockEntityComparatorImpl extends BlockEntityImpl implements BlockEntityComparator {

    @Delegate
    private BlockEntityComparatorBaseComponent comparatorBaseComponent;

    public BlockEntityComparatorImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
