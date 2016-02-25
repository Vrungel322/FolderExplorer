package com.nanddgroup.hw_listview.Folders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanddgroup.hw_listview.R;

import java.util.ArrayList;

/**
 * Created by Nikita on 05.01.2016.
 */
public class FolderAdapter extends ArrayAdapter<Folder> {
    Context context;
    ArrayList<Folder> folders;

    public FolderAdapter(Context context, int resource, ArrayList<Folder> folders) {
        super(context, resource, folders);
        this.context = context;
        this.folders = folders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = null;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = inflater.inflate(R.layout.each_item, parent, false);
        }
        else {
            rootView = convertView;
        }
        ImageView ivLogo = (ImageView) rootView.findViewById(R.id.ivLogo);
        ivLogo.setImageResource(folders.get(position).logoID);
        TextView tvName = (TextView) rootView.findViewById(R.id.tvName);
        tvName.setText(folders.get(position).folderName);
        TextView tvData = (TextView) rootView.findViewById(R.id.tvData);
        tvData.setText(folders.get(position).folderData);
        return rootView;

    }
}
