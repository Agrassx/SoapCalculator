package com.agrass.Calculator;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> DataOils = new ArrayList<String>();
    String[] ContextMenuItems;
    ArrayList<StructureTest> StructureList = new ArrayList<StructureTest>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview_answer = (TextView) findViewById(R.id.textView);
        setContentView(R.layout.main);
        listView = (ListView) findViewById(R.id.listView1);

        Log.e("see","OnCreate");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataOils);

        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        }

    @Override
    public void onCreateContextMenu(ContextMenu ContextMenuOils, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        ContextMenuItems = getResources().getStringArray(R.array.ContextMenu);
        for (int i = 0; i < ContextMenuItems.length; i++) ContextMenuOils.add(ContextMenuItems[i]);

    }



    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if ( item.getItemId() == 0) {

            int position = (int) info.id;
            deleteItem(position);

        } else return false;

        return true;

    }

    public void deleteItem(int position) {

        DataOils.remove(position);

        OilsTable com = new OilsTable(adapter);
        String MainTable[] = com.getRows();

        for (int i = 0; i < MainTable.length - 1; i++) if (MainTable[i] != null) DataOils.set(i, MainTable[i]);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataOils);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

    }

    public void solve(View view) {

        TextView textview_answer = (TextView) findViewById(R.id.textView);

        SumOfOilsMass SumOfMass = new SumOfOilsMass(adapter);

        float MassSum = SumOfMass.getSum();

        textview_answer.setText(Float.toString(MassSum));

    }

    public void add(View view) {
        Intent intent = new Intent(this, DisplayAdditionActivity.class);

        CheckOilTableElements CheckOilElements = new CheckOilTableElements(adapter);
        String[] CheckedElements = CheckOilElements.getStringArray();

        if (!adapter.isEmpty()) intent.putExtra("CheckElements", CheckedElements);

        startActivityForResult(intent, 1);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) { return; }

        ArrayAdapter<String> adapterEdit = (ArrayAdapter<String>) listView.getAdapter();
        adapterEdit.add(data.getStringExtra("floatOil"));
        adapterEdit.notifyDataSetChanged();


        StructureTest Struc = new StructureTest(data.getStringExtra("floatOil"));
        StructureList.add(Struc);

        for (int i = 0; i < StructureList.size(); i++ )
            Toast.makeText(MainActivity.this, StructureList.get(i).getName()+" - "+StructureList.get(i).getMass(),
                    Toast.LENGTH_SHORT).show();


        OilsTable oilstable = new OilsTable(adapter);
        String hit[] = oilstable.getRows();

        for (int i = 0; i < hit.length - 1; i++)
            if (!hit[i].isEmpty()) DataOils.set(i, hit[i]);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataOils);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        String stateSaved = data.getStringExtra("del");

        if (stateSaved != null) {
            Toast.makeText(MainActivity.this,
                        "onRestoreInstanceState:\n" +
                                "state saved!",
                        Toast.LENGTH_LONG).show();
        }

    }


    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);

        TextView myTextView = (TextView)findViewById(R.id.textView);
        saveInstanceState.putString("ArrayStateKey", myTextView.getText().toString());

        ArrayList<String> saveAdapter = new ArrayList<String>();
            for (int i = 0; i < adapter.getCount(); i++)
                saveAdapter.add(adapter.getItem(i));
        saveInstanceState.putStringArrayList("f",saveAdapter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        TextView textview_answer = (TextView) findViewById(R.id.textView);

        String text_answer = savedInstanceState.getString("ArrayStateKey");
        textview_answer.setText(text_answer);

        DataOils = savedInstanceState.getStringArrayList("f");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataOils);

        listView.setAdapter(adapter);
        registerForContextMenu(listView);

    }

}