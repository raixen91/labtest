package com.example.mylabtest3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView problemDisplay, point;
    private EditText enterNumber;
    private Button check;
    private RadioGroup levelSelection;
    private int currentLevel, firstNumber, secondNumber, operator, currentPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        problemDisplay = findViewById(R.id.problemDisplay);
        point = findViewById(R.id.point);
        enterNumber = findViewById(R.id.enter_number);
        check = findViewById(R.id.check);
        levelSelection = findViewById(R.id.levelSelection);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer();
            }
        });

        levelSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedLevel = findViewById(checkedId);
                if (selectedLevel.getText().toString().equals("i3")) {
                    currentLevel = 1;
                } else if (selectedLevel.getText().toString().equals("i5")) {
                    currentLevel = 2;
                } else if (selectedLevel.getText().toString().equals("i7")) {
                    currentLevel = 3;
                }
                generateProblem();
            }
        });
    }

    private void generateProblem() {
        Random random = new Random();
        if (currentLevel == 1) {
            firstNumber = random.nextInt(10);
            secondNumber = random.nextInt(10);
        } else if (currentLevel == 2) {
            firstNumber = random.nextInt(100);
            secondNumber = random.nextInt(100);
        } else if (currentLevel == 3) {
            firstNumber = random.nextInt(1000);
            secondNumber = random.nextInt(1000);
        }
        operator = random.nextInt(4);
        String display = getDisplay(operator);
        problemDisplay.setText(firstNumber + " " + display + " " + secondNumber);
    }

    private String getDisplay(int op) {
        switch (op) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "*";
            case 3:
                return "/";
            default:
                return "+";
        }
    }

    private void verifyAnswer() {
        int userAnswer = Integer.parseInt(enterNumber.getText().toString());
        int correctAnswer = calculateAnswer();
        if (userAnswer == correctAnswer) {
            currentPoints++;
        } else {
            currentPoints--;
        }
        point.setText(String.valueOf(currentPoints));
        generateProblem();
    }

    private int calculateAnswer() {
        switch (operator) {
            case 0:
                return firstNumber + secondNumber;
            case 1:
                return firstNumber - secondNumber;
            case 2:
                return firstNumber * secondNumber;
            case 3:
                return firstNumber / secondNumber;
            default:
                return firstNumber + secondNumber;
        }
    }
}
