package com.example.wamp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.wamp.R;

public class MainActivity extends AppCompatActivity {

    private TextView seekBarValue;
    String staff_id;
    Button nextButton;
    RadioGroup group1,group2,group3,group4,group5;
    RadioButton button1,button2,button3,button4,button5;

    final int RADIO_BUTON_VALUE1=1;
    final int RADIO_BUTON_VALUE2=2;
    final int RADIO_BUTON_VALUE3=3;
    final int RADIO_BUTON_VALUE4=4;
    final int RADIO_BUTON_VALUE5=5;

    int radio1,radio2,radio3,radio4,radio5;

    int seekbarProgress;

    EditText suggestionEditText;
    String suggestionString;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSeekbar();

        group1=findViewById(R.id.radio_group_1);
        group2=findViewById(R.id.radio_group_2);
        group3=findViewById(R.id.radio_group_3);
        group4=findViewById(R.id.radio_group_4);
        group5=findViewById(R.id.radio_group_5);


        staff_id=getIntent().getStringExtra("staff_id");
        suggestionEditText = findViewById(R.id.suggestions);

        nextButton=findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                button1=findViewById(group1.getCheckedRadioButtonId());
                switch (button1.getText().toString()){
                    case "Very poor":   radio1=RADIO_BUTON_VALUE1;
                                        break;
                    case "Poor":        radio1=RADIO_BUTON_VALUE2;
                                        break;
                    case "Average":     radio1=RADIO_BUTON_VALUE3;
                                        break;
                    case "Good":        radio1=RADIO_BUTON_VALUE4;
                                        break;
                    case "Excellent":   radio1=RADIO_BUTON_VALUE5;
                }

                button2=findViewById(group2.getCheckedRadioButtonId());
                switch (button2.getText().toString()){
                    case "Very disorganised":   radio2=RADIO_BUTON_VALUE1;
                                                break;
                    case "Disorganized":        radio2=RADIO_BUTON_VALUE2;
                                                break;
                    case "Acceptable":          radio2=RADIO_BUTON_VALUE3;
                                                break;
                    case "Organized":           radio2=RADIO_BUTON_VALUE4;
                                                break;
                    case "Very organized":      radio2=RADIO_BUTON_VALUE5;
                }

                button3=findViewById(group3.getCheckedRadioButtonId());
                switch (button3.getText().toString()){
                    case "Very poor":   radio3=RADIO_BUTON_VALUE1;
                                        break;
                    case "Poor":        radio3=RADIO_BUTON_VALUE2;
                                        break;
                    case "Average":     radio3=RADIO_BUTON_VALUE3;
                                        break;
                    case "Good":        radio3=RADIO_BUTON_VALUE4;
                                        break;
                    case "Excellent":   radio3=RADIO_BUTON_VALUE5;
                }

                button4=findViewById(group4.getCheckedRadioButtonId());
                switch (button4.getText().toString()){
                    case "Strongly Disagree":   radio4=RADIO_BUTON_VALUE1;
                                                break;
                    case "Disagree":            radio4=RADIO_BUTON_VALUE2;
                                                break;
                    case "Neutral":             radio4=RADIO_BUTON_VALUE3;
                                                break;
                    case "Agree":               radio4=RADIO_BUTON_VALUE4;
                                                break;
                    case "Strongly Agree":      radio4=RADIO_BUTON_VALUE5;
                }

                button5=findViewById(group5.getCheckedRadioButtonId());
                switch (button5.getText().toString()){
                    case "Not Applicable":          radio5=RADIO_BUTON_VALUE1;
                                                    break;
                    case "Partially Applicable":    radio5=RADIO_BUTON_VALUE2;
                                                    break;
                    case "Somewhat Applicable":     radio5=RADIO_BUTON_VALUE3;
                                                    break;
                    case "Applicable":              radio5=RADIO_BUTON_VALUE4;
                                                    break;
                    case "Highly Applicable":       radio5=RADIO_BUTON_VALUE5;
                }


                suggestionString=suggestionEditText.getText().toString();

//                String columnString = "\"Staff-ID\",\"Department\",\"Name\",\"E-Mail\",\"Q1\",\"Q2\",\"Q3\",\"Q4\",\"Q5\",\"Q6\",\"Q7\",\"Q8\"";
//                String dataString = "\"" + staff_id + "\",\"" + department + "\",\"" + name + "\",\"" + email + "\",\""+ radio1 + "\",\""+ seekbarProgress + "\",\""+ radio2 + "\",\""+ radio3 + "\",\""+ radio4 + "\",\""+ radio5 + "\",\""+ suggestionString + "\",\""+ rate + "\",\"";


//                To send data to the next Activity
                intent = new Intent(getApplicationContext(), FinalActivity.class);
                intent.putExtra("staff_id", staff_id);
                intent.putExtra("radio1", radio1);
                intent.putExtra("seekbar_progress",seekbarProgress);
                intent.putExtra("radio2",radio2);
                intent.putExtra("radio3",radio3);
                intent.putExtra("radio4",radio4);
                intent.putExtra("radio5",radio5);
                intent.putExtra("suggestions",suggestionString);

                startActivity(intent);
                finish();

            }
        });

    }

    public void getSeekbar() {

        SeekBar seekBar = findViewById(R.id.seek_bar);
        seekBarValue = findViewById(R.id.seek_bar_value);
        seekBarValue.setText("Rating: " + seekBar.getProgress() + "/" + seekBar.getMax());
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        seekbarProgress = progress;
                        seekBarValue.setText("Rating: " + progress + "/" + seekBar.getMax());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                        seekBarValue.setText("Rating: " + seekbarProgress + "/" + seekBar.getMax());
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.godown,R.anim.godown);
    }
}
