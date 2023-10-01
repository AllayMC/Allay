package cn.allay.server.block.type;

import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.server.block.attribute.AllayVanillaBlockAttributeRegistry;
import cn.allay.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Allay Project 2023/9/30
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class BlockStateHashTest {

    static Map<Integer, NbtMap> BLOCK_ATTRIBUTES = new HashMap<>();

    @BeforeAll
    static void load() {
        try {
            var list = ((NbtMap) NbtUtils.createGZIPReader(
                    new BufferedInputStream(
                            Objects.requireNonNull(
                                    AllayVanillaBlockAttributeRegistry.class
                                            .getClassLoader()
                                            .getResourceAsStream("block_attributes.nbt"),
                                    "block_attributes.nbt is missing!")
                    )
            ).readTag()).getList("block", NbtType.COMPOUND);
            for (var blockAttr : list) {
                BLOCK_ATTRIBUTES.put(blockAttr.getInt("blockStateHash"), blockAttr);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testAllHashes() {
        for (var expectedHash : BLOCK_ATTRIBUTES.keySet()) {
            assertNotNull(BlockStateHashPalette.getRegistry().get(expectedHash));
        }
        for (var computedHash : BlockStateHashPalette.getRegistry().getContent().keySet()) {
            assertNotNull(BLOCK_ATTRIBUTES.get(computedHash));
        }
    }
}
