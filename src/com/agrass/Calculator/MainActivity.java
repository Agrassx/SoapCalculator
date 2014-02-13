package com.agrass.Calculator;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends Activity {

        ListView listView;
        ArrayAdapter<String> adapter;
        ArrayList<String> test = new ArrayList<String>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Log.e("","OnCreate");

            setContentView(R.layout.main);

            listView = (ListView) findViewById(R.id.listView1);

            ArrayList<String> stringOil = new ArrayList<String>();

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, test);

            listView.setAdapter(adapter);

        }

        public void solve(View view) {

            TextView textview_answer = (TextView) findViewById(R.id.textView);

            float sum = 0;
//            float[] percent = new float[2];
//            int length = listView.getCount();
//            float[] per = new float[length];
//            String[] stringPer = new String[length];

            for (int i = 0; i < listView.getCount(); i++) {

                String[] item = listView.getAdapter().getItem(i).toString().split(" - ");
                Float item_float2 = Float.valueOf(item[1]);

                sum += item_float2;

            }

            for (int i = 0; i < listView.getCount(); i++) {

                String[] item = listView.getAdapter().getItem(i).toString().split(" - ");
                Float item_float2 = Float.valueOf(item[1]);

                Float item_float1 = (item_float2/sum)*100;

                test.set(i,listView.getAdapter().getItem(i).toString()+" - "+Float.toString(item_float1)+"%");
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, test);

                listView.setAdapter(adapter);

            }

            textview_answer.setText(Float.toString(sum));

        }

        public void add(View view) {

            Intent intent = new Intent(this, DisplayAdditionActivity.class);
            startActivityForResult(intent, 1);

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            //TextView textView = (TextView) findViewById(R.id.textView1);

            if (data == null) { return; }

            //ListItem th = new ListItem(data.getFloatArrayExtra("floatOil"));

            ArrayAdapter<String> adapterEdit = (ArrayAdapter<String>) listView.getAdapter();
            adapterEdit.add(data.getStringExtra("floatOil"));
            adapterEdit.notifyDataSetChanged();
        }

}