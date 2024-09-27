package com.dreaming.easy.reflect;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String msg = String.format("Reflect application=%s", new AndroidThread().getApplication());
        TextView tvText = findViewById(R.id.tv_text);
        tvText.setText(msg);
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }
}