package org.allaymc.codegen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author daoge_cmd
 */
public interface CodeGenConstants {
    //The root path of used resources
    String DATA_PATH = "Allay-Data/resources/";
    Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    String INDENT = "    ";
}
