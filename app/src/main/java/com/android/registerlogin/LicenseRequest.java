package com.android.registerlogin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;

public class LicenseRequest extends StringRequest {


    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://joonho.online/CAROL/license_auth.php";
    private Map<String, String> licence_info;
    public LicenseRequest(String license_num, String name, String resident_id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        licence_info = new HashMap<>();
        licence_info.put("name", name);
        licence_info.put("resident_id", resident_id);
        licence_info.put("license_num", license_num);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return licence_info;
    }
}
