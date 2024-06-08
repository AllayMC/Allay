package org.allaymc.server.i18n;

import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.LangCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Allay Project 2023/12/15
 *
 * @author daoge_cmd
 */
public class AllayI18nTest {

    public static final String[] ARGS = {"1", "2", "3", "4", "5"};
    public static final String TEST_VANILLA_KEY_NO_NAMESPACE = "translation.test.complex";
    public static final String TEST_VANILLA_KEY = "minecraft:" + TEST_VANILLA_KEY_NO_NAMESPACE;
    public static final String TEST_ALLAY_KEY_NO_NAMESPACE = "allay.starting";
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

        assertEquals("aaaStarting up allay...", translator.toClientFriendlyStyle("aaa%" + TEST_ALLAY_KEY));
        assertEquals("Starting up allay...", translator.toClientFriendlyStyle("%" + TEST_ALLAY_KEY));
        assertEquals("Starting up allay...", translator.toClientFriendlyStyle(TEST_ALLAY_KEY));
        assertEquals("aaaStarting up allay... eee", translator.toClientFriendlyStyle("aaa%" + TEST_ALLAY_KEY + " eee"));
    }
}
