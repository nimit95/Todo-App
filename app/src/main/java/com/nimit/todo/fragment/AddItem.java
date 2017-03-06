package com.nimit.todo.fragment;

import android.app.Dialog;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.nimit.todo.R;
import com.nimit.todo.database.DatabseColumns;
import com.nimit.todo.database.QuoteProvider;
import com.nimit.todo.helper.UpdateHelper;
import com.nimit.todo.model.Todo;

import java.util.ArrayList;
import java.util.Calendar;

public class AddItem extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText todoTitle, todoDescription;
    private String[] priority={"High", "Medium", "Low"};
    private Spinner prioritySpinner;
    // TODO: Rename and change types of parameters
    private String mParam1,date;
    private DatePicker datePicker;
    private String mParam2;
    private UpdateHelper updateHelper;
    static int index;
    static Todo todo;
    public AddItem() {
        // Required empty public constructor
    }

    public static AddItem newInstance(String param1, Todo todo, int i) {
        AddItem fragment = new AddItem();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        //args.put(ARG_PARAM2, todo);
        fragment.setArguments(args);
        if(param1.equalsIgnoreCase("edit"))
            index = i;
        else
            index = -20;
        AddItem.todo = todo;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return inflater.inflate(R.layout.fragment_add_item, container, false);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_item, null);
        todoTitle = (EditText) view.findViewById(R.id.todo_title);
        todoDescription = (EditText) view.findViewById(R.id.todo_description);
        prioritySpinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, priority);
        prioritySpinner.setAdapter(priorityAdapter);
        final Calendar calendar = Calendar.getInstance();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setView(view);
        updateHelper = (UpdateHelper) getActivity();
        datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        date = datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear();
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "-" + (month + 1) + "-" + year;

            }
        });
        if (index !=-20)
        {
            todoTitle.setText(todo.getTitle(), TextView.BufferType.EDITABLE);
            todoDescription.setText(todo.getDescription(), TextView.BufferType.EDITABLE);
            prioritySpinner.setSelection(todo.getPriority());

        }
        //alertDialogBuilder.setMessage("Are you sure?");
        alertDialogBuilder.setTitle("Add a New ToDo").setPositiveButton("ADD",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // on success
                Todo todo = new Todo(todoTitle.getText().toString(),
                        todoDescription.getText().toString(),date,prioritySpinner.getSelectedItemPosition(),
                            calendar.getTimeInMillis()
                        );
                Log.e("jaa",todo.getTitle());
                if(index==-20)
                    updateHelper.addItem(todo, 0);
                else
                    updateHelper.updateItem(todo, index );
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return alertDialogBuilder.create();
    }
}
