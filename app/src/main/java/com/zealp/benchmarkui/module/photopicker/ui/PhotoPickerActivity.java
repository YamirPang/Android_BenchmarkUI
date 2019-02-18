package com.zealp.benchmarkui.module.photopicker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import com.zealp.benchmark_ui.module.me.iwf.photopicker.PhotoPicker;
import com.zealp.benchmark_ui.module.me.iwf.photopicker.PhotoPreview;
import com.zealp.benchmarkui.R;
import com.zealp.benchmarkui.module.photopicker.adapter.PhotoAdapter;
import com.zealp.benchmarkui.module.photopicker.listener.RecyclerItemClickListener;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.RecyclerView;

public class PhotoPickerActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    private PhotoAdapter photoAdapter;

    private ArrayList<String> selectedPhotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_picker);
        initView();
    }

    /**
     * 初始化加载页面
     */
    private void initView() {
        photoAdapter = new PhotoAdapter(this, selectedPhotos);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(photoAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (photoAdapter.getItemViewType(position) == PhotoAdapter.TYPE_ADD) {
                            PhotoPicker.builder()
                                    .setPhotoCount(PhotoAdapter.MAX)
                                    .setShowCamera(true)
                                    .setPreviewEnabled(false)
                                    .setSelected(selectedPhotos)
                                    .start(PhotoPickerActivity.this, PhotoPicker.REQUEST_CODE);
                        } else {
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(position)
                                    .start(PhotoPickerActivity.this, PhotoPicker.REQUEST_CODE);
                        }
                    }
                }));

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button_no_camera).setOnClickListener(this);
        findViewById(R.id.button_one_photo).setOnClickListener(this);
        findViewById(R.id.button_photo_gif).setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK &&
                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {

            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            selectedPhotos.clear();
            if (photos != null) {
                selectedPhotos.addAll(photos);
            }
            photoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setGridColumnCount(4)
                        .start(PhotoPickerActivity.this, PhotoPicker.REQUEST_CODE);
                break;
            case R.id.button_no_camera:
                PhotoPicker.builder()
                        .setPhotoCount(7)
                        .setShowCamera(false)
                        .setPreviewEnabled(false)
                        .start(PhotoPickerActivity.this, PhotoPicker.REQUEST_CODE);
                break;
            case R.id.button_one_photo:
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .start(PhotoPickerActivity.this, PhotoPicker.REQUEST_CODE);
                break;
            case R.id.button_photo_gif:
                PhotoPicker.builder()
                        .setShowCamera(true)
                        .setShowGif(true)
                        .start(PhotoPickerActivity.this, PhotoPicker.REQUEST_CODE);
                break;
        }
    }
}
