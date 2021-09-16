package com.example.android.testing.espresso.BasicSample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

public class ToastActivity extends Activity {
    @VisibleForTesting()
    boolean toastShow = false;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast);
        Button button = (Button) findViewById(R.id.buttonShowToast);
        button.setOnClickListener((arg0) -> {
            Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.toast_text),
                    Toast.LENGTH_LONG
            ).show();
            toastShow = true;
        });
    }
}
