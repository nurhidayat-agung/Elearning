package agung.nurhidayat.elearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuUtama extends AppCompatActivity {
    TextView tvMenuWelcome;
    ImageView ivMenuTambahMateri, ivMenuCariMateri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        tvMenuWelcome = (TextView) findViewById(R.id.tv_menu_welcome);
        ivMenuTambahMateri = (ImageView) findViewById(R.id.iv_menu_tambah_materi);
        ivMenuCariMateri = (ImageView) findViewById(R.id.iv_menu_cari_materi);
        Intent getData = getIntent();
        String namaguru = getData.getStringExtra("namaGuru");
        tvMenuWelcome.setText("Selamat Datang "+ namaguru);
        

    }
}
