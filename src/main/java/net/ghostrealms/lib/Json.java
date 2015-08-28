package net.ghostrealms.lib;

import com.google.gson.Gson;

import java.io.*;

/**
 * Created by jamesraynor on 8/28/15.
 */
public class Json {
    private Json(){
    }

    public static Object getObject(Class type, File toLoad) throws IOException
    {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(toLoad));
        return gson.fromJson(br, type);
    }

    public static void saveObject(Object object, File toSave) throws IOException
    {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        if(!toSave.exists()) toSave.createNewFile();
        FileWriter writer = new FileWriter(toSave);
        writer.write(json);
        writer.close();
    }
}
