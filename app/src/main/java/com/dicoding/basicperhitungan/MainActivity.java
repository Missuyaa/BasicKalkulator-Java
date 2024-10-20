package com.dicoding.basicperhitungan;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText angkaPertama, angkaKedua, jawabanUser;
    Spinner operasi;
    Button lanjutButton, stopButton;
    TextView scoreLabel, livesLabel;

    private int score = 0;
    private int lives = 3;
    private int soalTerjawab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referensi ke komponen UI
        angkaPertama = findViewById(R.id.Number1);
        angkaKedua = findViewById(R.id.Number2);
        jawabanUser = findViewById(R.id.Number3);
        operasi = findViewById(R.id.spinner);
        lanjutButton = findViewById(R.id.ButtonLanjut);
        stopButton = findViewById(R.id.ButtonSelesai);
        scoreLabel = findViewById(R.id.Score);
        livesLabel = findViewById(R.id.Nyawa);

        // Logika untuk tombol Lanjut
        lanjutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int angka1 = Integer.parseInt(angkaPertama.getText().toString());
                    int angka2 = Integer.parseInt(angkaKedua.getText().toString());
                    int jawaban = Integer.parseInt(jawabanUser.getText().toString());
                    String operasiTerpilih = operasi.getSelectedItem().toString();

                    int hasil = 0;

                    // Operasi matematika berdasarkan pilihan user
                    switch (operasiTerpilih) {
                        case "Tambah":
                            hasil = angka1 + angka2;
                            break;
                        case "Kurang":
                            hasil = angka1 - angka2;
                            break;
                        case "Kali":
                            hasil = angka1 * angka2;
                            break;
                        case "Bagi":
                            hasil = angka1 / angka2;
                            break;
                    }

                    // Validasi jawaban
                    if (hasil == jawaban) {
                        score += 10;
                        soalTerjawab++;
                        scoreLabel.setText("Score yang didapat: " + score);
                        Toast.makeText(MainActivity.this, "Jawaban benar! Skor bertambah 10.", Toast.LENGTH_SHORT).show();
                    } else {
                        lives--;
                        livesLabel.setText("Nyawa yang tersisa: " + lives);
                        Toast.makeText(MainActivity.this, "Jawaban salah! Nyawa berkurang.", Toast.LENGTH_SHORT).show();

                        if (lives == 0) {
                            Toast.makeText(MainActivity.this, "Game Over! Skor akhir: " + score, Toast.LENGTH_LONG).show();
                            lanjutButton.setEnabled(false);  // Disable button Lanjut jika nyawa habis
                        }
                    }

                } catch (NumberFormatException ex) {
                    Toast.makeText(MainActivity.this, "Harap masukkan angka yang valid.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Logika untuk tombol Stop
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Memulai SecondActivity dengan score: " + score);
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("SCORE", score);  // Kirim data score
                intent.putExtra("SOAL_TERJAWAB", soalTerjawab);
                startActivity(intent);  // Memulai activity kedua
                finish();
            }
        });
    }
}

