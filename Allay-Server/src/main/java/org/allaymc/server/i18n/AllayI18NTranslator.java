package org.allaymc.server.i18n;

import it.unimi.dsi.fastutil.Pair;
import org.allaymc.api.i18n.I18nLoader;
import org.allaymc.api.i18n.I18nTranslator;
import org.allaymc.api.i18n.LangCode;
import java.util.Map;

import static java.lang.Math.min;
import static org.allaymc.api.utils.AllayStringUtils.fastTwoPartSplit;

/**
 * Allay Project 2023/12/15
 *
 * @author daoge_cmd
 */
public class AllayI18NTranslator implements I18nTranslator {

    protected Map<String, String> langMap;
    protected I18nLoader i18NLoader;

    public AllayI18NTranslator(I18nLoader i18NLoader, LangCode langCode) {
        this.i18NLoader = i18NLoader;
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
    public String tr(String tr) {
        var pair = findI18nKey(tr);
        var lang = langMap.get(pair.left());
        return new StringBuilder(tr).replace(pair.right(), pair.right() + pair.left().length() + 2, lang).toString();
    }

    @Override
    public void setLangCode(LangCode langCode) {
        this.langMap = i18NLoader.getLangMap(langCode);
    }
}
