package com.example.matrixcalculator;

import android.content.Intent;
import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.HeatDataEntry;
import com.anychart.charts.HeatMap;
import com.anychart.enums.SelectionMode;
import com.anychart.graphics.vector.SolidFill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import Jama.Matrix;

public class HeatmapActivity extends AppCompatActivity {

    int rows = 2;
    int cols = 2;

    int pixelW = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heatmap);

        TableLayout tableLayout = findViewById(R.id.tableMatrixHM);
        tableLayout.setPadding(0, 10, 0, 0);

        for(int i = 0; i < rows; i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
            for(int j = 0; j < cols; j++){
                EditText editText = new EditText(this);
                TableRow.LayoutParams editTextParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
                editText.setLayoutParams(editTextParams);
                editText.setMaxWidth(pixelW);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                editText.setText("0.0");
                editText.setTextSize(30);
                editText.setPadding(10, 10, 10, 10);
                tableRow.addView(editText, j);
            }
            tableLayout.addView(tableRow, i);
        }
    }

    public void addRow(View view){
        if(rows > 3){
            Toast toast = Toast.makeText(this, "Це максимальний розмір матриці!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        TableLayout tableA = findViewById(R.id.tableMatrixHM);
        TableRow tableRowA = new TableRow(this);
        tableRowA.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tableRowA.setGravity(Gravity.CENTER_HORIZONTAL);
        for(int j = 0; j < cols; j++){
            EditText editText = new EditText(this);
            TableRow.LayoutParams editTextParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            editText.setLayoutParams(editTextParams);
            editText.setMaxWidth(pixelW);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            editText.setText("0.0");
            editText.setTextSize(30);
            editText.setPadding(10, 10, 10, 10);
            tableRowA.addView(editText, j);
        }
        tableA.addView(tableRowA, rows);
        rows = rows + 1;
    }
    public void cutRow(View view){
        if(rows < 2){
            Toast toast = Toast.makeText(this, "Це мінімальний розмір матриці!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        TableLayout tableA = findViewById(R.id.tableMatrixHM);
        TableRow lastTableRowA = (TableRow)tableA.getChildAt(rows-1);
        tableA.removeView(lastTableRowA);

        rows = rows - 1;
    }
    public void addCol(View view){
        if(cols > 3){
            Toast toast = Toast.makeText(this, "Це максимальний розмір матриці!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        TableLayout tableA = findViewById(R.id.tableMatrixHM);
        for(int i = 0; i < rows; i++){
            TableRow tableRow = (TableRow)tableA.getChildAt(i);
            EditText editText = new EditText(this);
            TableRow.LayoutParams editTextParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            editText.setLayoutParams(editTextParams);
            editText.setMaxWidth(pixelW);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            editText.setText("0.0");
            editText.setTextSize(30);
            editText.setPadding(10, 10, 10, 10);
            tableRow.addView(editText, cols);
        }
        cols = cols + 1;
    }
    public void cutCol(View view){
        if(cols < 2){
            Toast toast = Toast.makeText(this, "Це мінімальний розмір матриці!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        TableLayout tableA = findViewById(R.id.tableMatrixHM);
        for(int i = 0; i < rows; i++){
            TableRow tableRow = (TableRow)tableA.getChildAt(i);
            EditText lastCol = (EditText)tableRow.getChildAt(cols - 1);
            tableRow.removeView(lastCol);
        }
        cols = cols - 1;
    }
    public void plot(View view){
        Intent intent = new Intent(this, DrawingHMActivity.class);
        Matrix matrix = new Matrix(rows, cols);
        TableLayout tableLayout = findViewById(R.id.tableMatrixHM);
        for(int i = 0; i < rows; i++){
            TableRow tableRow = (TableRow)tableLayout.getChildAt(i);
            for(int j = 0; j < cols; j++){
                EditText cell = (EditText)tableRow.getChildAt(j);
                double value = Double.parseDouble(String.valueOf(cell.getText()));
                matrix.set(i, j, value);
            }
        }
        intent.putExtra(Matrix.class.getSimpleName(), matrix);
        startActivity(intent);
    }
}