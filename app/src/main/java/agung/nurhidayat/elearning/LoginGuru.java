package agung.nurhidayat.elearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginGuru extends AppCompatActivity {
    private EditText edtLoginUser,edtLoginPass;
    private Button btnLoginLogin, btnLoginRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_guru);
        edtLoginUser = (EditText) findViewById(R.id.edt_login_username);
        edtLoginPass = (EditText) findViewById(R.id.edt_login_pass);
        btnLoginLogin = (Button) findViewById(R.id.btn_login_login);
        btnLoginRegister = (Button) findViewById(R.id.btn_login_register);

        btnLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goReg = new Intent(LoginGuru.this, RegisterGuru.class);
                startActivity(goReg);
            }
        });

        btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // action when clicked button
                HashMap pushLogin = new HashMap();
                pushLogin.put("txtUsername", edtLoginUser.getText().toString());
                pushLogin.put("txtPassword", edtLoginPass.getText().toString());
                PostResponseAsyncTask taskLogin = new PostResponseAsyncTask(LoginGuru.this, pushLogin, "mohon tunggu", new AsyncResponse() {
                    @Override
                    public void processFinish(String responServer) {
                        if (responServer.contains("namaGuru")){
                            try {
                                JSONObject jLogin = new JSONObject(responServer);
                                String namaGuru = jLogin.getString("namaGuru");
                                Intent goMenu = new Intent(LoginGuru.this, MenuUtama.class);
                                goMenu.putExtra("namaGuru", namaGuru);
                                startActivity(goMenu);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else if (responServer.equals("login failed")){
                            Toast.makeText(LoginGuru.this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginGuru.this, "error connection", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                taskLogin.execute("http://10.0.3.2/elearning/login.php");

            }
        });

    }
}
