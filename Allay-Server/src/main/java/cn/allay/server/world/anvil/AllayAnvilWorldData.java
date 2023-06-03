package cn.allay.server.world.anvil;

/**
 * Author: Cool_Loong <br>
 * Date: 5/31/2023 <br>
 * Allay Project
 */
/*
@Slf4j
public class AllayAnvilWorldData implements WorldData {
    private final File file;
    private NbtMap data;

    public AllayAnvilWorldData(File levelDat) {
        this.file = levelDat;
        try (var input = NbtUtils.createGZIPReader(new FileInputStream(levelDat))) {
            this.data = ((NbtMap) input.readTag()).getCompound("Data");
        } catch (FileNotFoundException e) {
            log.error("The level.dat file does not exist!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
*/
