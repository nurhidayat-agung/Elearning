package agung.nurhidayat.elearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class RegisterGuru extends AppCompatActivity {
    private EditText edtRegNamaGuru, edtRegEmailGuru, edtRegPassGuru, edtRegMapelGuru;
    Button btnRegRegGuru;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_guru);
        edtRegNamaGuru = (EditText) findViewById(R.id.edt_reg_nama_guru);
        edtRegEmailGuru = (EditText) findViewById(R.id.edt_reg_email_guru);
        edtRegPassGuru = (EditText) findViewById(R.id.edt_reg_pass_guru);
        edtRegMapelGuru = (EditText) findViewById(R.id.edt_reg_mapel_guru);
        btnRegRegGuru = (Button) findViewById(R.id.btn_reg_reg_guru);

        btnRegRegGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap pushRegGuru = new HashMap();
                pushRegGuru.put("txtNamaGuru", edtRegNamaGuru.getText().toString());
                pushRegGuru.put("txtEmailGuru", edtRegEmailGuru.getText().toString());
                pushRegGuru.put("txtPassGuru", edtRegPassGuru.getText().toString());
                pushRegGuru.put("txtMapelGuru", edtRegMapelGuru.getText().toString());

                PostResponseAsyncTask taskRegGuru = new PostResponseAsyncTask(RegisterGuru.this, pushRegGuru, "please wait", new AsyncResponse() {
                    @Override
                    public void processFinish(String result) {
                        //Toast.makeText(RegisterGuru.this, result, Toast.LENGTH_SHORT).show();
                        if (result.contains("terdaftar")){
                            edtRegNamaGuru.setText("");
                            edtRegEmailGuru.setText("");
                            edtRegPassGuru.setText("");
                            edtRegMapelGuru.setText("");
                            finish();
                        }else {
                            Toast.makeText(RegisterGuru.this, "regiter gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                try{
                    taskRegGuru.execute("http://10.0.3.2/elearning/regisGuru.php");
                }catch (Exception error){
                    Toast.makeText(RegisterGuru.this, error.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
