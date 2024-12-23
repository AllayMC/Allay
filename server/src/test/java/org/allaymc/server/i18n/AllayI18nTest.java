package org.allaymc.server.i18n;

import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.LangCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author daoge_cmd
 */
public class AllayI18nTest {

    public static final Object[] ARGS = {"1", "2", "3", "4", "5"};
    public static final String TEST_VANILLA_KEY_NO_NAMESPACE = "translation.test.complex";
    public static final String TEST_VANILLA_KEY = "minecraft:" + TEST_VANILLA_KEY_NO_NAMESPACE;
    public static final String TEST_ALLAY_KEY_NO_NAMESPACE = "server.stopped";
    public static final String TEST_ALLAY_KEY = "allay:" + TEST_ALLAY_KEY_NO_NAMESPACE;
    static I18n translator = new AllayI18n(new AllayI18nLoader(), LangCode.en_US);

    @Test
    void testTr() {
        assertEquals("Prefix, 15 again 2 and 4 lastly 3 and also 4 again!", translator.tr(TEST_VANILLA_KEY, ARGS));
        assertEquals("Prefix, 15 again 2 and 4 lastly 3 and also 4 again!", translator.tr("%" + TEST_VANILLA_KEY, ARGS));
        assertEquals("aaa Prefix, 15 again 2 and 4 lastly 3 and also 4 again!", translator.tr("aaa %" + TEST_VANILLA_KEY, ARGS));
        assertEquals("aaa Prefix, 15 again 2 and 4 lastly 3 and also 4 again! bbb", translator.tr("aaa %" + TEST_VANILLA_KEY + " bbb", ARGS));
        assertEquals("aaa Prefix, 15 again 2 and 4 lastly 3 and also 4 again!%bbb", translator.tr("aaa %" + TEST_VANILLA_KEY + "%bbb", ARGS));
        assertEquals("aaa Prefix, 15 again 2 and 4 lastly 3 and also 4 again!% bbb", translator.tr("aaa %" + TEST_VANILLA_KEY + "% bbb", ARGS));
        assertEquals("aaa Prefix, 15 again 2 and 4 lastly 3 and also 4 again!%%bbb", translator.tr("aaa %" + TEST_VANILLA_KEY + "%%bbb", ARGS));
    }

    @Test
    void testToClientFriendlyStyle() {
        assertEquals("aaa%" + TEST_VANILLA_KEY_NO_NAMESPACE, translator.toClientFriendlyStyle("aaa%" + TEST_VANILLA_KEY, ARGS));
        assertEquals("%" + TEST_VANILLA_KEY_NO_NAMESPACE, translator.toClientFriendlyStyle("%" + TEST_VANILLA_KEY, ARGS));
        assertEquals(TEST_VANILLA_KEY_NO_NAMESPACE, translator.toClientFriendlyStyle(TEST_VANILLA_KEY, ARGS));
        assertEquals("aaa%" + TEST_VANILLA_KEY_NO_NAMESPACE + " eee", translator.toClientFriendlyStyle("aaa%" + TEST_VANILLA_KEY + " eee", ARGS));

        assertEquals("aaaServer stopped", translator.toClientFriendlyStyle("aaa%" + TEST_ALLAY_KEY));
        assertEquals("Server stopped", translator.toClientFriendlyStyle("%" + TEST_ALLAY_KEY));
        assertEquals("Server stopped", translator.toClientFriendlyStyle(TEST_ALLAY_KEY));
        assertEquals("aaaServer stopped eee", translator.toClientFriendlyStyle("aaa%" + TEST_ALLAY_KEY + " eee"));
    }
}
