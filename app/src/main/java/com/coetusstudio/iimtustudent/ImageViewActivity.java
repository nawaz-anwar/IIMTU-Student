package com.coetusstudio.iimtustudent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.bumptech.glide.Glide;
import com.coetusstudio.iimtustudent.databinding.ActivityImageViewBinding;

public class ImageViewActivity extends AppCompatActivity {

    ActivityImageViewBinding binding;private
    ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        String filename=getIntent().getStringExtra("title");
        String fileurl=getIntent().getStringExtra("imageurl");

        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle(filename);
        pd.setMessage("Opening....!!!");

        binding.viewTitle.setText(filename);
        Glide.with(getApplicationContext()).load(fileurl).error(R.drawable.iimtulogo).into(binding.viewImage);
    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            binding.viewImage.setScaleX(mScaleFactor);
            binding.viewImage.setScaleY(mScaleFactor);
            return true;
        }
        }
}