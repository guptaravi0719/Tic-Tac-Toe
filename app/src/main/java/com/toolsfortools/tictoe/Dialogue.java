package com.toolsfortools.tictoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class Dialogue extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        MainActivity objectmain =new MainActivity();

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Result")
                .setMessage("Player 1 Wins!!")
                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                }) ;



        return builder.create();
    }
}
