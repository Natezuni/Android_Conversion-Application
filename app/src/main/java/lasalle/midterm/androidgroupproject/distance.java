package lasalle.midterm.androidgroupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class distance extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button btnConvert, btnCopy;
    Spinner spnInitialMetric, spnEndMetric;
    EditText edInitialValue, edEndValue;
    String initialValueSpinnerText, endValueSpinnerText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);
        Initialize();
    }

    private void Initialize() {

        btnConvert = findViewById(R.id.btnConvert);
        btnCopy = findViewById(R.id.btnCopy);
        btnConvert.setOnClickListener(this);
        btnCopy.setOnClickListener(this);

        spnInitialMetric = findViewById(R.id.spnInitialValue);
        spnEndMetric = findViewById(R.id.spnEndValue);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.distanceSpinnerItems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnInitialMetric.setAdapter(adapter);
        spnEndMetric.setAdapter(adapter);
        spnInitialMetric.setOnItemSelectedListener(this);
        spnEndMetric.setOnItemSelectedListener(this);

        edInitialValue = findViewById(R.id.edInitialValue);
        edEndValue = findViewById(R.id.edEndValue);
    }

    @Override
    public void onClick(View v) {
        String initialDistance, endDistance;
        initialDistance = edInitialValue.getText().toString();
        endDistance = edEndValue.getText().toString();
        int id = v.getId();

        switch (id){
            case R.id.btnConvert:
                if(initialDistance != null || endDistance != "") {
                    float temp = Float.parseFloat(initialDistance);
                    switch (initialValueSpinnerText){
                        case "mm":
                            switch(endValueSpinnerText){
                                case "mm":
                                    edEndValue.setText(initialDistance);
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                                case "cm":
                                    temp = temp / 10;
                                    edEndValue.setText(String.valueOf(temp));
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                                case "m":
                                    temp = temp / 1000;
                                    edEndValue.setText(String.valueOf(temp));
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                                case "km":
                                    temp = temp / 1000000;
                                    edEndValue.setText(String.valueOf(temp));
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;
                        case "cm":
                            switch(endValueSpinnerText){
                                case "mm":
                                    temp = temp * 10;
                                    edEndValue.setText(String.valueOf(temp));
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                                case "cm":
                                    edEndValue.setText(String.valueOf(temp));
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                                case "m":
                                    temp = temp / 100;
                                    edEndValue.setText(String.valueOf(temp));
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                                case "km":
                                    temp = temp / 100000;
                                    edEndValue.setText(String.valueOf(temp));
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;
                        case "m":
                            switch(endValueSpinnerText){
                                case "mm":
                                    temp = temp * 100;
                                    edEndValue.setText(String.valueOf(temp));
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                                case "cm":
                                    temp = temp * 10;
                                    edEndValue.setText(String.valueOf(temp));
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                                case "m":
                                    edEndValue.setText(String.valueOf(temp));
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                                case "km":
                                    temp = temp / 1000;
                                    edEndValue.setText(String.valueOf(temp));
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;
                        case "km":
                            switch(endValueSpinnerText){
                                case "mm":
                                    temp = temp * 100000;
                                    edEndValue.setText(String.valueOf(temp));
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                                case "cm":
                                    temp = temp * 10000;
                                    edEndValue.setText(String.valueOf(temp));
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                                case "m":
                                    temp = temp * 1000;
                                    edEndValue.setText(String.valueOf(temp));
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                                case "km":
                                    edEndValue.setText(String.valueOf(temp));
                                    Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;
                    }
                }
                else{
                    Toast.makeText(this, "initial value empty", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnCopy:
                if(endDistance != null || endDistance == "") {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("converted distance value", endDistance);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(this, "Successfully copied to clipboard", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "can't copy if no converted value exists", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnBackDistance:
                Intent intentFromDistanceToConversionOptions = new Intent(this, conversionOptions.class);
                startActivity(intentFromDistanceToConversionOptions);
                break;
        }

    }

    //On selected item in spinner change
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner selectedSpinner = (Spinner) parent;
        if(selectedSpinner.getId() == R.id.spnInitialValue){
            initialValueSpinnerText =  parent.getItemAtPosition(position).toString();
        }
        else if(selectedSpinner.getId() == R.id.spnEndValue){
            endValueSpinnerText = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}