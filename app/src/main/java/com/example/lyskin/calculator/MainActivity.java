package com.example.lyskin.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int[] numericButtons = {R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree,
            R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine};

    private TextView txtScreen;
    private DbProcesses dbProcesses;
    String expressionWithResult;
    String expressionWithResultCopy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expressionWithResultCopy = "x";
        txtScreen = findViewById(R.id.txtScreen);

        DbHelper dbHelper = new DbHelper(getBaseContext());
        dbProcesses = new DbProcesses(dbHelper);

        setNumericOnClickListener();

        final Button buttonDiv = findViewById(R.id.btnDivide);
        final Button buttonMulti = findViewById(R.id.btnMultiply);
        final Button buttonAdd = findViewById(R.id.btnAdd);
        final Button buttonSub = findViewById(R.id.btnSubtract);
        Button buttonHistory = findViewById(R.id.btnHistory);
        Button buttonC = findViewById(R.id.btnClear);
        final Button buttonEval = findViewById(R.id.btnEqual);
        final Button buttonDot = findViewById(R.id.btnDot);

        buttonC.setOnClickListener(v -> txtScreen.setText(""));
        buttonDot.setOnClickListener(v -> {
            if(Validation.isOperationCanBeEvaluate(txtScreen.getText().toString())){
                txtScreen.append(buttonDot.getText());
            }
        });
        buttonDiv.setOnClickListener(v -> {
            if(Validation.isOperationCanBeEvaluate(txtScreen.getText().toString())){
                txtScreen.append(buttonDiv.getText());
            }
        });
        buttonMulti.setOnClickListener(v -> {
            if(Validation.isOperationCanBeEvaluate(txtScreen.getText().toString())){
                txtScreen.append(buttonMulti.getText());
            }
        });

        buttonAdd.setOnClickListener(v -> {
            if(Validation.isOperationCanBeEvaluate(txtScreen.getText().toString())){
                txtScreen.append(buttonAdd.getText());
            }
        });
        buttonSub.setOnClickListener(v -> {
            if(Validation.isOperationCanBeEvaluate(txtScreen.getText().toString())){
                txtScreen.append(buttonSub.getText());
            }
        });

        buttonHistory.setOnClickListener(v -> {
            txtScreen.setText("");
            Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
            startActivity(intent);
        });

        buttonEval.setOnClickListener(view -> {
            if(Validation.isValidResult(Validation.calculate(txtScreen.getText().toString()))) {
                dbProcesses.setDataIntoDb(txtScreen.getText().toString());
                txtScreen.setText(String.valueOf(Validation.calculate(txtScreen.getText().toString())));
            } else {
                Toast.makeText(this,"Invalid Exression", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setNumericOnClickListener() {
        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            txtScreen.append(button.getText());
        };
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    @Override
    protected void onDestroy() {
        dbProcesses.closeConnection();
        super.onDestroy();
    }
}
