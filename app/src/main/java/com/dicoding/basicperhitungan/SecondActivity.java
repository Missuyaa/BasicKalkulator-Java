package com.dicoding.basicperhitungan;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ambil data yang dikirim dari MainActivity, jika ada
        int score = getIntent().getIntExtra("SCORE", 0);
        int soalTerjawab = getIntent().getIntExtra("SOAL_TERJAWAB", 0);

        // Referensi ke komponen UI untuk menampilkan hasil
        TextView SoalTerjawab = findViewById(R.id.SoalTerjawab);
        TextView Score = findViewById(R.id.ResultScore);

        // Tampilkan hasil di TextView
        SoalTerjawab.setText("Soal yang terjawab: " + soalTerjawab);
        Score.setText("Score keseluruhan: " + score);
    }
}
