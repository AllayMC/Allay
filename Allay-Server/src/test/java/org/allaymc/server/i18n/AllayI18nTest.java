package org.allaymc.server.i18n;

import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.LangCode;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Allay Project 2023/12/15
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class AllayI18nTest {

    public static final String[] ARGS = {"1", "2", "3", "4", "5"};
    public static final String TEST_KEY = "minecraft:translation.test.complex";
    static I18n translator = new AllayI18N(new AllayI18nLoader(), LangCode.en_US);

    @Test
    void testTr() {
        assertEquals("Prefix, 15 again 2 and 4 lastly 3 and also 4 again!", translator.tr(TEST_KEY, ARGS));
        assertEquals("Prefix, 15 again 2 and 4 lastly 3 and also 4 again!", translator.tr("%" + TEST_KEY, ARGS));
        assertEquals("aaa Prefix, 15 again 2 and 4 lastly 3 and also 4 again!", translator.tr("aaa %" + TEST_KEY, ARGS));
        assertEquals("aaa Prefix, 15 again 2 and 4 lastly 3 and also 4 again!bbb", translator.tr("aaa %" + TEST_KEY + " bbb", ARGS));
        assertEquals("aaa Prefix, 15 again 2 and 4 lastly 3 and also 4 again!bbb", translator.tr("aaa %" + TEST_KEY + "%bbb", ARGS));
        assertEquals("aaa Prefix, 15 again 2 and 4 lastly 3 and also 4 again! bbb", translator.tr("aaa %" + TEST_KEY + "% bbb", ARGS));
        assertEquals("aaa Prefix, 15 again 2 and 4 lastly 3 and also 4 again!%bbb", translator.tr("aaa %" + TEST_KEY + "%%bbb", ARGS));
        assertThrows(IllegalArgumentException.class, () -> translator.tr("%%" + TEST_KEY, ARGS));
    }
}
