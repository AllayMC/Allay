package cn.allay.block.registry;

import cn.allay.testutils.TestUtils;
import lombok.SneakyThrows;
import org.cloudburstmc.nbt.NBTInputStream;
import org.junit.jupiter.api.Test;

import java.io.DataInputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.util.zip.GZIPInputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/7 <br>
 * Allay Project <br>
 */
class VanillaBlockPaletteDataRegistryTest {

    @Test
    void testInit() {
        VanillaBlockPaletteDataRegistry.init(new VanillaBlockPaletteDataRegistry.Loader() {
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
        assertNotNull(VanillaBlockAttributeDataRegistry.getInstance());
    }
}