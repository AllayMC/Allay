package org.allaymc.api.block.dto;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class BlockTest {
    @Test
    void testConstructors() {
        var blockState = BlockTypes.GRASS_BLOCK.getDefaultState();
        var pos = new Position3i(mock(Dimension.class));
        assertThrows(NullPointerException.class, () -> new Block(blockState, null));
        assertThrows(NullPointerException.class, () -> new Block((Dimension) null, pos));
    }

    @Test
    void testEquals() {
        var blockState1 = BlockTypes.GRASS_BLOCK.getDefaultState();
        var blockState2 = BlockTypes.DIAMOND_BLOCK.getDefaultState();
        var dimension = mock(Dimension.class);
        var pos1 = new Position3i(0, 0, 0, dimension);
        var pos2 = new Position3i(1, 1, 1, dimension);
        assertEquals(new Block(blockState1, pos1), new Block(blockState1, pos1));
        assertEquals(new Block(blockState2, pos2), new Block(blockState2, pos2));
        assertNotEquals(new Block(blockState1, pos2), new Block(blockState1, pos1));
        assertNotEquals(new Block(blockState2, pos1), new Block(blockState1, pos1));
        assertNotEquals(new Block(blockState2, pos2), new Block(blockState1, pos1));
    }
}
