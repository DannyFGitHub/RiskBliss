package com.example.riskblitz;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.riskblitz.logic.BlitzLogic;

public class MainActivity extends AppCompatActivity {

    EditText attackerSoldierNum;
    EditText defenderSoldierNum;
    Button blitzButton;

    Button setAttacker1;
    Button setAttacker2;
    Button setAttacker3;
    Button setDefender1;
    Button setDefender2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attackerSoldierNum = findViewById(R.id.edittext_num_soldiers_pl1);
        defenderSoldierNum = findViewById(R.id.edittext_num_soldiers_pl2);
        blitzButton = findViewById(R.id.start_blitz_button);
        blitzButton.setOnClickListener(view -> runBlitz());

        setAttacker1 = findViewById(R.id.set_attacker_1_btn);
        setAttacker2 = findViewById(R.id.set_attacker_2_btn);
        setAttacker3 = findViewById(R.id.set_attacker_3_btn);
        setDefender1 = findViewById(R.id.set_defender_1_btn);
        setDefender2 = findViewById(R.id.set_defender_2_btn);

        setAttacker1.setOnClickListener(view -> {
            attackerSoldierNum.setText("1");
        });
        setAttacker2.setOnClickListener(view -> {
            attackerSoldierNum.setText("2");
        });
        setAttacker3.setOnClickListener(view -> {
            attackerSoldierNum.setText("3");
        });
        setDefender1.setOnClickListener(view -> {
            defenderSoldierNum.setText("1");
        });
        setDefender2.setOnClickListener(view -> {
            defenderSoldierNum.setText("2");
        });
    }

    private void runBlitz(){
        int attackerSoldierCount = 0;
        try {
            attackerSoldierCount = Integer.parseInt(attackerSoldierNum.getText().toString());
        } catch (Exception exception){
            //Couldn't parse will set to 0
        }
        int defenderSoldierCount = 0;
        try {
            defenderSoldierCount = Integer.parseInt(defenderSoldierNum.getText().toString());
        } catch (Exception exception){
            //Couldn't parse will set to 0
        }

        BlitzLogic blitzLogic = new BlitzLogic(attackerSoldierCount, defenderSoldierCount);
        blitzLogic.runBlitzTillVictory();
        AlertDialog.Builder battleLogDialog = new AlertDialog.Builder(this);
        battleLogDialog.setCancelable(false);
        battleLogDialog.setMessage(blitzLogic.getBattleLog());
        battleLogDialog.setPositiveButton("Done", (dialogInterface, i) -> dialogInterface.cancel());
        battleLogDialog.show();
    }
}