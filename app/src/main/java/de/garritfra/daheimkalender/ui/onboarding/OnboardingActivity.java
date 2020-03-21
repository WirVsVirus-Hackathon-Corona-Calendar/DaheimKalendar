package de.garritfra.daheimkalender.ui.onboarding;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.NumberPicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import de.garritfra.daheimkalender.R;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.fragment_onboarding_age_check);
        NumberPicker agePicker = findViewById(R.id.age_number_picker);
        agePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                System.out.println("Malte value = " + newVal);
            }
        });
    }
}
