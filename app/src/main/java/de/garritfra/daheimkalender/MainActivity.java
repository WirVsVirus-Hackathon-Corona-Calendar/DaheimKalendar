package de.garritfra.daheimkalender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.garritfra.daheimkalender.ui.graphicnovel.GraphicNovelActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openGraphicNovelBtn = findViewById(R.id.btn_open_graphic_novel);
        openGraphicNovelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGrapicNovel();
            }
        });
    }

    private void openGrapicNovel() {
        Intent intent = new Intent(this, GraphicNovelActivity.class);
        Bundle bundle = new Bundle();
        //TODO add correct challenged Id here
        bundle.putInt(GraphicNovelActivity.challengeId, -1);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
