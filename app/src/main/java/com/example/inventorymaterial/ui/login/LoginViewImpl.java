package com.example.inventorymaterial.ui.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.inventorymaterial.DashboardActivity;
import com.example.inventorymaterial.R;
import com.example.inventorymaterial.ui.base.BaseActivity;

/**
 * Esta clase da soporte de código a la activity del login
 * @author Carlos Cruz Domínguez
 */

//Heredarán de BaseActivity porque las actividades tienen cosas en comun.
public class LoginViewImpl extends BaseActivity implements View.OnClickListener, LoginView{

    LoginPresenterImpl loginPresenter;
    private Button btnSignIn;
    private EditText edtUser;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenterImpl(this);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSignIn) {
            loginPresenter.validateCredentials(edtUser.getText().toString(), edtPassword.getText().toString());
            /*Intent intent = new Intent(LoginViewImpl.this, DashboardActivity.class);
            strtActivity(intent);*/
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Si tienes objetos inicializados los destruyes y una vez destruidos lo igualamos a null.
        loginPresenter.onDestroy();
        loginPresenter = null;
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(LoginViewImpl.this, DashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void setUserEmptyError() {
        //Usamos un ID comun para todas las activitys que vaya a usar un snackbar para simplificar. Lo unico que variara
        //es el texto a mostrar. Por ahora se lo hemos puesto el id al LinearLayout de activity_login.xml.
        //Llamamos al método de la superclase que lanzará el snackbar.
        onError(R.string.userEmptyError);
    }

    @Override
    public void setPasswordEmptyError() {
        onError(R.string.passwordEmptyError);
    }

    @Override
    public void setPasswordError() {
        onError(R.string.passwordLengthError);
    }
}
