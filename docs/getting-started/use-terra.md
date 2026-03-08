---
comments: true
---

# Use Terra

Have you been tired of the boring ultra-flat terrain? Everyone playing on BDS server instead of your server? Well, struggle no more.

Introducing [Terra](https://github.com/PolyhedralDev/Terra), the best solution to solidify your position in the MCBE server space.

![use-terra-p1.png](use-terra-p1.png)

!!! about

    Terra is a modern world generation modding platform, primarily for Minecraft. Terra allows complete customization of world generation
    with an advanced API, tightly integrated with a powerful configuration system.

    Terra consists of several parts:

    - A voxel world generation API with emphasis on configuration and extensibility
    - Several platform implementations, the layer between the API and the platform it's running on
    - An addon loader, which allows addons to interface with the Terra API in a platform-agnostic setting
    - Several "core addons," which implement the "default" configurations of Terra. These addons can be thought of as the config "standard library"

## Install Terra

To install Terra in Allay, reach their CI site in [CodeMC](https://ci.codemc.io/job/PolyhedralDev/job/Terra/), and you will
see the latest build. Download the artifact named `Terra-allay-<version>-shaded.jar` and move it to the plugin folder.

## Edit World Settings

Next, we need to specify the terrain generator for the world as Terra instead of the built-in flat generator. For example,
if we want to modify the generator of default world, just open `worlds/world-settings.yml` and modify the following:

=== "Terra 7.0.0+"

    ```yml linenums="1" hl_lines="5-6 8-9 11-12"
    worlds:
      world:
        storage-type: LEVELDB
        overworld:
          generator-type: TERRA
          generator-preset: meta-pack=DEFAULT;seed=114514
        nether:
          generator-type: TERRA
          generator-preset: meta-pack=DEFAULT;seed=114514
        the-end:
          generator-type: TERRA
          generator-preset: meta-pack=DEFAULT;seed=114514
    # The default world is the world that newly joined players will be in
    default-world: world
    ```

    To set the pack used in Terra, edit `generator-preset`. The `DEFAULT` meta-pack is embedded in the plugin.

=== "Terra < 7.0.0"

    ```yml linenums="1" hl_lines="5-6"
    worlds:
      world:
        storage-type: LEVELDB
        overworld:
          generator-type: TERRA
          generator-preset: pack=overworld;seed=1919810
        nether: null
        the-end: null
    default-world: world
    ```

    To set the pack used in Terra and seed, edit `generator-preset` and fill in the format in the example.
    Please note that `overworld` is Terra's default configuration pack, which is embedded in the plugin.

You may need to delete the old world files if the world is already generated. In this example, you should delete `worlds/world/db`.

**:partying_face: And that's it!** Restart the server and enjoy it!