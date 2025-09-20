package org.allaymc.server.message;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.LangCode;
import org.allaymc.api.utils.Utils;

import java.util.EnumMap;
import java.util.Map;

import static java.lang.Math.min;
import static org.allaymc.api.message.I18n.KeyInfo.EMPTY;
import static org.allaymc.api.message.I18n.isValidKeyCharacter;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayI18n implements I18n {

    public static final String DISORDERED_PARAM_S = "%s";
    public static final String DISORDERED_PARAM_D = "%d";

    protected Map<LangCode, Map<String, String>> langMap = new EnumMap<>(LangCode.class);
    @Getter
    @Setter
    protected LangCode defaultLangCode;

    public AllayI18n(I18nLoader i18nLoader, LangCode defaultLangCode) {
        applyI18nLoader(i18nLoader);
        this.defaultLangCode = defaultLangCode;
    }

    @Override
    public String tr(LangCode langCode, String tr, Object... args) {
        var keyInfo = findI18nKey(tr);
        if (keyInfo == EMPTY) {
            return tr;
        }

        var translation = getTranslation(langCode, keyInfo.key());
        if (translation == null) {
            return tr;
        }

        var strArgs = Utils.objectArrayToStringArray(args);

        var result = replaceUnorderedParams(translation, strArgs);
        translation = replaceOrderedParams(result.left(), result.right(), strArgs);

        return new StringBuilder(tr).replace(keyInfo.startIndex(), keyInfo.endIndex() + 1, translation).toString();
    }

    @Override
    public KeyInfo findI18nKey(String str) {
        var startIndex = str.indexOf("%");
        var hasStarter = startIndex != -1;
        var index = startIndex + 1;
        var colonIndex = -1;
        var keyBuilder = new StringBuilder();
        for (/**/; index < str.length(); index++) {
            var c = str.charAt(index);
            if (c == ':') {
                if (colonIndex != -1) {
                    return EMPTY; // Illegal key style: more than one colon
                }
                colonIndex = index;
            }

            if (isValidKeyCharacter(c)) {
                keyBuilder.append(c);
            } else {
                break;
            }
        }

        return new KeyInfo(hasStarter ? startIndex : 0, index - 1, colonIndex, keyBuilder.toString(), hasStarter);
    }

    public void applyI18nLoader(I18nLoader i18nLoader) {
        for (var langCode : LangCode.values()) {
            try {
                var map = langMap.computeIfAbsent(langCode, $ -> new Object2ObjectOpenHashMap<>());
                map.putAll(i18nLoader.getLangMap(langCode));
            } catch (Exception e) {
                log.error("Error in applying i18n loader for lang code {}", langCode, e);
            }
        }
    }

    private String getTranslation(LangCode langCode, String key) {
        var translation = langMap.getOrDefault(langCode, Map.of()).get(key);
        if (translation == null) {
            translation = langMap.getOrDefault(FALLBACK_LANG, Map.of()).get(key);
        }
        return translation;
    }

    private Pair<String, Integer> replaceUnorderedParams(String text, String[] args) {
        int argIndex = 0;
        int paramIndex;
        while ((paramIndex = findUnorderedParamIndex(text)) != -1 && argIndex < args.length) {
            text = new StringBuilder(text).replace(paramIndex, paramIndex + 2, args[argIndex++]).toString();
        }

        for (int order = 1; order <= args.length - argIndex + 1; order++) {
            // Replace all %n%s and %n%d to %n
            text = text.replaceAll("%" + order + "\\$s", "%" + order);
            text = text.replaceAll("%" + order + "\\$d", "%" + order);
        }
        return Pair.of(text, argIndex);
    }

    private String replaceOrderedParams(String text, int startArgIndex, String[] args) {
        int order = 1;
        for (int i = startArgIndex; i < args.length; i++, order++) {
            int paramIndex;
            while ((paramIndex = findOrderedParamIndex(text, order)) != -1) {
                text = new StringBuilder(text).replace(paramIndex, paramIndex + 2, args[i]).toString();
            }
        }
        return text;
    }

    protected int findUnorderedParamIndex(String str) {
        var indexS = str.indexOf(DISORDERED_PARAM_S);
        var indexD = str.indexOf(DISORDERED_PARAM_D);
        if (indexS == -1) {
            return indexD;
        } else if (indexD == -1) {
            return indexS;
        } else {
            return min(indexS, indexD);
        }
    }

    protected int findOrderedParamIndex(String str, int order) {
        return str.indexOf("%" + order);
    }
}
