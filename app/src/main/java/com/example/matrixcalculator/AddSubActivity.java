package com.example.matrixcalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStream;

import Jama.Matrix;

public class AddSubActivity extends AppCompatActivity {
    int rows = 2;
    int cols = 2;

    int pixelW = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);

        TableLayout tableLayoutA = findViewById(R.id.tableMatrixA);
        tableLayoutA.setPadding(0, 10, 0, 0);

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
            tableLayoutA.addView(tableRow, i);
        }
        TableLayout tableLayoutB = findViewById(R.id.tableMatrixB);
        tableLayoutB.setPadding(0, 10, 0, 0);

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
            tableLayoutB.addView(tableRow, i);
        }
    }

    @SuppressLint("DefaultLocale")
    public void addMatrixes(View view){
        cleanTitle();
        cleanResult();
        TableLayout tableLayoutA = findViewById(R.id.tableMatrixA);
        Matrix matrixA = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++){
            TableRow tableRow = (TableRow)tableLayoutA.getChildAt(i);
            for(int j = 0; j < cols; j++){
                EditText cell = (EditText)tableRow.getChildAt(j);
                double value = Double.parseDouble(String.valueOf(cell.getText()));
                matrixA.set(i, j, value);
            }
        }

        TableLayout tableLayoutB = findViewById(R.id.tableMatrixB);
        Matrix matrixB = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++){
            TableRow tableRow = (TableRow)tableLayoutB.getChildAt(i);
            for(int j = 0; j < cols; j++){
                EditText cell = (EditText)tableRow.getChildAt(j);
                double value = Double.parseDouble(String.valueOf(cell.getText()));
                matrixB.set(i, j, value);
            }
        }

        Matrix result = matrixA.plus(matrixB);
        TextView resultTitle = findViewById(R.id.addSubResultTitle);
        resultTitle.setText("A + B");
        TableLayout resTable = findViewById(R.id.AddSubMatrixRes);
        resTable.setPadding(0, 10, 0, 0);
        for(int i = 0; i < rows; i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
            for(int j = 0; j < cols; j++){
                TextView textView = new TextView(this);
                textView.setText(String.format("%.2f", result.get(i, j)));
                textView.setTextSize(30);
                textView.setPadding(10, 10, 10, 10);
                tableRow.addView(textView, j);
            }
            resTable.addView(tableRow, i);
        }
    }
    @SuppressLint("DefaultLocale")
    public void subMatrixes(View view){
        cleanTitle();
        cleanResult();
        TableLayout tableLayoutA = findViewById(R.id.tableMatrixA);
        Matrix matrixA = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++){
            TableRow tableRow = (TableRow)tableLayoutA.getChildAt(i);
            for(int j = 0; j < cols; j++){
                EditText cell = (EditText)tableRow.getChildAt(j);
                double value = Double.parseDouble(String.valueOf(cell.getText()));
                matrixA.set(i, j, value);
            }
        }

        TableLayout tableLayoutB = findViewById(R.id.tableMatrixB);
        Matrix matrixB = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++){
            TableRow tableRow = (TableRow)tableLayoutB.getChildAt(i);
            for(int j = 0; j < cols; j++){
                EditText cell = (EditText)tableRow.getChildAt(j);
                double value = Double.parseDouble(String.valueOf(cell.getText()));
                matrixB.set(i, j, value);
            }
        }

        Matrix result = matrixA.minus(matrixB);
        TextView resultTitle = findViewById(R.id.addSubResultTitle);
        resultTitle.setText("A - B");
        TableLayout resTable = findViewById(R.id.AddSubMatrixRes);
        resTable.setPadding(0, 10, 0, 0);
        for(int i = 0; i < rows; i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
            for(int j = 0; j < cols; j++){
                TextView textView = new TextView(this);
                textView.setText(String.format("%.2f", result.get(i, j)));
                textView.setTextSize(30);
                textView.setPadding(10, 10, 10, 10);
                tableRow.addView(textView, j);
            }
            resTable.addView(tableRow, i);
        }
    }
    public void addRow(View view){
        if(rows > 3){
            Toast toast = Toast.makeText(this, "Це максимальний розмір матриці!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        cleanTitle();
        cleanResult();

        TableLayout tableA = findViewById(R.id.tableMatrixA);
        TableRow tableRowA = new TableRow(this);
        tableRowA.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
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

        TableLayout tableB = findViewById(R.id.tableMatrixB);
        TableRow tableRowB = new TableRow(this);
        tableRowB.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        tableRowB.setGravity(Gravity.CENTER_HORIZONTAL);
        for(int j = 0; j < cols; j++){
            EditText editText = new EditText(this);
            TableRow.LayoutParams editTextParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            editText.setLayoutParams(editTextParams);
            editText.setMaxWidth(pixelW);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            editText.setText("0.0");
            editText.setTextSize(30);
            editText.setPadding(10, 10, 10, 10);
            tableRowB.addView(editText, j);
        }
        tableB.addView(tableRowB, rows);
        rows = rows + 1;
    }
    public void cutRow(View view){
        if(rows < 2){
            Toast toast = Toast.makeText(this, "Це мінімальний розмір матриці!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        cleanTitle();
        cleanResult();

        TableLayout tableA = findViewById(R.id.tableMatrixA);
        TableRow lastTableRowA = (TableRow)tableA.getChildAt(rows-1);
        tableA.removeView(lastTableRowA);
        TableLayout tableB = findViewById(R.id.tableMatrixB);
        TableRow lastTableRowB = (TableRow)tableB.getChildAt(rows-1);
        tableB.removeView(lastTableRowB);
        rows = rows - 1;
    }
    public void addCol(View view){
        if(cols > 3){
            Toast toast = Toast.makeText(this, "Це максимальний розмір матриці!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        cleanTitle();
        cleanResult();

        TableLayout tableA = findViewById(R.id.tableMatrixA);
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

        TableLayout tableB = findViewById(R.id.tableMatrixB);
        for(int i = 0; i < rows; i++){
            TableRow tableRow = (TableRow)tableB.getChildAt(i);
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

        cleanTitle();
        cleanResult();

        TableLayout tableA = findViewById(R.id.tableMatrixA);
        for(int i = 0; i < rows; i++){
            TableRow tableRow = (TableRow)tableA.getChildAt(i);
            EditText lastCol = (EditText)tableRow.getChildAt(cols - 1);
            tableRow.removeView(lastCol);
        }

        TableLayout tableB = findViewById(R.id.tableMatrixB);
        for(int i = 0; i < rows; i++){
            TableRow tableRow = (TableRow)tableB.getChildAt(i);
            EditText lastCol = (EditText)tableRow.getChildAt(cols - 1);
            tableRow.removeView(lastCol);
        }
        cols = cols - 1;
    }

    public void cleanTitle(){
        TextView resultTitle = findViewById(R.id.addSubResultTitle);
        resultTitle.setText("");
    }
    public void cleanResult(){
        TableLayout resultTable = findViewById(R.id.AddSubMatrixRes);
        resultTable.removeAllViewsInLayout();
    }

    // Request code for creating a PDF document.
    private static final int CREATE_FILE = 1;

    public void createFile(View view) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "addsub.txt");
        startActivityForResult(intent, CREATE_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CREATE_FILE){
            if(resultCode == RESULT_OK){
                try{
                    Uri uri = data.getData();
                    OutputStream os = getContentResolver().openOutputStream(uri);
                    StringBuilder text = new StringBuilder();
                    text.append("A = \n");
                    Matrix matrixA = new Matrix(rows, cols);
                    TableLayout tableLayout = findViewById(R.id.tableMatrixA);
                    for(int i = 0; i < rows; i++){
                        TableRow tableRow = (TableRow)tableLayout.getChildAt(i);
                        for(int j = 0; j < cols; j++){
                            EditText cell = (EditText)tableRow.getChildAt(j);
                            double value = Double.parseDouble(String.valueOf(cell.getText()));
                            text.append(value + " ");
                            matrixA.set(i, j, value);
                        }
                        text.append('\n');
                    }
                    text.append("B = \n");
                    Matrix matrixB = new Matrix(rows, cols);
                    TableLayout tableLayoutB = findViewById(R.id.tableMatrixB);
                    for(int i = 0; i < rows; i++){
                        TableRow tableRow = (TableRow)tableLayoutB.getChildAt(i);
                        for(int j = 0; j < cols; j++){
                            EditText cell = (EditText)tableRow.getChildAt(j);
                            double value = Double.parseDouble(String.valueOf(cell.getText()));
                            text.append(value + " ");
                            matrixB.set(i, j, value);
                        }
                        text.append('\n');
                    }

                    Matrix plus = matrixA.plus(matrixB);
                    Matrix sub = matrixA.minus(matrixB);

                    text.append("A + B = \n");
                    for(int i = 0; i < plus.getRowDimension(); i++){
                        for(int j = 0; j < plus.getColumnDimension(); j++){
                            double value = plus.get(i, j);
                            text.append(value + " ");
                        }
                        text.append('\n');
                    }

                    text.append("A - B = \n");
                    for(int i = 0; i < sub.getRowDimension(); i++){
                        for(int j = 0; j < sub.getColumnDimension(); j++){
                            double value = sub.get(i, j);
                            text.append(value + " ");
                        }
                        text.append('\n');
                    }

                    os.write(text.toString().getBytes());
                    os.close();
                    Toast toast = Toast.makeText(this, "File saved!", Toast.LENGTH_LONG);
                    toast.show();
                }
                catch (Exception e) {
                    Toast toast = Toast.makeText(this, "Failed to save file!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            else{
                Toast toast = Toast.makeText(this, "File not saved!", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}