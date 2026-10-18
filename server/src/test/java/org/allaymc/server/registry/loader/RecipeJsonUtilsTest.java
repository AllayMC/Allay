package org.allaymc.server.registry.loader;

import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecipeJsonUtilsTest {

    @Test
    void userDataWithoutTagIsEmpty() {
        // No tag marker, empty CanPlaceOn and CanDestroy lists.
        assertSame(NbtMap.EMPTY, RecipeJsonUtils.parseUserData("AAAAAAAAAAAAAA=="));
    }

    @Test
    void userDataWithTrailingItemSpecificFieldsIsEmpty() {
        // Shield outputs carry an extra blocking tick counter after the lists.
        assertSame(NbtMap.EMPTY, RecipeJsonUtils.parseUserData("AAAAAAAAAAAAAAAAAAAAAAAA"));
    }

    @Test
    void decodesTheLittleEndianTagAfterTheMarker() {
        // Banner output from the 1.26.52 export: {Type: 0}.
        var tag = RecipeJsonUtils.parseUserData("//8BCgAAAwQAVHlwZQAAAAAAAAAAAAAAAAA=");

        assertEquals(NbtMap.builder().putInt("Type", 0).build(), tag);
    }

    @Test
    void rejectsAnUnknownUserDataVersion() {
        assertThrows(IllegalArgumentException.class, () -> RecipeJsonUtils.parseUserData("//8CCgAAAA=="));
    }
}
