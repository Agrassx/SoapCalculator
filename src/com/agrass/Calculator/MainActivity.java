package com.agrass.Calculator;


import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;


public class MainActivity extends Activity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> DataOils = new ArrayList<String>();
    ArrayList<StructureOfOils> OilsList = new ArrayList<StructureOfOils>();

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

        OilsList.remove(position);


        OilsTable com = new OilsTable(OilsList);
        String MainTable[] = com.getRows();

        DataOils.clear();

        for (int i = 0; i < MainTable.length - 1; i++) if (!MainTable[i].isEmpty()) DataOils.add(MainTable[i]);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataOils);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

    }

    public void ChangeOilMass(final int position) {

        final TextView textview_answer = (TextView) findViewById(R.id.textView);
        final AlertDialog dialog = DialogFragmentChange.getDialog(this, DialogFragmentChange.IDD_Change);

        dialog.show();



        Button D_OK = (Button) dialog.findViewById(R.id.Ok);
        Button D_Cancel = (Button) dialog.findViewById(R.id.Cancel);

        D_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String NewMass = ((EditText)dialog.findViewById(R.id.editTextChange)).getText().toString();
                OilsList.get(position).putMassString(NewMass);

                OilsTable com = new OilsTable(OilsList);
                String MainTable[] = com.getRows();

                DataOils.clear();
                for (int i = 0; i < MainTable.length - 1; i++) if (!MainTable[i].isEmpty()) DataOils.add(MainTable[i]);
                dialog.dismiss();

            }
        });

        D_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });



        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataOils);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        String name = ((EditText)dialog.findViewById(R.id.editTextChange)).getText().toString();

        textview_answer.setText(name);


    }

    public void solve(View view) {

        TextView textview_answer = (TextView) findViewById(R.id.textView);

        SumOfOilsMass SumOfMass = new SumOfOilsMass(OilsList);

        float MassSum = SumOfMass.getSum();

        textview_answer.setText(Float.toString(MassSum));

    }

    public void add(View view) {
        Intent intent = new Intent(this, DisplayAdditionActivity.class);

        CheckOilTableElements CheckOilElements = new CheckOilTableElements(OilsList);
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


        StructureOfOils OilRow = new StructureOfOils(data.getStringExtra("floatOil"));
        OilsList.add(OilRow);


        OilsTable oilstable = new OilsTable(OilsList);
        String hit[] = oilstable.getRows();

        for (int i = 0; i < hit.length - 1; i++)
            if (!hit[i].isEmpty()) DataOils.set(i, hit[i]);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataOils);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

    }


    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);

        TextView myTextView = (TextView)findViewById(R.id.textView);
        saveInstanceState.putString("ArrayStateKey", myTextView.getText().toString());

        ArrayList<String> saveAdapter = new ArrayList<String>();
        ArrayList<String> SaveData = new ArrayList<String>();

        for (int i = 0; i < adapter.getCount(); i++)
            saveAdapter.add(adapter.getItem(i));


        for (int i = 0; i < OilsList.size(); i++)
            SaveData.add(OilsList.get(i).toString());

        saveInstanceState.putStringArrayList("SaveOilList", SaveData);
        saveInstanceState.putStringArrayList("SaveTableData",saveAdapter);


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        TextView textview_answer = (TextView) findViewById(R.id.textView);

        String text_answer = savedInstanceState.getString("ArrayStateKey");
        textview_answer.setText(text_answer);

        DataOils = savedInstanceState.getStringArrayList("SaveTableData");
        ArrayList<String> SaveData = savedInstanceState.getStringArrayList("SaveOilList");

        for (int i = 0; i < SaveData.size(); i++) {
            StructureOfOils OilRow = new StructureOfOils(SaveData.get(i));
            OilsList.add(OilRow);
        }

        OilsTable oilstable = new OilsTable(OilsList);
        String hit[] = oilstable.getRows();

        for (int i = 0; i < hit.length - 1; i++)
            if (!hit[i].isEmpty()) DataOils.set(i, hit[i]);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataOils);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

    }

}