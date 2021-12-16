package lasalle.midterm.androidgroupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity implements View.OnClickListener{


    Button btnLogin, btnRegister;
    EditText edUserName, edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Initialize();
    }

    private void Initialize() {

        edUserName = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegisterAccountLoginPage);
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String userName = edUserName.getText().toString();
        String password = edPassword.getText().toString();

        switch(id){
            case R.id.btnLogin:
                Intent intentFromLogin = new Intent(this, conversionOptions.class);
                startActivity(intentFromLogin);
                break;
            case R.id.btnRegister:
                Intent intentFromRegister = new Intent(this, register.class);
                startActivity(intentFromRegister);
                break;
        }
    }

    public Boolean checkLogin(String username, String password) {
        if (username == "admin" && password == "padmin") {
            return true;
        } else {
            return false;
        }
    }
}