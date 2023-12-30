package org.allaymc.api.i18n;

import it.unimi.dsi.fastutil.Pair;
import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.utils.Utils;

/**
 * Allay Project 2023/12/15
 *
 * @author daoge_cmd
 */
public interface I18n {

    ApiInstanceHolder<I18n> I18N = ApiInstanceHolder.of();

    static I18n get() {
        return I18N.get();
    }

    String VANILLA_LANG_NAMESPACE = "minecraft";

    String tr(String tr, String... args);

    default String tr(String tr, Object... args) {
        return tr(tr, Utils.objectArrayToStringArray(args));
    }

    String tr(String tr);

    void setLangCode(LangCode langCode);

    LangCode getLangCode();

    Pair<String, Integer> findI18nKey(String str);

    default Pair<String, Boolean> toClientFriendlyStyle(String str, String... args) {
        var pair = findI18nKey(str);
        var isVanillaTr = pair.left().startsWith(I18n.VANILLA_LANG_NAMESPACE);
        if (isVanillaTr) {
            return Pair.of(
                    new StringBuilder(str)
                            .replace(
                                    pair.right(),
                                    pair.right() + I18n.VANILLA_LANG_NAMESPACE.length() + 1,
                                    "")
                            .toString(),
                    true);
        } else {
            return Pair.of(tr(str, args), false);
        }
    }

    default Pair<String, Boolean> toClientFriendlyStyle(String str, Object... args) {
        return toClientFriendlyStyle(str, Utils.objectArrayToStringArray(args));
    }

    default Pair<String, Boolean> toClientFriendlyStyle(String str) {
        return toClientFriendlyStyle(str, new String[0]);
    }
}
