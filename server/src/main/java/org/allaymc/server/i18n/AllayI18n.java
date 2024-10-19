package org.allaymc.server.i18n;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.LangCode;

import java.util.EnumMap;
import java.util.Map;

import static java.lang.Math.min;
import static org.allaymc.api.i18n.I18n.KeyInfo.EMPTY;
import static org.allaymc.api.i18n.I18n.isValidKeyCharacter;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayI18n implements I18n {

    public static final String DISORDERED_PARAM_S = "%s";
    public static final String DISORDERED_PARAM_D = "%d";

    protected EnumMap<LangCode, Map<String, String>> langMap = new EnumMap<>(LangCode.class);
    @Getter
    @Setter
    protected LangCode defaultLangCode;

    public AllayI18n(I18nLoader i18nLoader, LangCode defaultLangCode) {
        applyI18nLoader(i18nLoader);
        this.defaultLangCode = defaultLangCode;
    }

    public void applyI18nLoader(I18nLoader i18nLoader) {
        for (var langCode : LangCode.values()) {
            try {
                if (!langMap.containsKey(langCode)) {
                    langMap.put(langCode, new Object2ObjectOpenHashMap<>());
                }
                langMap.get(langCode).putAll(i18nLoader.getLangMap(langCode));
            } catch (Exception e) {
                log.error("Error in applying i18n loader for lang code {}", langCode, e);
            }
        }
    }

    @Override
    public String tr(LangCode langCode, String tr, String... args) {
        var keyInfo = findI18nKey(tr);
        if (keyInfo == EMPTY) return tr;
        var lang = langMap.get(langCode).get(keyInfo.key());
        if (lang == null) lang = langMap.get(FALLBACK_LANG).get(keyInfo.key());
        if (lang == null) return tr;
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
                orderedParamIndex = findOrderedParamIndex(lang, order);
            }
            argIndex++;
            order++;
            orderedParamIndex = findOrderedParamIndex(lang, order);
        }
        return new StringBuilder(tr).replace(keyInfo.startIndex(), keyInfo.endIndex() + 1, lang).toString();
    }

    @Override
    public KeyInfo findI18nKey(String str) {
        var startIndex = str.indexOf("%");
        var index = 0;
        var hasStarter = false;
        if (startIndex == -1) {
            // No '%' was found
            startIndex = 0;
        } else {
            hasStarter = true;
            // Jump over '%'
            index = startIndex + 1;
        }
        var colonIndex = -1;
        var keyBuilder = new StringBuilder();
        for (/**/; index < str.length(); index++) {
            var c = str.charAt(index);
            if (c == ':') {
                if (colonIndex == -1) {
                    colonIndex = index;
                } else {
                    // Illegal key style: more than one colon
                    return EMPTY;
                }
            }
            if (isValidKeyCharacter(c)) {
                keyBuilder.append(c);
            } else break;
        }
        return new KeyInfo(startIndex, index - 1, colonIndex, keyBuilder.toString(), hasStarter);
    }

    @Override
    public String tr(LangCode langCode, String tr) {
        var keyInfo = findI18nKey(tr);
        if (keyInfo == EMPTY) return tr;
        var lang = langMap.get(langCode).get(keyInfo.key());
        if (lang == null) lang = langMap.get(FALLBACK_LANG).get(keyInfo.key());
        if (lang == null) return tr;
        return new StringBuilder(tr).replace(keyInfo.startIndex(), keyInfo.endIndex() + 1, lang).toString();
    }

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
}
