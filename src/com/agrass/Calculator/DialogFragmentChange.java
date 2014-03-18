package com.agrass.Calculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;


public class DialogFragmentChange extends DialogFragment {

    MainActivity mainActivity = new MainActivity();
    public ArrayList<Oil> OilsList = mainActivity.OilsList;
    public ArrayList<String> DataOils = mainActivity.DataOils;
    public String xer = mainActivity.str;

    public AlertDialog getDialog(final Activity activity, final int Position) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            final View view = activity.getLayoutInflater().inflate(R.layout.dialog_change, null); // Получаем layout по его ID
            builder.setView(view);

            builder.setTitle(R.string.Dialog_Title);

            //final EditText ET = (EditText) view.findViewById(R.id.editTextChange);


            builder.setPositiveButton(R.string.Dialog_OK, new DialogInterface.OnClickListener() { // Кнопка ОК
                public void onClick(DialogInterface dialog, int whichButton) {

//                    String NewMass = ET.getText().toString();
//                    OilsList.get(Position).putMassString(NewMass);
//                    OilsTable com = new OilsTable(OilsList);
//                    String MainTable[] = com.getRows();
//                    DataOils.clear();
//                    for (int i = 0; i < MainTable.length - 1; i++) if (!MainTable[i].isEmpty()) DataOils.add(MainTable[i]);
//                    SumOfOilsMass SumOfMass = new SumOfOilsMass(OilsList);

//                    TV.setText("Сумма: " + xer);
                    dialog.dismiss();

                }
            });

                builder.setNegativeButton(R.string.Dialog_Cancel, new DialogInterface.OnClickListener() { // Кнопка Отмена
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.dismiss();
                    }
                });


               // builder.setCancelable(true);

                return builder.create();

    }


}