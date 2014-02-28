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
            setContentView(R.layout.main);


            listView = (ListView) findViewById(R.id.listView1);
            TextView textview_answer = (TextView) findViewById(R.id.textView);

            String text = "";

            if (savedInstanceState != null) {

                text = savedInstanceState.getString("ArrayStateKey");
                textview_answer.setText(text);

                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                        savedInstanceState.getStringArrayList("f"));

                listView.setAdapter(adapter);

            } else {

            Log.e("see","OnCreate");

            ArrayList<String> stringOil = new ArrayList<String>();

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, test);

            listView.setAdapter(adapter);

            }


        }

        public void solve(View view) {

            TextView textview_answer = (TextView) findViewById(R.id.textView);

            float sum = 0;

            for (int i = 0; i < listView.getCount(); i++) {

                String[] item = listView.getAdapter().getItem(i).toString().split(" - ");
                Float item_float2 = Float.valueOf(item[1]);

                sum += item_float2;

            }

            textview_answer.setText(Float.toString(sum));

        }

        public void add(View view) {

            Intent intent = new Intent(this, DisplayAdditionActivity.class);
            startActivityForResult(intent, 1);

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (data == null) { return; }

            ArrayAdapter<String> adapterEdit = (ArrayAdapter<String>) listView.getAdapter();
            adapterEdit.add(data.getStringExtra("floatOil"));
            adapterEdit.notifyDataSetChanged();

            TextView textview_answer = (TextView) findViewById(R.id.textView);

            float sum = 0;

            for (int i = 0; i < listView.getCount(); i++) {

                String[] item = listView.getAdapter().getItem(i).toString().split(" - ");
                Float item_float2 = Float.valueOf(item[1]);

                sum += item_float2;

            }

            for (int i = 0; i < listView.getCount(); i++) {

                String[] item = listView.getAdapter().getItem(i).toString().split(" - ");

                String StringOil = item[0];

                Float itemMass = Float.valueOf(item[1]);

                float item_percent = Math.round((itemMass/sum)*10000);

                test.set(i, StringOil+" - "+item[1]+" - "+Float.toString(item_percent/100)+"%");
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, test);

                listView.setAdapter(adapter);


            }

        }


        public void onSaveInstanceState(Bundle saveInstanceState) {

            TextView myTextView = (TextView)findViewById(R.id.textView);
//            saveInstanceState.putStringArrayList("ArrayListStateKey", test);
            super.onSaveInstanceState(saveInstanceState);
            saveInstanceState.putString("ArrayStateKey", myTextView.getText().toString());

            ArrayList<String> saveAdapter = new ArrayList<String>();
            for (int i = 0; i < adapter.getCount(); i++)
                saveAdapter.add(adapter.getItem(i).toString());

            saveInstanceState.putStringArrayList("f",saveAdapter);

        }

        @Override
        protected void onRestoreInstanceState(Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);

            String stateSaved = savedInstanceState.getString("ArrayStateKey");

            if(stateSaved == null){
            Toast.makeText(MainActivity.this,
                    "onRestoreInstanceState:\n" +
                            "NO state saved!",
                    Toast.LENGTH_LONG).show();
            }
        }

}