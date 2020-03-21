package de.garritfra.daheimkalender;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AloneFragment extends Fragment {

    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        return inflater.inflate(R.layout.fragment_check_alone, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.txtHeadline).setTag(R.string.titel_alone_fragment);
        view.findViewById(R.id.txtWithPerson).setTag(R.string.not_alone);
        view.findViewById(R.id.txtAlone).setTag(R.string.alone);

        view.findViewById(R.id.imageButtonWithPerson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sharedPreferences.edit().putBoolean("USER_ALONE", true).apply();
            }
        });

        view.findViewById(R.id.imageButtonAlone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putBoolean("USER_ALONE", false).apply();
            }
        });


    }





}
