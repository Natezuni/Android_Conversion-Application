package lasalle.midterm.androidgroupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class conversionOptions extends AppCompatActivity implements View.OnClickListener{

    Button btnTime, btnTemperature, btnWeight, btnDistance, btnLogoutConversionPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_options);

        btnTime = findViewById(R.id.btnTime);
        btnTemperature = findViewById(R.id.btnTemperature);
        btnWeight = findViewById(R.id.btnWeight);
        btnDistance = findViewById(R.id.btnDistance);
        btnLogoutConversionPage = findViewById(R.id.btnLogoutConversionPage);

        btnTime.setOnClickListener(this);
        btnTemperature.setOnClickListener(this);
        btnWeight.setOnClickListener(this);
        btnDistance.setOnClickListener(this);
        btnLogoutConversionPage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch(id){
            case R.id.btnTime:
                Intent intentFromTime = new Intent(this, time.class);
                startActivity(intentFromTime);
                break;
            case R.id.btnTemperature:
                Intent intentFromTemperature = new Intent(this, temperature.class);
                startActivity(intentFromTemperature);
                break;
            case R.id.btnWeight:
                Intent intentFromWeight = new Intent(this, weight.class);
                startActivity(intentFromWeight);
                break;
            case R.id.btnDistance:
                Intent intentFromDistance = new Intent(this, distance.class);
                startActivity(intentFromDistance);
                break;
            case R.id.btnLogoutConversionPage:
                Intent intentFromLogoutConversionPage = new Intent(this, login.class);
                startActivity(intentFromLogoutConversionPage);
                break;
        }
    }
}