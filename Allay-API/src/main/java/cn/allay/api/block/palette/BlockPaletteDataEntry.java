package cn.allay.api.block.palette;

import cn.allay.api.block.property.BlockState;
import cn.allay.api.identifier.Identifier;

/**
 * Author: Cool_Loong <br>
 * Date: 2023/5/29 <br>
 * Allay Project
 */
public record BlockPaletteDataEntry(Identifier blockId, BlockState<?> blockState, int version) {
}

