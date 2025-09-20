package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWildArmorTrimSmithingTemplateStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemWildArmorTrimSmithingTemplateStackImpl extends ItemStackImpl implements ItemWildArmorTrimSmithingTemplateStack {
    public ItemWildArmorTrimSmithingTemplateStackImpl(ItemStackInitInfo initInfo,
                                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
