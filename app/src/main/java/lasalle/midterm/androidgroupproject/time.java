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

public class time extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button btnCopy, btnConvert, btnBack;
    Spinner spnInitialValue, spnEndValue;
    EditText edInitialValue, edEndValue;
    String initialValueSpinnerText, endValueSpinnerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        Initialize();
    }

    private void Initialize() {
        edInitialValue = findViewById(R.id.edInitialValueTime);
        edEndValue = findViewById(R.id.edEndValueTime);

        btnBack = findViewById(R.id.btnBackTime);
        btnConvert = findViewById(R.id.btnConvertTime);
        btnCopy = findViewById(R.id.btnCopyTime);
        btnBack.setOnClickListener(this);
        btnCopy.setOnClickListener(this);
        btnConvert.setOnClickListener(this);

        spnInitialValue = findViewById(R.id.spnInitialValueTime);
        spnEndValue = findViewById(R.id.spnEndValueTime);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.timeSpinnerItems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnInitialValue.setOnItemSelectedListener(this);
        spnEndValue.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        String initialValue = edInitialValue.getText().toString();
        String endValue = edEndValue.getText().toString();
        Float temp = Float.parseFloat(edInitialValue.getText().toString());

        switch(id){
            case R.id.btnBackTime:
                Intent intentFromTimeToConversionOptions = new Intent(this, conversionOptions.class);
                startActivity(intentFromTimeToConversionOptions);
                break;
            case R.id.btnCopyTime:
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
            case R.id.btnConvertTime:
                switch(initialValueSpinnerText){
                    case "S":
                        switch (endValueSpinnerText){
                            case "S":
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                            case "M":
                                temp = temp / 60;
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                            case "H":
                                temp /= 3600;
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;
                    case "M":
                        switch (endValueSpinnerText){
                            case "S":
                                temp *= 60;
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                            case "M":
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                            case "H":
                                temp /= 60;
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;
                    case "H":
                        switch (endValueSpinnerText){
                            case "S":
                                temp = temp * 3600;
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                            case "M":
                                temp = temp * 60;
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                            case "H":
                                edEndValue.setText(String.valueOf(temp));
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