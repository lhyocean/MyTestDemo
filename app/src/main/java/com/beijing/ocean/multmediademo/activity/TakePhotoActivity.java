package com.beijing.ocean.multmediademo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.beijing.ocean.multimediademo.R;

import java.io.File;

public class   TakePhotoActivity extends AppCompatActivity {
    ImageView mImageView;
    File targetFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        mImageView= (ImageView) findViewById(R.id.image_photo);
    }

    public void btnTakeLarge(View view) {
         //  启动拍照，直接保存到文件中，是一个大图
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        targetFile=null;
        String state= Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            File discor=Environment.getExternalStorageDirectory();
            targetFile=new File(discor,"abc"+System.currentTimeMillis()+".jpg");
        }else {
            File dir=getFilesDir();
            targetFile=new File(dir,"abc"+System.currentTimeMillis()+".jpg");
        }
        Uri uri = Uri.fromFile(targetFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        startActivityForResult(intent,88);

    }

    public void btnTakeSmall(View view) {
        //启动拍照，获取缩略图
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,199);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode==199){
            if (resultCode==RESULT_OK){
                //小图片，保存在data中
                Bitmap bitmap = data.getParcelableExtra("data");
                mImageView.setImageBitmap(bitmap);
            }
        }else  if (requestCode==88){
            if (resultCode==RESULT_OK){
                if (targetFile!=null && targetFile.exists()){
                    mImageView.setImageURI(Uri.fromFile(targetFile));
                }
            }
        }
    }

}
