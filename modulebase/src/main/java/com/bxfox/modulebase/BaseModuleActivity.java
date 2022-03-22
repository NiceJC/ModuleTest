package com.bxfox.modulebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BaseModuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_module);
    }
}