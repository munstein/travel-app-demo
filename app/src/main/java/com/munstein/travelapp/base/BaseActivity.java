package com.munstein.travelapp.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by @Munstein on 06/01/2018.
 */

public class BaseActivity extends AppCompatActivity {
    protected void showToast(String msg){
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
    }

}
