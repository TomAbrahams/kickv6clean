//Abrahams, Thomas
//Kick it app.
//With help from many tutorials (Lynda, Stackoverflow), it was a fun experience. Android is alright.
package com.example.thomas.cskickitappv2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class Recommendation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        final DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(android.R.drawable.stat_sys_download)
                .showImageForEmptyUri(android.R.drawable.ic_dialog_alert)
                .showImageOnFail(android.R.drawable.stat_notify_error)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY) //filled width
                .build();

        final ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
        String imageURL = getIntent().getExtras().getString("imageURL");
        String name = getIntent().getExtras().getString("name");
        String address = getIntent().getExtras().getString("address");
        Bundle bundle = getIntent().getExtras();
        //This was added
        String myCll = bundle.getString("theCLL");
        int eventNum = bundle.getInt("eventNumber");
        changeImage(imageURL);
        //This ends added
        getInfo(name, address);
        goBack(eventNum, myCll);
    }
    public void changeImage(String theImageURL)
    {
        ImageView imageViewURL = (ImageView)findViewById(R.id.imageViewPlace);
        if(theImageURL != "None") {
            String url = theImageURL;
            ImageLoader.getInstance().displayImage(theImageURL, imageViewURL);
        }
    }
    public void getInfo(String name, String address)
    {
        String info = name + "\n" + address + "\n";
        TextView theInfo = (TextView)findViewById(R.id.tvInfo);
        try {
            theInfo.setText(info);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(),Toast.LENGTH_LONG).show();

        }

    }
    public void goBack(final int eventNum, final String myCll)
    {
        Button myBack = (Button)findViewById(R.id.btnBack);
        myBack.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          //Build the box.
                                          Intent myIntent = new Intent(Recommendation.this, suggestOnGPS.class);
                                          Bundle myBundle = new Bundle();
                                          myBundle.putString("theCLL",myCll);
                                          myBundle.putInt("eventNumber",eventNum);
                                          myIntent.putExtras(myBundle);
                                          startActivity(myIntent);


                                      }
                                  }

        );

    }
}
