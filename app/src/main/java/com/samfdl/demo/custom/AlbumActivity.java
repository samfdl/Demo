package com.samfdl.demo.custom;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.samfdl.demo.R;

import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity {
    private RecyclerView photo_grid;

    private AlbumAdapter albumAdapter;
    private ArrayList<String> albumList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_album);

        photo_grid = findViewById(R.id.photo_grid);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        photo_grid.setLayoutManager(gridLayoutManager);

        albumAdapter = new AlbumAdapter(this, albumList);

        photo_grid.setAdapter(albumAdapter);

        loadData();
    }

    private void loadData() {
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED + " desc");
        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            albumList.add(path);
        }

        albumAdapter.updateData(albumList);
    }
}