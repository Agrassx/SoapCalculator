package com.agrass.Calculator;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agrass on 06.02.14.
 */



public class MainActivity extends Activity {

        ListView listView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);

            listView = (ListView) findViewById(R.id.listView1);

            final ArrayList<Float[]> oil = new ArrayList<Float[]>();

            final ArrayList<String> stringOil = new ArrayList<String>();

            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringOil);

            listView.setAdapter(adapter);



        }

        public void solve(View view) {

            TextView textview_answer = (TextView) findViewById(R.id.textView);

            float sum = 0;

            for (int i = 0; i < listView.getCount(); i++) {

                String[] item = listView.getAdapter().getItem(i).toString().split(" x ");
                Float item_float1 = Float.valueOf(item[0]);
                Float item_float2 = Float.valueOf(item[1]);
                Float ItemEnd = item_float1 * item_float2;
                sum += ItemEnd;

            }


            textview_answer.setText(Float.toString(sum));


            /*float answer_float;

            TextView textview_answer = (TextView) findViewById(R.id.TextView_Answer);
            EditText edt = (EditText) findViewById(R.id.Multiplier_First);
            EditText edt2 = (EditText) findViewById(R.id.Multiplier_Second);

            if (edt.getText() == null || edt2.getText() == null) {

                textview_answer.setText("Error");

            } else {

                float multiplier_first = Float.valueOf(edt.getText().toString());
                float multiplier_second = Float.valueOf(edt2.getText().toString());

                answer_float = multiplier_first * multiplier_second;
                String answer_string = Float.toString(answer_float);

                textview_answer.setTextSize(40);
                textview_answer.setText(answer_string);

            }*/



        }

        public void add(View view) {

            Intent intent = new Intent(this, DisplayAdditionActivity.class);
            startActivityForResult(intent, 1);
            Intent int_ent = getIntent();
            //String ans = int_ent.getStringExtra(DisplayAdditionActivity.example);
            //TextView textView = (TextView) findViewById(R.id.textView1);
            //textView.setTextSize(40);
            //textView.setText("");
        }

        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            TextView textView = (TextView) findViewById(R.id.textView1);
            if (data == null) { return; }

            //ans = data.getStringExtra("Answer");
            ListItem th = new ListItem(data.getFloatArrayExtra("floatOil"));

            ArrayAdapter<String> adapterEdit = (ArrayAdapter<String>) listView.getAdapter();

            adapterEdit.add(th.toString());
            adapterEdit.notifyDataSetChanged();
        }


}