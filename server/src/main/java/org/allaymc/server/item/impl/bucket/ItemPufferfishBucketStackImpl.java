package org.allaymc.server.item.impl.bucket;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemBucketComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.bucket.ItemPufferfishBucketStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

public class ItemPufferfishBucketStackImpl extends ItemStackImpl implements ItemPufferfishBucketStack {
    @Delegate
    protected ItemBucketComponent bucketComponent;

    public ItemPufferfishBucketStackImpl(ItemStackInitInfo initInfo,
                                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
