package cn.allay.block.registry;

import cn.allay.testutils.TestUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/26 <br>
 * Allay Project <br>
 */
class VanillaBlockAttributeDataRegistryTest {
    @Test
    void testInit() {
        VanillaBlockAttributeDataRegistry.init(new VanillaBlockAttributeDataRegistry.Loader() {
            @SneakyThrows
            @Override
            protected Reader getReader() {
                return Files.newBufferedReader(TestUtils.getSrcMainResourceFilePath("block_attributes.json"));
            }
        });
        assertNotNull(VanillaBlockAttributeDataRegistry.getInstance());
    }
}