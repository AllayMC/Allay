package cn.allay.mapping.generator.id;

import org.apache.commons.lang3.tuple.Pair;

public interface IdentifierMapper {
    Pair<String, Pair<String, Object>> translateIdentifier(String fullIdentifier);

    default String getStateValue(String fullIdentifier, String property) {
        String[] states = fullIdentifier.substring(fullIdentifier.lastIndexOf("[") + 1).replace("]", "").split(",");
        for (String state : states) {
            String key = state.split("=")[0];
            if (property.equals(key)) {
                return state.split("=")[1];
            }
        }
        return null;
    }
}
