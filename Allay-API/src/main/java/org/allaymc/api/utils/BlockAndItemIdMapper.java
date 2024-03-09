package org.allaymc.api.utils;

import org.allaymc.api.client.data.Identifier;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.data.VanillaItemMetaBlockStateBiMap;

/**
 * Allay Project 2023/12/8
 *
 * @author daoge_cmd
 * <br>
 * 一个用于描述方块和物品字符串Id之间映射关系的工具类 <br>
 * Id一直是mc中比较复杂的一部分，我们将所有相关逻辑都放到这里，方便阅读也方便后续维护 <br>
 * <br>
 * 所有的方块必将有一个对应的硬性方块物品，这里我们称之为“硬性方块物品”。但是并不是所有的物品都有对应的方块。 <br>
 * 硬性方块物品的贴图只能是方块的渲染图。为了解决这个问题，原版还为某些方块注册了额外的方块物品，例如：蛋糕，炼药锅，草，海带，床，甘蔗（注意甘蔗比较特殊，后面讲）等等<br>
 * 我们将这些额外注册的方块物品称为“实际方块物品”。<br>
 * 在一个方块只有单独对应的方块物品时，“实际方块物品”就等于“硬性方块物品” <br>
 * 原版方块&物品注册顺序：先注册物品，再注册方块（注册方块同时会注册方块的硬性方块物品，物品的id通常与方块相同，但有特例）。 <br>
 * <br>
 * 实际方块物品在物品注册阶段注册，先于方块。通常其id与对应的方块相同，例如蛋糕的实际方块物品id就是"minecraft:cake"。这时蛋糕方块还未注册<br>
 * 在注册蛋糕方块的时候，同时还要注册一个蛋糕方块的硬性方块物品，但是"minecraft:cake"这个id已经被先前注册的物品占了<br>
 * 原版在这种情况下就会在"cake"前加个"item."前缀，也就是"minecraft:item.cake" <br>
 * 原版具有"item."前缀的物品有几十个 <br>
 * <br>
 * 特例：甘蔗 <br>
 * 甘蔗是唯一不遵循上诉规则的物品，我们认为这是历史遗留问题 <br>
 * 甘蔗的方块-硬性方块物品配对为"minecraft:reeds" <-> "minecraft:item.reeds" <br>
 * 而方块-实际方块物品配对则为"minecraft:reeds" <-> "minecraft:sugar_cane" <br>
 * 可以看到甘蔗的实际方块物品id并不与其方块id相同 <br>
 * <br>
 * 为了统一命名，减少混乱。在allay中，我们保证注册的方块物品的硬性方块物品id在没有id冲突的情况下与方块id相同 <br>
 * 若你想为你的方块注册额外的方块物品，请确保其id与对应方块id一致。稍后在注册一个方块时，若allay发现已有一个与方块id相同的物品，将把其作为实际使用的方块物品（也就是方法BlockType.getItemType()的返回值）<br>
 * 但是即便如此，硬性方块物品仍然会被注册。和原版相同，我们将加上前缀"item."
 * <br>
 * 请注意，对于原版方块物品，若其meta（特殊值）不等于0，则不同特殊值的物品还有可能映射到一个方块类型的不同的方块状态上 <br>
 * 具体点说，此类物品特殊值是由对应方块状态的各个方块属性序列化后全填充二进制位得到的。我们不推荐继续使用特殊值，这里也不会细讲具体逻辑<br>
 * 此类映射关系由 {@link VanillaItemMetaBlockStateBiMap} 维护<br>
 */
public final class BlockAndItemIdMapper {

    // Naming conflict prefix
    public static final String NAMING_CONFLICT_PATH_PREFIX = "item.";

    /**
     * 需要调用方保证传入的itemId是方块物品，且方块与方块物品的命名规则合法。否则不存在id为返回值的方块
     * @param itemId 物品id
     * @return 可能的方块id
     */
    public static Identifier itemIdToPossibleBlockId(Identifier itemId) {
        // 特例：甘蔗
        if (itemId.equals(VanillaItemId.SUGAR_CANE.getIdentifier())) {
            return VanillaBlockId.REEDS.getIdentifier();
        }
        var blockId = itemId.clone();
        if (blockId.path().contains(NAMING_CONFLICT_PATH_PREFIX)) {
            blockId = new Identifier(blockId.namespace(), blockId.path().replace(NAMING_CONFLICT_PATH_PREFIX, ""));
        }
        return blockId;
    }

    /**
     * 我们无法仅凭方块id判断其是否有额外的方块物品，不过我们可以推断出其实际方块物品
     * @param blockId 方块id
     * @return 实际方块物品id
     */
    public static Identifier blockIdToActualBlockItemId(Identifier blockId) {
        // 特例：甘蔗
        if (blockId.equals(VanillaBlockId.REEDS.getIdentifier())) {
            return VanillaItemId.SUGAR_CANE.getIdentifier();
        }
        return blockId;
    }
}
