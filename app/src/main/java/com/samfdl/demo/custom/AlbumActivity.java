package com.samfdl.demo.custom;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
        albumAdapter.setOnItemClickListener(new AlbumAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int[] location = new int[2];
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Rect frame = new Rect();
                    AlbumActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                    int statusBarHeight = frame.top;
                    view.getLocationOnScreen(location);
                    location[1] += statusBarHeight;
                } else {
                    view.getLocationOnScreen(location);
                }
                view.invalidate();
                int width = view.getWidth();
                int height = view.getHeight();

                Intent intent = new Intent(AlbumActivity.this, PreviewActivity.class);
                intent.putExtra(PreviewActivity.PHOTO_SOURCE_ID, albumList.get(position));
                intent.putExtra(PreviewActivity.PHOTO_SELECT_POSITION, position);
                intent.putExtra(PreviewActivity.PHOTO_SELECT_X_TAG, location[0]);
                intent.putExtra(PreviewActivity.PHOTO_SELECT_Y_TAG, location[1]);
                intent.putExtra(PreviewActivity.PHOTO_SELECT_W_TAG, width);
                intent.putExtra(PreviewActivity.PHOTO_SELECT_H_TAG, height);
                AlbumActivity.this.startActivity(intent);
                AlbumActivity.this.overridePendingTransition(0, 0);
            }
        });

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