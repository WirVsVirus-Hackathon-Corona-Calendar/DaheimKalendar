package de.garritfra.daheimkalender.ui.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.garritfra.daheimkalender.R;

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        view.findViewById(R.id.eula_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Datenschutz", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.impressum_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Impressum", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.parental_notice_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Hinweise für Eltern", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.twitter_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/wirvsvirushack"));
                startActivity(browserIntent);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}
