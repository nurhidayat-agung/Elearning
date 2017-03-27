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

public class TambahMateri extends AppCompatActivity {
    private EditText edtTambahMapel, edtTambahBab, edtTambahIsi;
    private Button btnTambahInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_materi);
        edtTambahBab = (EditText) findViewById(R.id.edt_tambah_bab);
        edtTambahIsi = (EditText) findViewById(R.id.edt_tambah_isi);
        edtTambahMapel = (EditText) findViewById(R.id.edt_tambah_mapel);
        btnTambahInput = (Button) findViewById(R.id.btn_tambah_input);

        btnTambahInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap pushAdd = new HashMap();
                pushAdd.put("mapel", edtTambahMapel.getText().toString());
                pushAdd.put("bab", edtTambahBab.getText().toString());
                pushAdd.put("isiMateri", edtTambahIsi.getText().toString());
                PostResponseAsyncTask taskAddMateri = new PostResponseAsyncTask(TambahMateri.this, pushAdd, "mohon tunggu", new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        if (s.contains("sukses")){
                            Toast.makeText(TambahMateri.this, "Berhasil", Toast.LENGTH_SHORT).show();
                            edtTambahMapel.setText("");
                            edtTambahIsi.setText("");
                            edtTambahBab.setText("");
                        }else {
                            Toast.makeText(TambahMateri.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                taskAddMateri.execute("http://10.0.3.2/elearning/tambahMateri.php");
            }
        });
    }
}
