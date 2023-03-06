package cn.allay.identifier;

import cn.allay.utils.IdentifierUtils;
import cn.allay.utils.InvalidIdentifierException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
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
        assertEquals("pnx", id.getNamespace());
        assertEquals("love", id.getPath());
    }

    @Test
    void of() {
        var id = IdentifierUtils.of("pnx", "love");
        assert id != null;
        assertEquals("pnx", id.getNamespace());
        assertEquals("love", id.getPath());
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