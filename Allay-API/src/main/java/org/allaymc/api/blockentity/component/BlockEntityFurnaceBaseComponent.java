package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.component.common.BlockEntityBaseComponent;

/**
 * Allay Project 2024/8/11
 *
 * @author daoge_cmd
 */
public interface BlockEntityFurnaceBaseComponent extends BlockEntityBaseComponent {
    short getBurnTime();

    short getCookTime();

    short getBurnDuration();

    int getStoredXPInt();

    BlockType<?> getUnlitBlockType();

    BlockType<?> getLitBlockType();

    void setLit(boolean lit);

    String getFurnaceRecipeTag();

    int getSpeedMultiplier();
}
