package com.android.registerlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MessageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        EditText ed_input_phone_num, ed_input_check_num;
        Button ed_check_button, ed_send_sms_button;
        ed_input_phone_num = findViewById(R.id.input_phone_num);
        ed_input_check_num = findViewById(R.id.input_check_num);
        ed_send_sms_button = findViewById(R.id.send_sms_button);
        ed_check_button = findViewById(R.id.check_button);

        Intent intent = getIntent();
        String phone_number = intent.getStringExtra("phone_number");
        ed_input_phone_num.setText(phone_number);

        ed_send_sms_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_phone_num = ed_input_phone_num.getText().toString();

            }
        });


        ed_check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MessageActivity.this, GoogleMapActivity.class);
//                intent.putExtra("phone_number", phone_number);
                startActivity(intent);
                return;

            }
        });


    }
}
