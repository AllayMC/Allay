package cn.allay.block.palette;

import cn.allay.block.attribute.VanillaBlockAttributeRegistry;
import cn.allay.block.palette.VanillaBlockPaletteRegistry;
import cn.allay.block.property.type.BooleanPropertyType;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.testutils.TestUtils;
import lombok.SneakyThrows;
import org.cloudburstmc.nbt.NBTInputStream;
import org.junit.jupiter.api.Test;

import java.io.DataInputStream;
import java.nio.file.Files;
import java.util.zip.GZIPInputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/7 <br>
 * Allay Project <br>
 */
class VanillaBlockPaletteRegistryTest {

    @Test
    void testInit() {
        VanillaBlockPaletteRegistry.init(new VanillaBlockPaletteRegistry.Loader() {
            @SneakyThrows
            @Override
            protected NBTInputStream getNBTInputStream() {
                var input = Files.newInputStream(TestUtils.getSrcMainResourceFilePath("block_palette.nbt"));
                return new NBTInputStream(
                        new DataInputStream(
                                new GZIPInputStream(
                                        input
                                )
                        )
                );
            }
        });
        assertNotNull(VanillaBlockPaletteRegistry.getInstance());
    }
}