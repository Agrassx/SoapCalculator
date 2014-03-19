package com.agrass.Calculator;


import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;


public class MainActivity extends Activity {

    ListView listView;
    public ArrayAdapter<String> adapter;
    public ArrayList<String> DataOils = new ArrayList<String>();
    public ArrayList<Oil> OilsList = new ArrayList<Oil>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.main);
            TextView textView = (TextView) findViewById(R.id.textView);
            listView = (ListView) findViewById(R.id.listView1);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataOils);
            listView.setAdapter(adapter);
            registerForContextMenu(listView);

        }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

    }

    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int Pos = (int) info.id;

        switch (item.getItemId()) {
            case R.id.CMDelete:
                deleteItem(Pos);
                return true;

            case R.id.CMChangeMass:
                ChangeOilMass((int) info.id);
                return true;

            case R.id.CMAddOil:
                Intent intent = new Intent(this, DisplayAdditionActivity.class);

                CheckOilTableElements CheckOilElements = new CheckOilTableElements(OilsList);
                String[] CheckedElements = CheckOilElements.getStringArray();

                if (!adapter.isEmpty()) intent.putExtra("CheckElements", CheckedElements);

                startActivityForResult(intent, 1);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    public void deleteItem(int position) {

        final TextView textView = (TextView) findViewById(R.id.textView);

        OilsList.remove(position);

        OilsTable com = new OilsTable(OilsList);
        String MainTable[] = com.getRows();

        DataOils.clear();

        for (int i = 0; i < MainTable.length - 1; i++)
            if (!MainTable[i].isEmpty())
                DataOils.add(MainTable[i]);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataOils);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        SumOfOilsMass SumOfMass = new SumOfOilsMass(OilsList);
        textView.setText("Общий вес: "+Float.toString(SumOfMass.getSum()));

    }

    public void ChangeOilMass(final int position) {

        final AlertDialog dialog = new DialogFragmentChange().getDialog(this, position);
        final TextView textView = (TextView) findViewById(R.id.textView);
        dialog.show();
        SumOfOilsMass SumOfMass = new SumOfOilsMass(OilsList);
        textView.setText("Общий вес: "+Float.toString(SumOfMass.getSum()));

    }

    public void Clear(View view) {

//        TextView textview = (TextView) findViewById(R.id.textView);
//
//        OilsList.clear();
//        DataOils.clear();
//        textview.setText("Общий вес: -");
//        adapter.notifyDataSetChanged();
        AlertDialog dialog = new DialogYouAreSure().getDialog(this);
        if (!adapter.isEmpty()) {
            dialog.show();
        }

    }

    public void add(View view) {

        Intent intent = new Intent(this, DisplayAdditionActivity.class);

        CheckOilTableElements CheckOilElements = new CheckOilTableElements(OilsList);
        String[] CheckedElements = CheckOilElements.getStringArray();

        if (!adapter.isEmpty()) {
            intent.putExtra("CheckElements", CheckedElements);
        }
        if (CheckedElements.length < 7) {
            startActivityForResult(intent, 1);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) { return; }

        TextView textview = (TextView) findViewById(R.id.textView);

        adapter.add(data.getStringExtra("floatOil"));

        Oil OilRow = new Oil(data.getStringExtra("floatOil"));
        OilsList.add(OilRow);


        OilsTable oilstable = new OilsTable(OilsList);
        String rows[] = oilstable.getRows();

        for (int i = 0; i < rows.length - 1; i++)
            if (!rows[i].isEmpty()) DataOils.set(i, rows[i]);

        SumOfOilsMass SumOfMass = new SumOfOilsMass(OilsList);
        textview.setText("Общий вес: "+Float.toString(SumOfMass.getSum()));

        adapter.notifyDataSetChanged();

    }


    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);

        TextView myTextView = (TextView)findViewById(R.id.textView);
        ArrayList<String> SaveData = new ArrayList<String>();

        for (Oil aOilsList : OilsList)
            SaveData.add(aOilsList.toString());

        saveInstanceState.putString("Sum", myTextView.getText().toString());
        saveInstanceState.putStringArrayList("SaveOilList", SaveData);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        TextView textview = (TextView) findViewById(R.id.textView);
        ArrayList<String> SaveData = savedInstanceState.getStringArrayList("SaveOilList");
        String text_answer = savedInstanceState.getString("Sum");


        for (String aSaveData : SaveData) {
            Oil OilRow = new Oil(aSaveData);
            OilsList.add(OilRow);
        }

        OilsTable oilstable = new OilsTable(OilsList);
        String hit[] = oilstable.getRows();

        for (int i = 0; i < hit.length - 1; i++)
            if (!hit[i].isEmpty()) DataOils.add(i, hit[i]);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataOils);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        textview.setText(text_answer);

    }

}