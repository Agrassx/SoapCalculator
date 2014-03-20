package com.agrass.Calculator;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class DialogFragmentChange extends DialogFragment {

    public AlertDialog getDialog(final MainActivity activity, final int Position) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            final View view = activity.getLayoutInflater().inflate(R.layout.dialog_change, null); // Получаем layout по его ID
            builder.setView(view);

            builder.setTitle(R.string.Dialog_Change_Title);
            final EditText ET = (EditText) view.findViewById(R.id.editTextChange);
            ET.setText(String.valueOf(activity.OilsList.get(Position).getMass()));
            ET.selectAll();
            builder.setPositiveButton(R.string.Dialog_Change_OK, new DialogInterface.OnClickListener() { // Кнопка ОК
                public void onClick(DialogInterface dialog, int whichButton) {

                    String NewMass = ET.getText().toString();
                    if (NewMass.isEmpty()) {
                        dialog.dismiss();
                    } else {
                        activity.OilsList.get(Position).putMass(NewMass);

                        OilsTable com = new OilsTable(activity.OilsList);
                        String MainTable[] = com.getRows();

                        activity.DataOils.clear();
                        for (int i = 0; i < MainTable.length - 1; i++) {
                            if (!MainTable[i].isEmpty()) {
                                activity.DataOils.add(MainTable[i]);
                            }
                        }
                        activity.adapter.notifyDataSetChanged();
                        TextView text = (TextView) activity.findViewById(R.id.textView);
                        SumOfOilsMass SumOfMass = new SumOfOilsMass(activity.OilsList);
                        text.setText("Общий вес: " + Float.toString(SumOfMass.getSum()));
                        dialog.dismiss();
                    }
                }
            });

                builder.setNegativeButton(R.string.Dialog_Change_Cancel, new DialogInterface.OnClickListener() { // Кнопка Отмена
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

               builder.setCancelable(true);
               return builder.create();
    }

}