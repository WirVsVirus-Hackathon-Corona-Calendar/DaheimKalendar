package de.garritfra.daheimkalender.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;

import de.garritfra.daheimkalender.R;
import de.garritfra.daheimkalender.ui.WebContentActivity;

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
                onOpenWebBrowser("datenschutzerklärung.html");
            }
        });

        view.findViewById(R.id.impressum_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOpenWebBrowser("impressum.html");
            }
        });

        view.findViewById(R.id.parental_notice_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOpenWebBrowser("parental_notice.html");
            }
        });

        view.findViewById(R.id.licenses_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), OssLicensesMenuActivity.class));
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    public void onOpenWebBrowser(String filename) {
        Intent intent = new Intent(getContext(), WebContentActivity.class);
        intent.putExtra("filename", filename);
        startActivity(intent);
    }


}
