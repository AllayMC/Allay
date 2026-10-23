package org.allaymc.server.message;

import org.allaymc.api.message.LangCode;

import java.util.Map;

/**
 * {@code I18nLoader} supplies a translation catalog for one requested locale. Translator loading
 * merges the returned key-value entries into its own retained catalog, allowing separate loaders to
 * contribute or override translations without requiring a new translator instance.
 *
 * @author daoge_cmd
 */
public interface I18nLoader {
    /**
     * Returns translation entries for the requested locale for merging into a translator. The
     * translator copies map entries rather than retaining the returned map itself, and loader
     * failures are handled per locale by its caller.
     */
    Map<String, String> getLangMap(LangCode langCode);
}
