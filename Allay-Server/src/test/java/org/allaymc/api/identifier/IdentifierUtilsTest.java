package org.allaymc.api.identifier;

import org.allaymc.api.utils.IdentifierUtils;
import org.allaymc.api.utils.InvalidIdentifierException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
class IdentifierUtilsTest {

    static final String idStr = "pnx:love";
    static final String idStr2 = "pnx;love";

    @Test
    void testSplitOn() {
        var id1 = IdentifierUtils.splitOn(idStr, ":");
        var id2 = IdentifierUtils.splitOn(idStr2, ";");
        assert id1.equals(id2);
    }

    @Test
    void testTryParse() {
        var id = IdentifierUtils.tryParse(idStr);
        assert id != null;
        assertEquals("pnx", id.namespace());
        assertEquals("love", id.path());
    }

    @Test
    void testOf() {
        var id = IdentifierUtils.of("pnx", "love");
        assert id != null;
        assertEquals("pnx", id.namespace());
        assertEquals("love", id.path());
    }

    @Test
    void testIsValid() {
        assert IdentifierUtils.isValid(idStr);
        assert !IdentifierUtils.isValid(idStr2);
    }

    @Test
    void testAssertValid() {
        assertDoesNotThrow(() -> IdentifierUtils.assertValid(idStr));
        assertThrows(InvalidIdentifierException.class, () -> IdentifierUtils.assertValid(idStr2));
    }
}