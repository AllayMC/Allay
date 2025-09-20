package org.allaymc.api.block.type;

import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class BlockStateGetterTest {
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
