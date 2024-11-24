package org.allaymc.server.block.impl.trapdoor;

import java.util.List;
import org.allaymc.api.block.interfaces.trapdoor.BlockCherryTrapdoorBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockCherryTrapdoorBehaviorImpl extends BlockBehaviorImpl implements BlockCherryTrapdoorBehavior {
    public BlockCherryTrapdoorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
