package org.allaymc.api.block.type;

import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code BlockStateGetterTest} verifies named and legacy block-state requests
 * through installed NBT migration.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class BlockStateGetterTest {
    /**
     * Resolves legacy stone metadata, an old anvil property, and a modern name
     * without properties. The decoder must migrate the legacy forms and use the
     * modern block's default state when properties are absent.
     */
    @Test
    void testBlockStateGetter() {
        var blockState = BlockStateGetter.name("minecraft:stone").val(1).blockState();
        assertEquals(BlockTypes.GRANITE, blockState.getBlockType());

        blockState = BlockStateGetter.name("minecraft:anvil").property("damage", "very_damaged").blockState();
        assertEquals(BlockTypes.DAMAGED_ANVIL, blockState.getBlockType());

        blockState = BlockStateGetter.name("minecraft:pink_petals").blockState();
        assertEquals(BlockTypes.PINK_PETALS, blockState.getBlockType());
        assertEquals(BlockTypes.PINK_PETALS.getDefaultState(), blockState);
    }
}
