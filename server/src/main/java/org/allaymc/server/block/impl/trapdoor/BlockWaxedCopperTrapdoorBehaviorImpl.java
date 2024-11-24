package org.allaymc.server.block.impl.trapdoor;

import java.util.List;
import org.allaymc.api.block.interfaces.trapdoor.BlockWaxedCopperTrapdoorBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockWaxedCopperTrapdoorBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedCopperTrapdoorBehavior {
    public BlockWaxedCopperTrapdoorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
