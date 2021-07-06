package com.example.coretech_mobile.calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;


public class CalerndarDialogBusy extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Wybrany termin jest zajęty")
        .setPositiveButton("Ok" ,(dialog, which) -> {

        });

        return builder.create();
    }
}
