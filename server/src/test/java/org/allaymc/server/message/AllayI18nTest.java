package org.allaymc.server.message;

import org.allaymc.api.message.I18n;
import org.allaymc.api.message.LangCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code AllayI18nTest} verifies bundled translation substitution and preservation of surrounding
 * text during client-key conversion.
 *
 * @author daoge_cmd
 */
public class AllayI18nTest {

    /**
     * Ordered string substitution arguments.
     */
    // ARGS supplies repeatable formatting inputs; TEST_VANILLA_KEY_NO_NAMESPACE
    // and TEST_VANILLA_KEY compare client and server key forms, while
    // TEST_ALLAY_KEY_NO_NAMESPACE and TEST_ALLAY_KEY cover server-owned translation.
    public static final Object[] ARGS = {"1", "2", "3", "4", "5"};
    /**
     * Vanilla key without its namespace.
     */
    public static final String TEST_VANILLA_KEY_NO_NAMESPACE = "translation.test.complex";
    /**
     * Namespaced vanilla translation key.
     */
    public static final String TEST_VANILLA_KEY = "minecraft:" + TEST_VANILLA_KEY_NO_NAMESPACE;
    /**
     * Server-owned key without its namespace.
     */
    public static final String TEST_ALLAY_KEY_NO_NAMESPACE = "server.stopped";
    /**
     * Namespaced server-owned translation key.
     */
    public static final String TEST_ALLAY_KEY = "allay:" + TEST_ALLAY_KEY_NO_NAMESPACE;
    /**
     * Bundled English catalog translator fixture.
     */
    static I18n translator = new AllayI18n(new AllayI18nLoader(), LangCode.en_US);

    /**
     * Translates the bundled complex formatting key with plain and percent-prefixed forms. The
     * expected result preserves surrounding text and verifies argument ordering across repeated
     * numbered and unnumbered placeholders.
     */
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

    /**
     * Converts vanilla keys with and without percent starters and surrounding text. Vanilla
     * conversion removes the namespace, while server-owned keys are translated into English without
     * losing their prefix or suffix.
     */
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
