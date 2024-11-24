package org.allaymc.server.block.impl.planks;

import java.util.List;
import org.allaymc.api.block.interfaces.planks.BlockMangrovePlanksBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockMangrovePlanksBehaviorImpl extends BlockBehaviorImpl implements BlockMangrovePlanksBehavior {
    public BlockMangrovePlanksBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
