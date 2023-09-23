package cn.allay.api.identifier;

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
    void splitOn() {
        var id1 = IdentifierUtils.splitOn(idStr, ":");
        var id2 = IdentifierUtils.splitOn(idStr2, ";");
        assert id1.equals(id2);
    }

    @Test
    void tryParse() {
        var id = IdentifierUtils.tryParse(idStr);
        assert id != null;
        assertEquals("pnx", id.namespace());
        assertEquals("love", id.path());
    }

    @Test
    void of() {
        var id = IdentifierUtils.of("pnx", "love");
        assert id != null;
        assertEquals("pnx", id.namespace());
        assertEquals("love", id.path());
    }

    @Test
    void isValid() {
        assert IdentifierUtils.isValid(idStr);
        assert !IdentifierUtils.isValid(idStr2);
    }

    @Test
    void assertValid() {
        assertDoesNotThrow(() -> IdentifierUtils.assertValid(idStr));
        assertThrows(InvalidIdentifierException.class, () -> IdentifierUtils.assertValid(idStr2));
    }
}