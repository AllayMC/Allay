package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.attribute.BlockAttributeComponentImpl;
import cn.allay.api.component.annotation.AutoRegister;

import static cn.allay.api.block.component.CommonBlockComponents.STAIR_ATTRIBUTE_COMPONENT_SUPPLIER;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPolishedGraniteStairsBehavior extends BlockBehavior {
    @AutoRegister
    BlockAttributeComponentImpl BLOCK_ATTRIBUTE_COMPONENT = STAIR_ATTRIBUTE_COMPONENT_SUPPLIER.get();
}
