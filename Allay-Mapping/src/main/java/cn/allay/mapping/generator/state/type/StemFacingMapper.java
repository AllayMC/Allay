package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "facing", blockRegex = ".*[^f]_stem.?$")
public class StemFacingMapper extends StateMapper<Integer> {

    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        int direction = switch (value) {
            case "north" -> 2;
            case "west" -> 4;
            case "south" -> 3;
            case "east" -> 5;
            default -> 0;
        };
        return Pair.of("facing_direction", direction);
    }
}
