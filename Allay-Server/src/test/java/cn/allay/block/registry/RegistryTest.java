package cn.allay.block.registry;

import cn.allay.block.attribute.AllayVanillaBlockAttributeRegistry;
import cn.allay.block.component.impl.attribute.VanillaBlockAttributeRegistry;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.palette.AllayVanillaBlockPaletteRegistry;
import cn.allay.block.palette.VanillaBlockPaletteRegistry;
import cn.allay.block.property.AllayBlockPropertyTypeRegistry;
import cn.allay.block.property.BlockPropertyTypeRegistry;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.testutils.TestUtils;
import lombok.SneakyThrows;
import org.cloudburstmc.nbt.NBTInputStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.DataInputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.util.zip.GZIPInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public class RegistryTest {
    static VanillaBlockAttributeRegistry ATTRIBUTE_REGISTRY;

    static BlockPropertyTypeRegistry PROPERTY_REGISTRY;

    static VanillaBlockPaletteRegistry PALETTE_REGISTRY;

    @BeforeAll
    static void initRegistries() {
        ATTRIBUTE_REGISTRY = new AllayVanillaBlockAttributeRegistry(new AllayVanillaBlockAttributeRegistry.Loader() {
            @SneakyThrows
            @Override
            protected Reader getReader() {
                return Files.newBufferedReader(TestUtils.getSrcMainResourceFilePath("Data/block_attributes_old.json"));
            }
        });
        PROPERTY_REGISTRY = new AllayBlockPropertyTypeRegistry();
        PALETTE_REGISTRY = new AllayVanillaBlockPaletteRegistry(new AllayVanillaBlockPaletteRegistry.Loader() {
            @SneakyThrows
            @Override
            protected NBTInputStream getNBTInputStream() {
                var input = Files.newInputStream(TestUtils.getSrcMainResourceFilePath("Data/block_palette.nbt"));
                return new NBTInputStream(
                        new DataInputStream(
                                new GZIPInputStream(
                                        input
                                )
                        )
                );
            }

            @Override
            protected BlockPropertyTypeRegistry getBlockPropertyTypeRegistry() {
                return PROPERTY_REGISTRY;
            }
        });
    }

    @Test
    void testAttributeRegistry() {
        for (var id : VanillaBlockId.values()) {
            assertNotNull(ATTRIBUTE_REGISTRY.get(id));
        }
    }

    @Test
    void testPropertyRegistry() {
        for (BlockPropertyType<?> property : VanillaBlockPropertyTypes.values()) {
            assertEquals(property, PROPERTY_REGISTRY.get(property.getName()));
        }
    }

    @Test
    void testPaletteRegistry() {
        for (var id : VanillaBlockId.values()) {
            assertNotNull(PALETTE_REGISTRY.get(id));
        }
    }
}
