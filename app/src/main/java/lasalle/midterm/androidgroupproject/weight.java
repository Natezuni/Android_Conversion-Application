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

public class weight extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button btnBack, btnCopy, btnConvert;
    EditText edInitialValue, edEndValue;
    Spinner spnInitialValue, spnEndValue;
    String initialSpinnerValueText, endSpinnerValueText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        Initialize();
    }

    private void Initialize() {
        edInitialValue = findViewById(R.id.edInitialValueWeight);
        edEndValue = findViewById(R.id.edEndValueWeight);

        btnBack = findViewById(R.id.btnBackWeight);
        btnConvert = findViewById(R.id.btnConvertWeight);
        btnCopy = findViewById(R.id.btnCopyWeight);
        btnBack.setOnClickListener(this);
        btnConvert.setOnClickListener(this);
        btnCopy.setOnClickListener(this);

        spnInitialValue = findViewById(R.id.spnInitialValueWeight);
        spnEndValue = findViewById(R.id.spnEndValueWeight);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.weightSpinnerItems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnInitialValue.setOnItemSelectedListener(this);
        spnEndValue.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String endValue = edEndValue.getText().toString();
        String initialValue = edInitialValue.getText().toString();

        switch(id){
            case R.id.btnBackWeight:
                Intent intentFromWeightToConversionOptions = new Intent(this, conversionOptions.class);
                startActivity(intentFromWeightToConversionOptions);
                break;
            case R.id.btnCopy:
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
            case R.id.btnConvertWeight:
                Float temp = Float.parseFloat(edInitialValue.getText().toString());
                switch(initialSpinnerValueText){
                    case "mg":
                        switch(endSpinnerValueText){
                            case "mg":
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                            case "g":
                                temp /= 1000;
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_LONG).show();
                                break;
                            case "kg":
                                temp /= 1000000;
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;
                    case "g":
                        switch(endSpinnerValueText){
                            case "mg":
                                temp *= 1000;
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                            case "g":
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                            case "kg":
                                temp /= 1000;
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_LONG).show();
                                break;

                        }
                        break;
                    case "kg":
                        switch(endSpinnerValueText){
                            case "mg":
                                temp *= 1000000;
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                            case "g":
                                temp *= 1000;
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_SHORT).show();
                                break;
                            case "kg":
                                edEndValue.setText(String.valueOf(temp));
                                Toast.makeText(this, "Converted", Toast.LENGTH_LONG).show();
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
        if(selectedSpinner.getId() == R.id.spnInitialValueWeight){
            initialSpinnerValueText = parent.getItemAtPosition(position).toString();
        }
        else if(selectedSpinner.getId() == R.id.spnEndValueWeight){
            endSpinnerValueText = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}