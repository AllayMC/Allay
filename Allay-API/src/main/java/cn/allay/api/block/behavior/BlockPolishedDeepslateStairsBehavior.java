package cn.allay.api.block.behavior;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.impl.attribute.BlockAttributeComponentImpl;
import cn.allay.api.component.annotation.AutoRegister;

import static cn.allay.api.block.component.CommonBlockComponents.VANILLA_STAIR_ATTRIBUTE_COMPONENT_SUPPLIER;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPolishedDeepslateStairsBehavior extends BlockBehavior {
    @AutoRegister
    BlockAttributeComponentImpl BLOCK_ATTRIBUTE_COMPONENT = VANILLA_STAIR_ATTRIBUTE_COMPONENT_SUPPLIER.get();
}
