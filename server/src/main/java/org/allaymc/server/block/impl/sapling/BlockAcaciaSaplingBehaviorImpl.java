package org.allaymc.server.block.impl.sapling;

import java.util.List;
import org.allaymc.api.block.interfaces.sapling.BlockAcaciaSaplingBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockAcaciaSaplingBehaviorImpl extends BlockBehaviorImpl implements BlockAcaciaSaplingBehavior {
    public BlockAcaciaSaplingBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
