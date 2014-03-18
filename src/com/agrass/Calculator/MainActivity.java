package com.agrass.Calculator;


import android.app.AlertDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;


public class MainActivity extends Activity {

    ListView listView;
    DialogFragment Dialog;
    public ArrayAdapter<String> adapter;
    public ArrayList<String> DataOils = new ArrayList<String>();
    public ArrayList<Oil> OilsList = new ArrayList<Oil>();
    public String str = "Rj-rj-rj";

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
                //Dialog.show(getFragmentManager(), "dlg2");
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

        //final AlertDialog dialog = DialogFragmentChange.getDialog(this, DialogFragmentChange.IDD_Change);
        final AlertDialog dialog = new DialogFragmentChange().getDialog(this, position);
        //final dialig2 dlg = new dialig2();
        final TextView textView = (TextView) findViewById(R.id.textView);


        //dialog.show();
        dialog.show();

            dialog.getButton(dialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String NewMass = ((EditText) dialog.findViewById(R.id.editTextChange)).getText().toString();
            OilsList.get(position).putMassString(NewMass);

            OilsTable com = new OilsTable(OilsList);
            String MainTable[] = com.getRows();

            DataOils.clear();
            for (int i = 0; i < MainTable.length - 1; i++) if (!MainTable[i].isEmpty()) DataOils.add(MainTable[i]);

            SumOfOilsMass SumOfMass = new SumOfOilsMass(OilsList);
            textView.setText("Сумма: " + Float.toString(SumOfMass.getSum()));
            adapter.notifyDataSetChanged();
            dialog.dismiss();

        }
    });

    dialog.getButton(dialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialog.dismiss();
        }
    });





    }

    public void solve(View view) {

        TextView textview = (TextView) findViewById(R.id.textView);

        OilsList.clear();
        DataOils.clear();
        textview.setText("Сумма: -");
        adapter.notifyDataSetChanged();

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

        TextView textview = (TextView) findViewById(R.id.textView);

        adapter.add(data.getStringExtra("floatOil"));

        Oil OilRow = new Oil(data.getStringExtra("floatOil"));
        OilsList.add(OilRow);


        OilsTable oilstable = new OilsTable(OilsList);
        String rows[] = oilstable.getRows();

        for (int i = 0; i < rows.length - 1; i++)
            if (!rows[i].isEmpty()) DataOils.set(i, rows[i]);

        SumOfOilsMass SumOfMass = new SumOfOilsMass(OilsList);
        textview.setText("Сумма: "+Float.toString(SumOfMass.getSum()));

        adapter.notifyDataSetChanged();

    }


    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);

        TextView myTextView = (TextView)findViewById(R.id.textView);
        ArrayList<String> SaveData = new ArrayList<String>();

        for (int i = 0; i < OilsList.size(); i++) SaveData.add(OilsList.get(i).toString());

        saveInstanceState.putString("Sum", myTextView.getText().toString());
        saveInstanceState.putStringArrayList("SaveOilList", SaveData);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        TextView textview = (TextView) findViewById(R.id.textView);
        ArrayList<String> SaveData = savedInstanceState.getStringArrayList("SaveOilList");
        String text_answer = savedInstanceState.getString("Sum");



        for (int i = 0; i < SaveData.size(); i++) {
            Oil OilRow = new Oil(SaveData.get(i));
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