package org.allaymc.server.i18n;

import it.unimi.dsi.fastutil.Pair;
import org.allaymc.api.i18n.I18nLoader;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.LangCode;
import java.util.Map;
import java.util.Objects;

import static java.lang.Math.min;
import static org.allaymc.api.utils.AllayStringUtils.fastTwoPartSplit;

/**
 * Allay Project 2023/12/15
 *
 * @author daoge_cmd
 */
public class AllayI18N implements I18n {

    protected Map<String, String> langMap;
    protected LangCode langCode;
    protected I18nLoader i18NLoader;

    public AllayI18N(I18nLoader i18NLoader, LangCode langCode) {
        this.i18NLoader = i18NLoader;
        this.langCode = langCode;
        setLangCode(langCode);
    }

    @Override
    public String tr(String tr, String... args) {
        var pair = findI18nKey(tr);
        var lang = langMap.get(pair.left());
        if (lang == null) {
            throw new IllegalArgumentException("Cannot find lang for key " + pair.left());
        }
        var argIndex = 0;
        var maxArgIndex = args.length - 1;
        var unorderedParamIndex = findUnorderedParamIndex(lang);
        while (unorderedParamIndex != -1 && argIndex <= maxArgIndex) {
            var arg = args[argIndex++];
            lang = new StringBuilder(lang).replace(unorderedParamIndex, unorderedParamIndex + 2, arg).toString();
            unorderedParamIndex = findUnorderedParamIndex(lang);
        }
        for (var order = 1; order <= maxArgIndex - argIndex + 1; order++) {
            // Replace all %n%s and %n%d to %n
            lang = lang.replaceAll("%" + order + "\\$s", "%" + order);
            lang = lang.replaceAll("%" + order + "\\$d", "%" + order);
        }
        var order = 1;
        var orderedParamIndex = findOrderedParamIndex(lang, order);
        while (orderedParamIndex != -1 && argIndex <= maxArgIndex) {
            var arg = args[argIndex];
            while (orderedParamIndex != -1) {
                lang = new StringBuilder(lang).replace(orderedParamIndex, orderedParamIndex + 2, arg).toString();
                orderedParamIndex = findOrderedParamIndex(lang,  order);
            }
            argIndex++;
            order++;
            orderedParamIndex = findOrderedParamIndex(lang, order);
        }
        return new StringBuilder(tr).replace(pair.right(), pair.right() + pair.left().length() + 2, lang).toString();
    }

    @Override
    public Pair<String, Integer> findI18nKey(String str) {
        var split1 = fastTwoPartSplit(str, "%", "");
        var split2 = fastTwoPartSplit(split1[1], "%", split1[1]);
        var split3 = fastTwoPartSplit(split2[0], " ", split2[0]);
        // if there is a '%' before the key (split1[1].isEmpty()), we need to add 1 to the start index
        return Pair.of(split3[0], split1[0].length() + (split1[1].isEmpty() ? 1 : 0));
    }

    public static final String DISORDERED_PARAM_S = "%s";
    public static final String DISORDERED_PARAM_D = "%d";

    protected int findUnorderedParamIndex(String str) {
        var indexS = str.indexOf(DISORDERED_PARAM_S);
        var indexD = str.indexOf(DISORDERED_PARAM_D);
        var minIndex = min(indexS, indexD);
        if (minIndex != -1) return minIndex;
        else {
            if (indexS == -1) {
                return indexD;
            } else {
                return indexS;
            }
        }
    }

    protected int findOrderedParamIndex(String str, int order) {
        return str.indexOf("%" + order);
    }

    @Override
    public String tr(String tr) {
        var pair = findI18nKey(tr);
        var lang = langMap.get(pair.left());
        Objects.requireNonNull(lang, "No valid lang key found in \"" + tr + "\"");
        return new StringBuilder(tr).replace(pair.right(), pair.right() + pair.left().length() + 2, lang).toString();
    }

    @Override
    public void setLangCode(LangCode langCode) {
        this.langMap = i18NLoader.getLangMap(langCode);
    }

    @Override
    public LangCode getLangCode() {
        return langCode;
    }
}
