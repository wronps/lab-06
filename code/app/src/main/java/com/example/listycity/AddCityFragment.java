package com.example.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {
    interface AddCityDialogListener {
        void addCity(City city);
        void onEditCity(City city, String newName, String newProvince);
    }
    private AddCityDialogListener listener;
    public static AddCityFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view =
                LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        Bundle args = getArguments();
        final boolean isEditing = args != null && args.containsKey("city");
        final City cityToEdit = isEditing ? (City) args.getSerializable("city") : null;
        if (isEditing && cityToEdit != null) {
            editCityName.setText(cityToEdit.getCityName());
            editProvinceName.setText(cityToEdit.getProvince());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle(isEditing ? "Edit a city" : "Add a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton(isEditing ? "Save" : "Add", (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    if (isEditing){
                        listener.onEditCity(cityToEdit, cityName, provinceName);
                    }
                    else{
                        listener.addCity(new City(cityName, provinceName));
                    }
                })
                .create();
    }
}