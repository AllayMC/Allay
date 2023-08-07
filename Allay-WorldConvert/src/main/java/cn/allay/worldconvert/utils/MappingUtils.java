package cn.allay.worldconvert.utils;

import cn.allay.api.mapping.JeBlockState;

import java.util.Map;

public class MappingUtils {
    public static JeBlockState convertBlockState(org.jglrxavpok.hephaistos.mca.BlockState blockState) {
        String name = blockState.getName();
        Map<String, String> properties = blockState.getProperties();
        StringBuilder ret = new StringBuilder(name).append('[');
        String join = String.join(",", properties.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).toList());
        return new JeBlockState(ret.append(join).append(']').toString());
    }
}
