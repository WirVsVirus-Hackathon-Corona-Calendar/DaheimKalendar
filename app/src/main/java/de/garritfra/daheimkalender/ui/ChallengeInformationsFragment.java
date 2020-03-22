package de.garritfra.daheimkalender.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.List;

import de.garritfra.daheimkalender.R;
import de.garritfra.daheimkalender.model.Challenge;

public class ChallengeInformationsFragment extends Fragment {


    TextView txt_material1, txt_material2, txt_material3, txt_material4, txt_material5, txt_instruction1, txt_instruction2, txt_instruction3, txt_instruction4, txt_instruction5;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private Challenge challenge;

    public ChallengeInformationsFragment(Challenge challenge) {
        this.challenge = challenge;
    }

    /*
    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ChallengeInformationsFragment newInstance(int columnCount) {
        ChallengeInformationsFragment fragment = new ChallengeInformationsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }
    */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_challenge, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageViewChallenge = view.findViewById(R.id.imageChallenge);

        txt_material1 = view.findViewById(R.id.txt_material1);
        txt_material2 = view.findViewById(R.id.txt_material2);
        txt_material3 = view.findViewById(R.id.txt_material3);
        txt_material4 = view.findViewById(R.id.txt_material4);
        txt_material5 = view.findViewById(R.id.txt_material5);

        txt_instruction1 = view.findViewById(R.id.txt_instruction1);
        txt_instruction2 = view.findViewById(R.id.txt_instruction2);
        txt_instruction3 = view.findViewById(R.id.txt_instruction3);
        txt_instruction4 = view.findViewById(R.id.txt_instruction4);
        txt_instruction5 = view.findViewById(R.id.txt_instruction5);

        TextView txt_headlineMaterial =  view.findViewById(R.id.txt_headlineMaterial);
        txt_headlineMaterial.setText("Material, das du brauchst:");
        //txt_headlineMaterial.setText(R.string.titel_material);

        setMock();

        if (!challenge.getMaterials().isEmpty()) {
            //setupResourceFields(challenge.getMaterials());
        }

        TextView txt_headlineInstruction = view.findViewById(R.id.txt_headlineInstruction);
        txt_headlineInstruction.setText("Anleitung");
        //txt_headlineInstruction.setText(R.string.titel_instruction);

        if (!challenge.getTutorialSteps().isEmpty()) {
            //setupInstructionFields(challenge.getTutorialSteps());
        }

        ImageView imageViewInstruction = view.findViewById(R.id.imageInstruction);

    }

    private void setMock() {
        txt_material1.setVisibility(View.VISIBLE);
        txt_material1.setText("Papier - jede Größe, Form und Farbe ist erlaubt");
        txt_material2.setVisibility(View.VISIBLE);
        txt_material2.setText("Stifte, ob bunt oder nicht, dick oder dünn, alle Farben kannst du verwenden");
        txt_material3.setVisibility(View.VISIBLE);
        txt_material3.setText("Vielleicht malst du aber lieber mit Farbe, dann kannst du Firus natürlich auch mit Farben malen.");
        txt_material4.setVisibility(View.INVISIBLE);
        txt_material5.setVisibility(View.INVISIBLE);
        txt_instruction1.setVisibility(View.VISIBLE);
        txt_instruction1.setText("Hier darfst du einfach deiner Fantasie freien Lauf lassen! Wie könnte Firus aussehen? Hat er lange Haare oder nicht? Stacheln oder vielleicht ganz lange Zähne? Ist er groß oder ganz klein? Hat er zwei Augen oder ganz viele? Oder hat Firus vielleicht sogar ganz lustige Ohren und trägt am liebsten viel zu große Kleidung?");
        txt_instruction2.setVisibility(View.INVISIBLE);
        txt_instruction3.setVisibility(View.INVISIBLE);
        txt_instruction4.setVisibility(View.INVISIBLE);
        txt_instruction5.setVisibility(View.INVISIBLE);
    }

    private void setupResourceFields(List<String> materials) {
        switch (materials.size()) {
            case 0:
                txt_material1.setVisibility(View.INVISIBLE);
                txt_material2.setVisibility(View.INVISIBLE);
                txt_material3.setVisibility(View.INVISIBLE);
                txt_material4.setVisibility(View.INVISIBLE);
                txt_material5.setVisibility(View.INVISIBLE);
            case 1:
                txt_material1.setVisibility(View.VISIBLE);
                txt_material1.setText(materials.get(0));
                txt_material2.setVisibility(View.INVISIBLE);
                txt_material3.setVisibility(View.INVISIBLE);
                txt_material4.setVisibility(View.INVISIBLE);
                txt_material5.setVisibility(View.INVISIBLE);
            case 2:
                txt_material1.setVisibility(View.VISIBLE);
                txt_material1.setText(materials.get(0));
                txt_material2.setVisibility(View.VISIBLE);
                txt_material1.setText(materials.get(1));
                txt_material3.setVisibility(View.INVISIBLE);
                txt_material4.setVisibility(View.INVISIBLE);
                txt_material5.setVisibility(View.INVISIBLE);
            case 3:
                txt_material1.setVisibility(View.VISIBLE);
                //txt_material1.setText(materials.get(0));
                txt_material1.setText("Test1");
                txt_material2.setVisibility(View.VISIBLE);
                //txt_material2.setText(materials.get(1));
                txt_material2.setText("Test2");
                txt_material3.setVisibility(View.VISIBLE);
                //txt_material3.setText(materials.get(2));
                txt_material3.setText("Test3");
                txt_material4.setVisibility(View.INVISIBLE);
                txt_material5.setVisibility(View.INVISIBLE);
            case 4:
                txt_material1.setVisibility(View.VISIBLE);
                txt_material1.setText(materials.get(0));
                txt_material2.setVisibility(View.VISIBLE);
                txt_material2.setText(materials.get(1));
                txt_material3.setVisibility(View.VISIBLE);
                txt_material3.setText(materials.get(2));
                txt_material4.setVisibility(View.VISIBLE);
                txt_material4.setText(materials.get(3));
                txt_material5.setVisibility(View.INVISIBLE);
            case 5:
                txt_material1.setVisibility(View.VISIBLE);
                txt_material1.setText(materials.get(0));
                txt_material2.setVisibility(View.VISIBLE);
                txt_material2.setText(materials.get(1));
                txt_material3.setVisibility(View.VISIBLE);
                txt_material3.setText(materials.get(2));
                txt_material4.setVisibility(View.VISIBLE);
                txt_material4.setText(materials.get(3));
                txt_material5.setVisibility(View.VISIBLE);
                txt_material5.setText(materials.get(4));
        }
    }

    private void setupInstructionFields(List<String> instructions) {
        switch (instructions.size()) {
            case 0:
                txt_instruction1.setVisibility(View.INVISIBLE);
                txt_instruction2.setVisibility(View.INVISIBLE);
                txt_instruction3.setVisibility(View.INVISIBLE);
                txt_instruction4.setVisibility(View.INVISIBLE);
                txt_instruction5.setVisibility(View.INVISIBLE);
            case 1:
                txt_instruction1.setVisibility(View.VISIBLE);
                txt_instruction1.setText(instructions.get(0));
                txt_instruction2.setVisibility(View.INVISIBLE);
                txt_instruction3.setVisibility(View.INVISIBLE);
                txt_instruction4.setVisibility(View.INVISIBLE);
                txt_instruction5.setVisibility(View.INVISIBLE);
            case 2:
                txt_instruction1.setVisibility(View.VISIBLE);
                txt_instruction1.setText(instructions.get(0));
                txt_instruction2.setVisibility(View.VISIBLE);
                txt_instruction2.setText(instructions.get(1));
                txt_instruction3.setVisibility(View.INVISIBLE);
                txt_instruction4.setVisibility(View.INVISIBLE);
                txt_instruction5.setVisibility(View.INVISIBLE);
            case 3:
                txt_instruction1.setVisibility(View.VISIBLE);
                txt_instruction1.setText(instructions.get(0));
                txt_instruction2.setVisibility(View.VISIBLE);
                txt_instruction2.setText(instructions.get(1));
                txt_instruction3.setVisibility(View.VISIBLE);
                txt_instruction3.setText(instructions.get(2));
                txt_instruction4.setVisibility(View.INVISIBLE);
                txt_instruction5.setVisibility(View.INVISIBLE);
            case 4:
                txt_instruction1.setVisibility(View.VISIBLE);
                txt_instruction1.setText(instructions.get(0));
                txt_instruction2.setVisibility(View.VISIBLE);
                txt_instruction2.setText(instructions.get(1));
                txt_instruction3.setVisibility(View.VISIBLE);
                txt_instruction3.setText(instructions.get(2));
                txt_instruction4.setVisibility(View.VISIBLE);
                txt_instruction4.setText(instructions.get(3));
                txt_instruction5.setVisibility(View.INVISIBLE);
            case 5:
                txt_instruction1.setVisibility(View.VISIBLE);
                txt_instruction1.setText(instructions.get(0));
                txt_instruction2.setVisibility(View.VISIBLE);
                txt_instruction2.setText(instructions.get(1));
                txt_instruction3.setVisibility(View.VISIBLE);
                txt_instruction3.setText(instructions.get(2));
                txt_instruction4.setVisibility(View.VISIBLE);
                txt_instruction4.setText(instructions.get(3));
                txt_instruction5.setVisibility(View.VISIBLE);
                txt_instruction5.setText(instructions.get(4));
        }
    }
}
