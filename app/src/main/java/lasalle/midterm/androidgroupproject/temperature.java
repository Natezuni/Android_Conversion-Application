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

public class temperature extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    Spinner spnInitialValue, spnEndValue;
    Button btnCopy, btnConvert, btnBack;
    EditText edInitialValue, edEndValue;
    String initialValueSpinnerText, endValueSpinnerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        Initialize();
    }

    private void Initialize() {
        edInitialValue = findViewById(R.id.edInitialValueTemperature);
        edEndValue = findViewById(R.id.edEndValueTemperature);

        btnBack = findViewById(R.id.btnBackTemperature);
        btnCopy = findViewById(R.id.btnCopyTemperature);
        btnConvert = findViewById(R.id.btnConvertTemperature);
        btnConvert.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnCopy.setOnClickListener(this);

        spnInitialValue = findViewById(R.id.spnInitialValueTemperature);
        spnEndValue = findViewById(R.id.spnEndValueTemperature);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.temperatureSpinnerItems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnInitialValue.setOnItemSelectedListener(this);
        spnEndValue.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String initialValue, endValue;
        initialValue = edInitialValue.getText().toString();
        endValue = edEndValue.getText().toString();


        switch(id){
            case R.id.btnBackTemperature:
                Intent intentFromTemperatureToConversionOptions = new Intent(this, conversionOptions.class);
                startActivity(intentFromTemperatureToConversionOptions);
                break;
            case R.id.btnCopyTemperature:
                if(endValue != null || endValue == "") {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("converted distance value", endValue);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(this, "Successfully copied to clipboard", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "can't copy if no converted value exists", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnConvertTemperature:
                double temp = Float.parseFloat(initialValue);
                switch(initialValueSpinnerText){
                    case "Celsius":
                        switch(endValueSpinnerText){
                            case "Fahrenheit":
                                temp = temp * 1.8 + 32;
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_LONG).show();
                                break;
                            case "Celsius":
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_LONG).show();
                                break;
                        }
                        break;
                    case "Fahrenheit":
                        switch (endValueSpinnerText){
                            case "Fahrenheit":
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                            case "Celsius":
                                temp = (temp - 32) / 1.8;
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;
                }
                break;
        }
    }

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