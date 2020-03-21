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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
    }
}
