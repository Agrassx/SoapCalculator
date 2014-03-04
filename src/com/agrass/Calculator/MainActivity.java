package com.agrass.Calculator;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends Activity {

        ListView listView;
        ArrayAdapter<String> adapter;
        ArrayList<String> DataOils = new ArrayList<String>();
        String[] ContextMenuItems;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.main);
            listView = (ListView) findViewById(R.id.listView1);
            TextView textview_answer = (TextView) findViewById(R.id.textView);

            if (savedInstanceState != null) {

                String text_answer = savedInstanceState.getString("ArrayStateKey");
                textview_answer.setText(text_answer);
                DataOils = savedInstanceState.getStringArrayList("f");
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataOils);

            } else {

            Log.e("see","OnCreate");
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataOils);

            }

            listView.setAdapter(adapter);
            registerForContextMenu(listView);

        }

        @Override
        public void onCreateContextMenu(ContextMenu ContextMenuOils, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

            ContextMenuItems = getResources().getStringArray(R.array.ContextMenu);

//            ContextMenuOils.add("Delete");
            for (int i = 0; i < ContextMenuItems.length; i++) {
                ContextMenuOils.add(ContextMenuItems[i]);
            }
        }

        public boolean onContextItemSelected(MenuItem item) {

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            if ( item.getItemId() == 0 ) {

                deleteItem(item.getItemId());
                int position = (int) info.id;
                DataOils.remove(position);
                this.adapter.notifyDataSetChanged();

            } else {

                return false;
            }

            return true;
        }

        public void deleteItem(int id) {

            Toast.makeText(this, "Delete was called, but its another story", Toast.LENGTH_SHORT).show();
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
//
//            float sum = 0;
//
//            for (int i = 0; i < listView.getCount(); i++) {
//
//                String[] item = listView.getAdapter().getItem(i).toString().split(" - ");
//                Float item_float2 = Float.valueOf(item[1]);
//
//                sum += item_float2;
//
//            }
//
//            for (int i = 0; i < listView.getCount(); i++) {
//
//                String[] item = listView.getAdapter().getItem(i).toString().split(" - ");
//
//                String StringOil = item[0];
//
//                Float itemMass = Float.valueOf(item[1]);
//
//                float item_percent = Math.round((itemMass/sum)*10000);
//
//                DataOils.set(i, StringOil + " - " + item[1] + " - " + Float.toString(item_percent / 100) + "%");
//                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataOils);
//
//                listView.setAdapter(adapter);
//
//        }


            AddDataRow com = new AddDataRow(adapter);
            String hit[] = com.getString();
            for (int i = 0; i < hit.length - 1; i++) {
                if (hit[i] != null) {
                    DataOils.set(i, hit[i]);
                }
            }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataOils);
            adapter.notifyDataSetChanged();
//            listView.setAdapter(adapter);
        }


        public void onSaveInstanceState(Bundle saveInstanceState) {
            super.onSaveInstanceState(saveInstanceState);

            TextView myTextView = (TextView)findViewById(R.id.textView);
            saveInstanceState.putString("ArrayStateKey", myTextView.getText().toString());

            ArrayList<String> saveAdapter = new ArrayList<String>();
            for (int i = 0; i < adapter.getCount(); i++)
                saveAdapter.add(adapter.getItem(i).toString());

            saveInstanceState.putStringArrayList("f",saveAdapter);

//            saveInstanceState.putStringArray("ContextMenuItems",ContextMenuItems);
        }

        @Override
        protected void onRestoreInstanceState(Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);

            String stateSaved = savedInstanceState.getString("ArrayStateKey");

            if (stateSaved == null) {
            Toast.makeText(MainActivity.this,
                    "onRestoreInstanceState:\n" +
                            "NO state saved!",
                    Toast.LENGTH_LONG).show();
            }
        }

}