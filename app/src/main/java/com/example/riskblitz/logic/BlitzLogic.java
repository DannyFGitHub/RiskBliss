package com.example.riskblitz.logic;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class BlitzLogic {

    StringBuilder battleLog = new StringBuilder();

    private int attackerSoldierNum, defenderSoldierNum;

    class Dice{
        private int diceMax;
        private Random random;
        public Dice(int diceMax){
            this.diceMax = diceMax;
            // Random seed generator
            Random randomSeedGen = new Random();
            this.random = new Random(randomSeedGen.nextInt(99) * randomSeedGen.nextInt(99));
        }
        public int rollDice(){
            int diceMin = 1;
            return random.nextInt((diceMax - diceMin) + 1) + diceMin;
        }
    }

    public BlitzLogic(int attackerSoldierNum, int defenderSoldierNum){
        this.attackerSoldierNum = attackerSoldierNum;
        this.defenderSoldierNum = defenderSoldierNum;
    }

    public void runBlitzTillVictory(){
        int runCounter = 0;
        // Need more than 1 to attack, need 1 or more to defend
        while(attackerSoldierNum > 1 && defenderSoldierNum >= 1){
            ArrayList<Integer> attackDiceNumbers = new ArrayList<>();
            if(attackerSoldierNum > 3){
                Dice attackDice1 = new Dice(6);
                Dice attackDice2 = new Dice(6);
                Dice attackDice3 = new Dice(6);
                attackDiceNumbers.add(attackDice1.rollDice());
                attackDiceNumbers.add(attackDice2.rollDice());
                attackDiceNumbers.add(attackDice3.rollDice());
            } else if(attackerSoldierNum > 2){
                Dice attackDice1 = new Dice(6);
                Dice attackDice2 = new Dice(6);
                attackDiceNumbers.add(attackDice1.rollDice());
                attackDiceNumbers.add(attackDice2.rollDice());
            } else if (attackerSoldierNum == 2){
                Dice attackDice1 = new Dice(6);
                attackDiceNumbers.add(attackDice1.rollDice());
            }
            attackDiceNumbers.sort(Integer::compareTo);

            ArrayList<Integer> defendDiceNumbers = new ArrayList<>();
            if(defenderSoldierNum >= 2){
                Dice defenderDice1 = new Dice(6);
                Dice defenderDice2 = new Dice(6);
                defendDiceNumbers.add(defenderDice1.rollDice());
                defendDiceNumbers.add(defenderDice2.rollDice());
            } else if(defenderSoldierNum == 1){
                Dice defenderDice1 = new Dice(6);
                defendDiceNumbers.add(defenderDice1.rollDice());
            }
            defendDiceNumbers.sort(Integer::compareTo);

            runCounter++;
            battleLog.append("Round " + runCounter + "\n");
            battleLog.append("Attacker DiceRolls : " + Arrays.toString(attackDiceNumbers.toArray())  + "\n");
            battleLog.append("Defender DiceRolls : " + Arrays.toString(defendDiceNumbers.toArray())  + "\n");

            battleLog.append("Contesting DiceRolls" + "\n");
            int minArraySize = Math.min(attackDiceNumbers.size(), defendDiceNumbers.size());
            int attackerLosesNum = 0;
            int defenderLosesNum = 0;
            for(int i = 0; i < minArraySize; i++){
                int attackNum = attackDiceNumbers.get(i);
                int defendNum = defendDiceNumbers.get(i);
                int result = Integer.compare(attackNum, defendNum);
                battleLog.append("Top " + (i + 1) + " DiceRoll : " + "Atk:[" + attackNum + "] Def:[" + defendNum  + "]\n");
                if(result == 1){
                    defenderLosesNum++;
                    battleLog.append("Defender Lost Dice Contest" + "\n");
                } else if (result == 0){
                    attackerLosesNum++;
                    battleLog.append("Attacker Lost Dice Contest" + "\n");
                } else if (result == -1){
                    attackerLosesNum++;
                    battleLog.append("Attacker Lost Dice Contest" + "\n");
                }
                battleLog.append("\n");
            }

            attackerSoldierNum -= attackerLosesNum;
            defenderSoldierNum -= defenderLosesNum;
        }

        if(attackerSoldierNum == defenderSoldierNum) {
            battleLog.append("\nRESULT:" + "Stale Mate\n");
        } else if(attackerSoldierNum > defenderSoldierNum){
            battleLog.append("\nRESULT:" + "Attacker Won\n");
        } else {
            battleLog.append("\nRESULT:" + "Defender Won\n");
        }

        battleLog.append("\nAllocation:" +
                "\nAttacker keeps " + attackerSoldierNum + " soldiers." +
                "\nDefender keeps " + defenderSoldierNum + " soldiers.\n");
    }

    public String getBattleLog(){
        return battleLog.toString();
    }
}
