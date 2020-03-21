package de.garritfra.daheimkalender;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import de.garritfra.daheimkalender.model.Challenge;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_main);
    }
}
