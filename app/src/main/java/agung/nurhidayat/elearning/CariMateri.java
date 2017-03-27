package agung.nurhidayat.elearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CariMateri extends AppCompatActivity {
    SearchView svCariMateri;
    private ListView lvCariMateri;
    public ArrayList<String> getBab = new ArrayList<>();
    public ArrayList<Integer> getIdMateri = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_materi);
        svCariMateri = (SearchView) findViewById(R.id.sv_carimateri);
        lvCariMateri = (ListView) findViewById(R.id.lv_carimateri);
        adapter = new ArrayAdapter<String>(CariMateri.this, android.R.layout.simple_list_item_1, getBab);
        lvCariMateri.setAdapter(adapter);

        svCariMateri.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //Toast.makeText(CariMateri.this, "text submited", Toast.LENGTH_SHORT).show();
                cariMateri(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Toast.makeText(CariMateri.this, "typing ....", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        lvCariMateri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent goDet = new Intent(CariMateri.this, DetilMateri.class);
                goDet.putExtra("id", getIdMateri.get(i));
                startActivity(goDet);
            }
        });
    }

    public void cariMateri(String cari){
        HashMap pushCari = new HashMap();
        pushCari.put("bab", cari);
        PostResponseAsyncTask taskCari = new PostResponseAsyncTask(CariMateri.this, pushCari, "please wait", new AsyncResponse() {
            @Override
            public void processFinish(String s) {
               if (s.contains("bab")){
                   try {
                       JSONArray jaray = new JSONArray(s);
                       getIdMateri.clear();
                       getBab.clear();
                       for (int a = 0; a < jaray.length(); a++){
                           JSONObject jdata = jaray.getJSONObject(a);
                           getBab.add(jdata.getString("bab").toString());
                           getIdMateri.add(Integer.valueOf(jdata.getString("idMateri").toString()));
                       }
                       adapter.notifyDataSetChanged();

                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }else if (s.equals("gagalcari")){
                   Toast.makeText(CariMateri.this, "Tidak ditemukan", Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(CariMateri.this, "error koneksi", Toast.LENGTH_SHORT).show();
               }
            }
        });
        taskCari.execute("http://10.0.3.2/elearning/cari.php");
    }
}
