package com.example.androidparking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputLayout;

public class LegacyFragment extends Fragment {
    TextInputLayout plateInputLayout,countryDropdown,parkingDropdown;
    EditText plateEditText;
    MaterialButton opazaj,munja,pregled;
    MaterialCheckBox stranaRegCheckBox;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_legacy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        plateInputLayout = view.findViewById(R.id.plateInputLayout);
        countryDropdown = view.findViewById(R.id.countryDropdown);
        parkingDropdown = view.findViewById(R.id.parkingDropdown);
        plateEditText = view.findViewById(R.id.passwordEditText);
        opazaj = view.findViewById(R.id.opazaj);
        munja = view.findViewById(R.id.munja);
        pregled = view.findViewById(R.id.pregled);
        stranaRegCheckBox = view.findViewById(R.id.stranaRegCheckBox);

        stranaRegCheckBox.addOnCheckedStateChangedListener((checkBox, state) -> {
            if(state==MaterialCheckBox.STATE_CHECKED){
                countryDropdown.setEnabled(true);
            }else{
                countryDropdown.setEnabled(false);
            }
        });
    }
}