package cn.allay.mapping.generator.state;

import org.apache.commons.lang3.tuple.Pair;

public abstract class StateMapper<T> {

    /**
     * Translates a state string from a Minecraft: Java Edition
     * block state to it's bedrock counterpart.
     *
     * @param fullIdentifier the full identifier of the block to translate the state for
     * @param value          the value given in the Java blockstate
     * @return a state string for bedrock edition
     */
    public abstract Pair<String, T> translateState(String fullIdentifier, String value);

    public static <V> V asType(Pair<String, ?> pair, Class<V> type) {
        if (type.isInstance(pair.getValue())) {
            return type.cast(pair.getValue());
        }
        throw new IllegalArgumentException("Mapping type " + pair.getValue().getClass().getSimpleName() + " cannot be cast to " + type.getSimpleName());
    }

    protected String getStateValue(String fullIdentifier, String property) {
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
