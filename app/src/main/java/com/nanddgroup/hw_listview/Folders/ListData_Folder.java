package com.nanddgroup.hw_listview.Folders;


import com.nanddgroup.hw_listview.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Nikita on 05.01.2016.
 */
public class ListData_Folder {
    static String folderNames[] = {
            "Images",
            "Music",
            "Documents",
            "Video"
    };
    static String folderData[] = {
            "05.01.2016",
            "05.01.2016",
            "05.01.2016",
            "05.01.2016"
    };
    static int logos[] = {
            R.drawable.images_logo,
            R.drawable.music_logo,
            R.drawable.documents_logo,
            R.drawable.video_logo
    };
//   public static Folder[] initFolder(File dir){
//        Folder folders [] = new Folder[folderNames.length];
//        for (int i = 0; i < folderNames.length; i++){
//            folders[i] = new Folder(String.valueOf(listFilesWithSubFolders(dir)), folderData[i], logos[i]);
//        }
//        return folders;
//    }
    public static ArrayList<Folder> initFolder(File dir) {
        ArrayList<File> files = new ArrayList<File>();
        ArrayList<Folder> folders = new ArrayList<Folder>();
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                files.add(file);
                folders.add(new Folder(String.valueOf(file), folderData[0], logos[2]));
            }
                //files.addAll(listFilesWithSubFolders(file));
//            else
//                files.add(file);
        }
        return folders;
    }
}
