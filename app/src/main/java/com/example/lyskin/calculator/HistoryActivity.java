package com.example.lyskin.calculator;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class HistoryActivity  extends AppCompatActivity {

    private DbProcesses dbProcesses;
    private ListView listView;
    Button buttonClearHistory;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        buttonClearHistory = findViewById(R.id.buttonDelete);
        listView = findViewById(R.id.ListViewData);
        DbHelper dbHelper = new DbHelper(getBaseContext());
        dbProcesses = new DbProcesses(dbHelper);
        setListView();

        buttonClearHistory.setOnClickListener(v -> {
            dbProcesses.deleteDataFromDb();
            setListView();
        });

    }

    private void setListView() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.history_list_element,dbProcesses.getDataFromDb());
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onDestroy() {
        dbProcesses.closeConnection();
        super.onDestroy();
    }
}