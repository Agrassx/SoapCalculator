package com.agrass.Calculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;


public class DialogYouAreSure {

    public AlertDialog getDialog(final MainActivity activity) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle(R.string.Dialog_Sure_Title);
        builder.setMessage(R.string.Dialog_Sure_Message);
        builder.setCancelable(true);

        builder.setPositiveButton(R.string.Dialog_Sure_Clear, new DialogInterface.OnClickListener() { // Кнопка ОК
            public void onClick(DialogInterface dialog, int whichButton) {

                TextView text = (TextView) activity.findViewById(R.id.textView);

                activity.OilsList.clear();
                activity.DataOils.clear();
                text.setText("Общий вес: -");
                activity.adapter.notifyDataSetChanged();

            }
        });

        builder.setNegativeButton(R.string.Dialog_Sure_Cancel, new DialogInterface.OnClickListener() { // Кнопка Отмена
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setCancelable(true);
        return builder.create();
    }

}
