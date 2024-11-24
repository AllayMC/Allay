package org.allaymc.server.block.impl.slab;

import java.util.List;
import org.allaymc.api.block.interfaces.slab.BlockOxidizedDoubleCutCopperSlabBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockOxidizedDoubleCutCopperSlabBehaviorImpl extends BlockBehaviorImpl implements BlockOxidizedDoubleCutCopperSlabBehavior {
    public BlockOxidizedDoubleCutCopperSlabBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
