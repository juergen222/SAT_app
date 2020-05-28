package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ConnectionLostDialogue extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.connection_lost_dialogue, null);
        builder.setView(view)
                .setTitle("Error")
                .setMessage("Connection lost")
                .setPositiveButton("to Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);

                    }
                });

    return builder.create();
    }
}
