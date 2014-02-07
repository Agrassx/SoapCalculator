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

            EditText editTextMultiplierFirst = (EditText) findViewById(R.id.editText_Multiplier_First);
            EditText editTextMultiplierSecond = (EditText) findViewById(R.id.editText_Multiplier_Second);

            String StringMultiplierFirst = editTextMultiplierFirst.getText().toString();
            String StringMultiplierSecond = editTextMultiplierSecond.getText().toString();

            float multiplier_first = Float.valueOf(editTextMultiplierFirst.getText().toString());
            float multiplier_second = Float.valueOf(editTextMultiplierSecond.getText().toString());

            float[] floatOil = {multiplier_first, multiplier_second};

            String ans = StringMultiplierFirst+" x "+StringMultiplierSecond;

            intent.putExtra("floatOil", floatOil);
            //intent.putExtra("ans",ans);
            setResult(RESULT_OK, intent);
            finish();
        }

}