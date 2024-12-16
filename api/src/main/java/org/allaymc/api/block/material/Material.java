package org.allaymc.api.block.material;

import lombok.extern.slf4j.Slf4j;

/**
 * Represents the data of a type of material.
 * <p>
 * See <a href="https://zh.minecraft.wiki/w/%E5%9F%BA%E5%B2%A9%E7%89%88%E6%9D%90%E6%96%99">Bedrock Materials</a> for details.
 *
 * @author daoge_cmd
 */
@Slf4j
public record Material(
        MaterialType materialType,
        boolean blocksMotion,
        boolean blocksPrecipitation,
        // TODO: replace with BlockLegacy::mRequiresCorrectToolForDrops
        // When isAlwaysDestroyable is true, it means the block can be destroyed by any item (even bare hands) without incurring a mining penalty.
        // Some blocks (such as stone and obsidian) cannot be mined by hand. If you try to mine them, the mining speed will be penalized (baseTime increased by 5 times, normally it's 1.5 times).
        boolean isAlwaysDestroyable,
        boolean isLiquid,
        boolean isNeverBuildable,
        boolean isSolid,
        boolean isSolidBlocking,
        boolean isSuperHot,
        float translucency
) {

    public boolean isTransparent() {
        return translucency() != 1.0f;
    }
}
