package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


/**
 * Also covers suspicious_gravel
 */
@StateRemapper(value = "dusted", blockRegex = "minecraft:suspicious_(sand|gravel)$")
public class SuspiciousSandDustedMapper extends StateMapper<Integer> {

    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        return Pair.of("brushed_progress", Integer.parseInt(value));
    }
}
