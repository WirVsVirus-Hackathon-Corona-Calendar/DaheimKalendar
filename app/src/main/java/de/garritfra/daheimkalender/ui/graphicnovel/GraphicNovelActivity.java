package de.garritfra.daheimkalender.ui.graphicnovel;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.garritfra.daheimkalender.ChallengeRepository;
import de.garritfra.daheimkalender.R;
import de.garritfra.daheimkalender.model.Challenge;

public class GraphicNovelActivity extends AppCompatActivity {

    public static final String challengeId = "challengeId";
    public Challenge challenge;

    private TextView graphicNovelBodyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_novel);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String challengeId = bundle.getString(GraphicNovelActivity.challengeId, "");

            challenge = ChallengeRepository.getInstance().getNextAvailableChallenge();

        if (challenge == null) {
            challenge = new Challenge("1", "Für heute bist du fertig", "Du hast deine heutige Herausforderung bereits gemeistert. Komm gerne morgen wieder vorbei. Und das wichtigste ist: BLEIB GESUND!");
        }
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
