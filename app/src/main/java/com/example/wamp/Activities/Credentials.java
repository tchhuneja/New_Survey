package com.example.wamp.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.wamp.R;

public class Credentials extends AppCompatActivity {


    Button nextButton;
    EditText staff_idEdit;
    String staff_id;
    Intent intent;

    RelativeLayout rellay1, rellay2;

    boolean allFieldsFilled;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);//For Animation

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);

        rellay1 =  findViewById(R.id.rellay1);
        rellay2 =  findViewById(R.id.rellay2);//For Animation

        handler.postDelayed(runnable, 1500);

        nextButton=findViewById(R.id.nextButton);

        staff_idEdit=findViewById(R.id.firstEdit);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                staff_id = staff_idEdit.getText().toString();

                allFieldsFilled = true;

                if (staff_id.length() == 0) {
                    allFieldsFilled = false;
                    staff_idEdit.setError("This is a required Field");
                }

                if (allFieldsFilled) {

                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("staff_id", staff_id);

                    startActivity(intent);
                    finish();

                    overridePendingTransition(R.anim.goup, R.anim.godown);
                } else
                    Toast.makeText(Credentials.this, "Some Fields are Missing", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
