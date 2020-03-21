package de.garritfra.daheimkalender.ui.onboarding;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.garritfra.daheimkalender.R;

public class AgeCheckFragment extends Fragment {
    int age;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_onboarding_age_check, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_age_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = 1;
            }
        });
        view.findViewById(R.id.btn_age_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = 2;
            }
        });
        view.findViewById(R.id.btn_age_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = 3;
            }
        });
        view.findViewById(R.id.btn_age_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = 4;
            }
        });
        view.findViewById(R.id.btn_age_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = 5;
            }
        });
        view.findViewById(R.id.btn_age_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = 6;
            }
        });
        view.findViewById(R.id.btn_age_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = 7;
            }
        });
        view.findViewById(R.id.btn_age_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = 8;
            }
        });
        view.findViewById(R.id.btn_age_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = 9;
            }
        });
        view.findViewById(R.id.btn_age_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = 10;
            }
        });
        view.findViewById(R.id.btn_age_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = 11;
            }
        });
        view.findViewById(R.id.btn_age_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = 12;
            }
        });
        view.findViewById(R.id.btn_age_check_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                sharedPreferences.edit().putInt("USER_AGE", age);
            }
        });
    }
}
