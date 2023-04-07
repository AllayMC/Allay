package cn.allay.block.registry;

import cn.allay.block.attribute.VanillaBlockAttributeRegistry;
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
class VanillaBlockAttributeRegistryTest {
    @Test
    void testInit() {
        VanillaBlockAttributeRegistry.init(new VanillaBlockAttributeRegistry.Loader() {
            @SneakyThrows
            @Override
            protected Reader getReader() {
                return Files.newBufferedReader(TestUtils.getSrcMainResourceFilePath("block_attributes.json"));
            }
        });
        assertNotNull(VanillaBlockAttributeRegistry.getInstance());
    }
}