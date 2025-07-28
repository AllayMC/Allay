package org.allaymc.server.item.component.edible;

import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.api.item.component.ItemPotionComponent;
import org.allaymc.api.item.data.PotionType;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.component.annotation.Dependency;

/**
 * @author daoge_cmd
 */
public class ItemPotionComponentImpl implements ItemPotionComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:potion_component");

    @Dependency
    protected ItemBaseComponent baseComponent;

    @Override
    public PotionType getPotionType() {
        return PotionType.fromId(baseComponent.getMeta());
    }

    @Override
    public void setPotionType(PotionType potionType) {
        baseComponent.setMeta(potionType.ordinal());
    }
}
