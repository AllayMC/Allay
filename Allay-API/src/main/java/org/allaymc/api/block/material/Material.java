package org.allaymc.api.block.material;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaMaterialTypes;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Objects;

/**
 * Allay Project 2024/6/11
 * <p>
 * See <a href="https://zh.minecraft.wiki/w/%E5%9F%BA%E5%B2%A9%E7%89%88%E6%9D%90%E6%96%99">Bedrock Materials</a> for details
 *
 * @author daoge_cmd
 */
@Slf4j
public record Material(
        MaterialType materialType,
        boolean canBeMovingBlock,
        boolean canHavePrecipitation,
        boolean isAlwaysDestroyable,
        boolean isLiquid,
        boolean isSolid,
        boolean isSolidBlocking,
        boolean isSuperHot,
        float translucency
) {
    private static final EnumMap<VanillaBlockId, Material> VANILLA_BLOCK_MATERIALS = new EnumMap<>(VanillaBlockId.class);

    static {
        var stream = new BufferedInputStream(
                Objects.requireNonNull(
                        Material.class
                                .getClassLoader()
                                .getResourceAsStream("block_material_data.nbt"),
                        "block_material_data.nbt is missing!")
        );
        try (var reader = NbtUtils.createGZIPReader(stream)) {
            var nbtMap = (NbtMap) reader.readTag();
            nbtMap.forEach((k, v) -> {
                var material = (NbtMap) v;
                VANILLA_BLOCK_MATERIALS.put(VanillaBlockId.fromIdentifier(new Identifier(k)), new Material(
                        VanillaMaterialTypes.getMaterialTypeByName(material.getString("materialType")),
                        material.getBoolean("canBeMovingBlock"),
                        material.getBoolean("canHavePrecipitation"),
                        material.getBoolean("isAlwaysDestroyable"),
                        material.getBoolean("isLiquid"),
                        material.getBoolean("isSolid"),
                        material.getBoolean("isSolidBlocking"),
                        material.getBoolean("isSuperHot"),
                        material.getFloat("translucency")
                ));
            });
        } catch (IOException e) {
            log.error("Failed to load block material data", e);
        }
    }

    public static Material getVanillaBlockMaterial(VanillaBlockId blockId) {
        return VANILLA_BLOCK_MATERIALS.get(blockId);
    }
}
