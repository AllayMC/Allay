package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "facing", blockRegex = ".*_door.?$")
public class DoorFacingMapper extends StateMapper<Integer> {

    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        int direction = switch (value) {
            case "north" -> 3;
            case "west" -> 2;
            case "south" -> 1;
            default -> 0;
        };
        return Pair.of("direction", direction);
    }
}
