package agung.nurhidayat.elearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DetilMateri extends AppCompatActivity {
    private TextView tvDetMapel, tvDetBab, tvDetIsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_materi);
        tvDetMapel = (TextView) findViewById(R.id.tv_det_mapel);
        tvDetBab = (TextView) findViewById(R.id.tv_det_bab);
        tvDetIsi = (TextView) findViewById(R.id.tv_det_isi);
        int a = getIntent().getIntExtra("id",0);
        Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();
        HashMap pushDet = new HashMap();
        pushDet.put("idMateri", String.valueOf(a));
        PostResponseAsyncTask taskDet = new PostResponseAsyncTask(DetilMateri.this, pushDet, "mohon tunggu", new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                try {
                    JSONObject jdata = new JSONObject(s);
                    tvDetMapel.setText(jdata.getString("mapel").toString());
                    tvDetBab.setText(jdata.getString("bab").toString());
                    tvDetIsi.setText(jdata.getString("isiMateri").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        taskDet.execute("http://10.0.3.2/elearning/detilMateri.php");
    }
}
