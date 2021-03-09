package com.example.matrixcalculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Scanner;

import Jama.Matrix;

public class DeterminantActivity extends AppCompatActivity {
    int cols = 2;
    int rows = 2;

    int pixelW = 250;

    String dirStr = "MatrixCalculator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_determinant);

        TableLayout tableLayout = findViewById(R.id.tableDet);
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

    public void cleanResult(){
        TextView textView = findViewById(R.id.detRes);
        textView.setText("");
    }

    public void expandTable(View view) {
        if(rows > 3){
            Toast toast = Toast.makeText(this, "This is maximal size of matrix!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        cleanResult();

        TableLayout tableLayout = findViewById(R.id.tableDet);
        for(int i = 0; i < rows; i++){
            TableRow tableRow = (TableRow)tableLayout.getChildAt(i);
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
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
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
        tableLayout.addView(tableRow, rows);
        rows = rows + 1;
    }

    public void cutTable(View view){
        if(rows < 2) {
            Toast toast = Toast.makeText(this, "This is minimal size of matrix!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        cleanResult();

        TableLayout tableLayout = findViewById(R.id.tableDet);
        TableRow lastTableRow = (TableRow)tableLayout.getChildAt(rows-1);
        tableLayout.removeView(lastTableRow);
        rows = rows - 1;
        for(int i = 0; i < rows; i++){
            TableRow tableRow = (TableRow)tableLayout.getChildAt(i);
            EditText lastCol = (EditText)tableRow.getChildAt(cols - 1);
            tableRow.removeView(lastCol);
        }
        cols = cols - 1;
    }

    @SuppressLint("SetTextI18n")
    public void calcDeterminant(View view){
        TableLayout tableLayout = findViewById(R.id.tableDet);
        Matrix matrix = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++){
            TableRow tableRow = (TableRow)tableLayout.getChildAt(i);
            for(int j = 0; j < cols; j++){
                EditText cell = (EditText)tableRow.getChildAt(j);
                double value = Double.parseDouble(String.valueOf(cell.getText()));
                matrix.set(i, j, value);
            }
        }
        try {
            double d = matrix.det();
            TextView textView = findViewById(R.id.detRes);
            textView.setText("Визначник: " + d);
            textView.setPadding(10, 10, 10, 10);
        }
        catch (Exception e){
            cleanResult();
            Toast toast = Toast.makeText(this, "Can't calculate determinant!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    // Request code for creating a TXT document.
    private static final int CREATE_FILE = 1;

    public void createFile(View view) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "det.txt");
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
                    Matrix matrix = new Matrix(rows, cols);
                    TableLayout tableLayout = findViewById(R.id.tableDet);
                    for(int i = 0; i < rows; i++){
                        TableRow tableRow = (TableRow)tableLayout.getChildAt(i);
                        for(int j = 0; j < cols; j++){
                            EditText cell = (EditText)tableRow.getChildAt(j);
                            double value = Double.parseDouble(String.valueOf(cell.getText()));
                            text.append(value + " ");
                            matrix.set(i, j, value);
                        }
                        text.append('\n');
                    }
                    text.append("\nВизначник = " + matrix.det());

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
        else if(requestCode == PICK_TXT_FILE){
            if(resultCode == RESULT_OK){
                try {
                    Uri uri = data.getData();
                    readTextFromFile(uri);
                }
                catch (Exception e){
                    Toast toast = Toast.makeText(this, "Data not loaded!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            else{
                Toast toast = Toast.makeText(this, "File not opened!", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    private void readTextFromFile(Uri uri) throws IOException, Exception {
        try (InputStream inputStream =
                     getContentResolver().openInputStream(uri);
             Scanner scanner = new Scanner(new BufferedReader(
                     new InputStreamReader(Objects.requireNonNull(inputStream))))) {
            String nstr = scanner.nextLine().trim();
            int n = Integer.parseInt(nstr);
            Matrix matrix = new Matrix(n, n);
            while (scanner.hasNextLine()){
                for(int i = 0; i < n; i++){
                    String[] line = scanner.nextLine().trim().split(" ");
                    for(int j = 0; j < n; j++){
                        matrix.set(i, j, Double.parseDouble(line[j]));
                    }
                }
            }
            showMatrix(matrix);
        }
        catch (Exception e){
            e.printStackTrace();
            Toast toast = Toast.makeText(this, "Неправильний файл!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void showMatrix(Matrix matrix){
        TableLayout table = findViewById(R.id.tableDet);
        int n = matrix.getRowDimension();
        while (n > rows) expandTable(findViewById(R.id.addRowCol));
        while (n < rows) cutTable(findViewById(R.id.remRowCol));
        for(int i = 0; i < n; i++){
            TableRow row = (TableRow)table.getChildAt(i);
            for(int j = 0; j < n; j++){
                EditText cell = (EditText)row.getChildAt(j);
                cell.setText(String.valueOf(matrix.get(i, j)));
            }
        }
    }

    // Request code for selecting a TXT document.
    private static final int PICK_TXT_FILE = 2;

    public void openFile(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");

        startActivityForResult(intent, PICK_TXT_FILE);
    }

}