package org.allaymc.server.item.impl.bucket;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemBucketComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.bucket.ItemTropicalFishBucketStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

public class ItemTropicalFishBucketStackImpl extends ItemStackImpl implements ItemTropicalFishBucketStack {
    @Delegate
    protected ItemBucketComponent bucketComponent;

    public ItemTropicalFishBucketStackImpl(ItemStackInitInfo initInfo,
                                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
