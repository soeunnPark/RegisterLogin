package com.android.registerlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LicenseActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        EditText ed_license_num, ed_name, ed_resident_num;
        ed_license_num = findViewById(R.id.license_num);
        ed_name = findViewById(R.id.name);
        ed_resident_num = findViewById(R.id.resident_num);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        ed_name.setText(userName);


        Button button = findViewById(R.id.btn_license);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String license_num = ed_license_num.getText().toString();
                String name = ed_name.getText().toString();
                String resident_num = ed_resident_num.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { //면허 인증에 성공한 경우
                                String phone_number = jsonObject.getString("phone_number");
                                Toast.makeText(getApplicationContext(), "면허 인증("+phone_number+")에 성공하였습니다.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(LicenseActivity.this, MessageActivity.class);
                                intent.putExtra("phone_number", phone_number);
                                startActivity(intent);

                            } else { //면허 인증에 실패한 경우
                                String mysqli_error = jsonObject.getString("mysqli_error");
                                Toast.makeText(getApplicationContext(), "면허 인증에 실패하였습니다.("+mysqli_error+")", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                LicenseRequest licenseRequest = new LicenseRequest(license_num, name, resident_num, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LicenseActivity.this);
                queue.add(licenseRequest);
                //          Toast.makeText(getApplicationContext(), "("+license_num+","+name+","+resident_num+")", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
