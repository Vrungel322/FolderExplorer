package com.nanddgroup.hw_listview;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nanddgroup.hw_listview.Folders.Folder;
import com.nanddgroup.hw_listview.Folders.FolderAdapter;
import com.nanddgroup.hw_listview.Folders.ListData_Folder;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    final String FILENAME = "file.txt";
    final String LOG_TAG = "myLogs";
    final String DIR_SD = "MyFiles";
    final String FILENAME_SD = "fileSD";
    private String currentFolder;
    private ListView lvFiles;
    private FloatingActionButton fab;
    private boolean inMainMenu;
    private Button bReadSD;
    private Button bWriteSD;
    private FolderAdapter folderAdapter;
    private File tempDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        lvFiles = (ListView) findViewById(R.id.lvFiles);
        lvFiles.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        bReadSD = (Button) findViewById(R.id.bReadSD);
        bWriteSD = (Button) findViewById(R.id.bWriteSD);


        Log.d(LOG_TAG, "SD-карта  " + Environment.getExternalStorageDirectory());
        ArrayList<Folder> folders = ListData_Folder.initFolder(Environment.getExternalStorageDirectory());
        folderAdapter = new FolderAdapter(this, R.layout.each_item, folders);
        lvFiles.setAdapter(folderAdapter);
        inMainMenu = true;
        exit();

        lvFiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.tvName);
                currentFolder = String.valueOf(textView.getText());
                tempDir = new File(currentFolder);
                ArrayList<Folder> folders = ListData_Folder.initFolder(tempDir);
                folderAdapter = new FolderAdapter(MainActivity.this, R.layout.each_item, folders);
                lvFiles.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                lvFiles.setAdapter(folderAdapter);
            }
        });
        bWriteSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // получаем путь к текущей папке
                File sdPath = tempDir;
                // добавляем свой каталог к пути
                sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
                // создаем каталог
                sdPath.mkdirs();
                ArrayList<Folder> folders = ListData_Folder.initFolder(tempDir);
                folderAdapter = new FolderAdapter(MainActivity.this, R.layout.each_item, folders);
                lvFiles.setAdapter(folderAdapter);
                lvFiles.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
            }
        });


    }

    private void exit() {
        if (inMainMenu) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "To SD", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    //finish();

                    ArrayList<Folder> folders = ListData_Folder.initFolder(Environment.getExternalStorageDirectory());
                    folderAdapter = new FolderAdapter(MainActivity.this, R.layout.each_item, folders);
                    lvFiles.setAdapter(folderAdapter);
                }
            });
        }
    }
}
