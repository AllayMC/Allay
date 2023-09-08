package cn.allay.api.item.component.attribute;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.item.component.ItemComponent;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemAttributeComponent extends ItemComponent {
    @Inject
    ItemAttributes getItemAttributes();
}
