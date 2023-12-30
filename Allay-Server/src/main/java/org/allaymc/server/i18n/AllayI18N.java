package org.allaymc.server.i18n;

import it.unimi.dsi.fastutil.Pair;
import org.allaymc.api.i18n.I18nLoader;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.LangCode;

import java.util.EnumMap;
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

    protected EnumMap<LangCode, Map<String, String>> langMap = new EnumMap<>(LangCode.class);
    protected LangCode defaultLangCode;
    protected I18nLoader i18NLoader;

    public AllayI18N(I18nLoader i18NLoader, LangCode defaultLangCode) {
        this.i18NLoader = i18NLoader;
        this.defaultLangCode = defaultLangCode;
        setDefaultLangCode(defaultLangCode);
        for (var langCode : LangCode.values()) {
            langMap.put(langCode, i18NLoader.getLangMap(langCode));
        }
    }

    @Override
    public String tr(LangCode langCode, String tr, String... args) {
        var pair = findI18nKey(tr);
        var lang = langMap.get(langCode).get(pair.left());
        if (lang == null) {
            // key is not exist
            return tr;
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
        return Pair.of(split3[0], split1[0].length());
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
    public String tr(LangCode langCode, String tr) {
        var pair = findI18nKey(tr);
        var lang = langMap.get(langCode).get(pair.left());
        Objects.requireNonNull(lang, "No valid lang key found in \"" + tr + "\"");
        return new StringBuilder(tr).replace(pair.right(), pair.right() + pair.left().length() + 2, lang).toString();
    }

    @Override
    public void setDefaultLangCode(LangCode defaultLangCode) {
        this.defaultLangCode = defaultLangCode;
    }

    @Override
    public LangCode getDefaultLangCode() {
        return defaultLangCode;
    }
}
