package com.agrass.Calculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;


public class DialogFragmentChange extends DialogFragment {

    public static final int IDD_Change = 1; // Идентификаторы диалоговых окон

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    public static AlertDialog getDialog(final Activity activity, int ID) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);



        switch(ID) {

            case IDD_Change: // Изменение масла, массы
                View view = activity.getLayoutInflater().inflate(R.layout.dialog_change, null); // Получаем layout по его ID
                builder.setView(view);

                builder.setTitle(R.string.Dialog_Title);


//                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // Кнопка ОК
//                    public void onClick(DialogInterface dialog, int whichButton) {
//
//                        dialog.dismiss();
//                    }
//                });
//
//                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // Кнопка Отмена
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });


                builder.setCancelable(true);

                return builder.create();
            default:
                return null;
        }

    }


}