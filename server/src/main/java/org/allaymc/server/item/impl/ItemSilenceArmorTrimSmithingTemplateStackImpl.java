package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSilenceArmorTrimSmithingTemplateStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemSilenceArmorTrimSmithingTemplateStackImpl extends ItemStackImpl implements ItemSilenceArmorTrimSmithingTemplateStack {
    public ItemSilenceArmorTrimSmithingTemplateStackImpl(ItemStackInitInfo initInfo,
                                                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
