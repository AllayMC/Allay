package org.allaymc.server.message;

import com.google.gson.reflect.TypeToken;
import org.allaymc.api.message.LangCode;
import org.allaymc.server.utils.JSONUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * {@code AllayI18nLoader} reads bundled locale catalogs from UTF-8 JSON resources beneath
 * {@code /lang/}. Each lookup builds the resource name from the locale identifier and parses a new
 * string map; missing resources fail before parsing rather than producing an empty catalog.
 *
 * @author daoge_cmd
 */
public class AllayI18nLoader implements I18nLoader {
    /**
     * Reads and parses the bundled JSON catalog for the requested locale. The resource stream is
     * closed after reading; missing resources raise {@code NullPointerException}, while read
     * failures are wrapped as {@code I18nException}.
     *
     * @throws I18nException if resource reading or stream closure fails with an I/O exception
     */
    @Override
    public Map<String, String> getLangMap(LangCode langCode) {
        try (var input = Objects.requireNonNull(AllayI18nLoader.class.getResourceAsStream("/lang/" + langCode.name() + ".json"))) {
            TypeToken<HashMap<String, String>> typeToken = new TypeToken<>() {
            };
            var bytes = input.readAllBytes();
            return JSONUtils.fromLenient(new String(bytes, StandardCharsets.UTF_8), typeToken);
        } catch (IOException e) {
            // Catalog callers use an unchecked loader failure; retain the I/O
            // cause so per-locale loading diagnostics still identify the read failure.
            throw new I18nException(e);
        }
    }
}
