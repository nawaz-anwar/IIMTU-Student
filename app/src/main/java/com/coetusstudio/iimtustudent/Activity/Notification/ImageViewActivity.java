package com.coetusstudio.iimtustudent.Activity.Notification;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.coetusstudio.iimtustudent.R;
import com.coetusstudio.iimtustudent.databinding.ActivityImageViewBinding;

public class ImageViewActivity extends AppCompatActivity {

    ActivityImageViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String fileName = getIntent().getStringExtra("title");
        String fileUrl = getIntent().getStringExtra("imageurl");
        String fileDate = getIntent().getStringExtra("date");
        String fileTime = getIntent().getStringExtra("time");

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle(fileName);
        pd.setMessage("Opening....!!!");

        binding.viewTitle.setText(fileName);
        binding.notificationDateActivity.setText(fileDate);
        binding.notificationTimeActivity.setText(fileTime);
        Glide.with(getApplicationContext()).load(fileUrl).error(R.drawable.noimage).into(binding.viewImage);


        binding.notificationDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadImage(getApplicationContext(),fileName,".jpg",DIRECTORY_DOWNLOADS,fileUrl);
            }
        });

        binding.notificationDownloadTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadImage(getApplicationContext(),fileName,".jpg",DIRECTORY_DOWNLOADS,fileUrl);
            }
        });

    }

    public void downloadImage(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {

        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadmanager.enqueue(request);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ImageViewActivity.this, NotificationActivity.class);
        startActivity(intent);
        finish();
    }
}