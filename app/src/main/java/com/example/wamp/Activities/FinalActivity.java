package com.example.wamp.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import com.example.wamp.R;
import com.kyanogen.signatureview.SignatureView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FinalActivity extends AppCompatActivity {

    RatingBar ratingBar;

    String staff_id,suggestionString;

    int radio1,radio2,radio3,radio4,radio5,seekbarProgress;

    SignatureView signatureView;

    Button button,clear_buttn;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        ratingBar=findViewById(R.id.rating_bar);

        staff_id=getIntent().getStringExtra("staff_id");
        radio1=getIntent().getIntExtra("radio1",0);
        radio2=getIntent().getIntExtra("radio2",0);
        radio3=getIntent().getIntExtra("radio3",0);
        radio4=getIntent().getIntExtra("radio4",0);
        radio5=getIntent().getIntExtra("radio5",0);
        seekbarProgress=getIntent().getIntExtra("seekbar_progress",0);
        suggestionString=getIntent().getStringExtra("suggestions");

        signatureView=findViewById(R.id.signatureView);

        button=findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bitmap=signatureView.getSignatureBitmap();
                storeImage(bitmap);

                float rating=ratingBar.getRating();
                int rate=(int) rating;

                String columnString="Staff ID,Q1,Q2,Q3,Q4,Q5,Q6,Q7,Q8,Image Name";
                String dataString="" + staff_id + ","+ radio1 + ","+ seekbarProgress + ","+ radio2 + ","+ radio3 + ","+ radio4 + ","+ radio5 + ","+ suggestionString + ","+ rate + "," + "MSIL_"+ staff_id +".jpg" + "\n";

                String combinedString;

                File file1 = new File(Environment.getExternalStorageDirectory() + File.separator + "/PersonData/FeedbackData.csv");

                boolean file_is_there=file1.exists();
                if (!file_is_there){
                    combinedString=columnString + "\n" + dataString;
                }
                else{
                    combinedString=dataString;
                }

                File file;
                File root = Environment.getExternalStorageDirectory();
                if (root.canWrite()) {
                    File dir = new File(root.getAbsolutePath() + "/PersonData");
                    boolean abc=dir.mkdirs();
                    file = new File(dir, "FeedbackData.csv");
                    FileWriter out = null;
                    try {
                        out = new FileWriter(file,true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        out.append(combinedString);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.flush();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(FinalActivity.this, "Thanks!", Toast.LENGTH_SHORT).show();

                Intent new_intent=new Intent(getApplicationContext(),Credentials.class);
                startActivity(new_intent);
                finish();
            }

        });

        clear_buttn=findViewById(R.id.clear);
        clear_buttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signatureView.clearCanvas();
            }
        });


    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    private  File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()+ File.separator + "/PersonData");

        // Creating storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Creating a media file name
        File mediaFile;
        String mImageName="MSIL_"+ staff_id +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }
}
