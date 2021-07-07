package com.example.coretech_mobile.calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.coretech_mobile.R;
import com.example.coretech_mobile.model.Event;

import java.time.LocalDate;
import java.util.Calendar;

public class CalendarDialog extends AppCompatDialogFragment {

    EditText editTextSubject, editTextDescription, editTextEventLength, editTextStartDateTime;
    TimePickerDialog picker;
    CalendarDialogListener calendarDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.calendar_dialog_layout, null);

        editTextSubject = view.findViewById(R.id.subject);
        editTextDescription = view.findViewById(R.id.calendar_description);
        editTextEventLength = view.findViewById(R.id.event_length);
        editTextStartDateTime = view.findViewById(R.id.start_date_time);
        initHourPicker(view);

        builder.setView(view)
                .setTitle("Rezerwacja")
                .setNegativeButton("Cancel", (dialog, which) -> {
                    Toast.makeText(getContext(),"Cancel Pressed", Toast.LENGTH_LONG).show();

                })
                .setPositiveButton("Ok", (dialog, which) -> {
                    Toast.makeText(getContext(),"Ok Pressed", Toast.LENGTH_LONG).show();
                    //String date = getArguments().getString("date");
                    String subject = editTextSubject.getText().toString();
                    String eventDescription = editTextDescription.getText().toString();
                    Long eventLength = Long.parseLong(editTextEventLength.getText().toString());
                    //Long startDateTime = Long.parseLong(editTextStartDateTime.getText().toString());

                    Event event = new Event(subject, LocalDate.now().toString(), eventLength, eventDescription);
                    calendarDialogListener.saveEvent(event);
                });


        return builder.create();
    }



    private void initHourPicker(View view) {
        editTextStartDateTime.setInputType(InputType.TYPE_NULL);
        editTextStartDateTime.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int hour = cldr.get(Calendar.HOUR_OF_DAY);
            int minutes = cldr.get(Calendar.MINUTE);
            // time picker dialog
            picker = new TimePickerDialog(getContext(),
                    (tp, sHour, sMinute) -> editTextStartDateTime.setText(sHour + ":" + sMinute), hour, minutes, true);
            picker.show();
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            calendarDialogListener = (CalendarDialogListener) context;
        }catch (ClassCastException classCastException){
            throw new ClassCastException(context.toString() + "must implement CalendarDialogListener");
        }
    }

    public interface CalendarDialogListener {
        void saveEvent(Event event);
    }
}
