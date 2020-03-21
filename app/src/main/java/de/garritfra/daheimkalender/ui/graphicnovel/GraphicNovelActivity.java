package de.garritfra.daheimkalender.ui.graphicnovel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import de.garritfra.daheimkalender.R;

public class GraphicNovelActivity extends AppCompatActivity {

    public static final String challengeId = "challengeId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_novel);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int challengeId = bundle.getInt(GraphicNovelActivity.challengeId, -1);
            //TODO: Get Challenge
        }

        ImageButton closeBtn = findViewById(R.id.btn_graphic_novel_close);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
