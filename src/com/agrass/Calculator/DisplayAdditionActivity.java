package com.agrass.Calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class DisplayAdditionActivity extends Activity {

    public final static String example = "com.agrass.Calculator";

    public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display_addition);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//             Show the Up button in the action bar.
                getActionBar().setDisplayHomeAsUpEnabled(true);
            }
    }

        public void buttonAdd(View v) {

            Intent intent = new Intent(this, MainActivity.class);

            EditText editText = (EditText) findViewById(R.id.editText1);
            String editText1 = editText.getText().toString();

            EditText editText2 = (EditText) findViewById(R.id.editText2);
            String editText3 = editText2.getText().toString();

            String ans = (editText1+" x "+editText3);

            intent.putExtra(example, ans);
            setResult(RESULT_OK, intent);
            finish();
        }

}