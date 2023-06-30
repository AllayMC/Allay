package cn.allay.api.item.component.impl.attribute;

import cn.allay.api.component.annotation.Inject;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemAttributeComponent {
    @Inject
    ItemAttributes getItemAttributes();
}
