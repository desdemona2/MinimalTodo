package com.redheadhammer.minimaltodolist;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper {

    public  static String FILE = "todo_list.dat";

    public static void writeData(ArrayList<String> itemList, Context context) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(FILE, MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(itemList);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<String> readData(Context context) {
        ArrayList<String> itemList = null;

        try {
            FileInputStream fileInputStream = context.openFileInput(FILE);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            itemList = (ArrayList<String>)objectInputStream.readObject();
        }
        catch (FileNotFoundException e) {
            itemList = new ArrayList<String>();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return itemList;
    }
}
