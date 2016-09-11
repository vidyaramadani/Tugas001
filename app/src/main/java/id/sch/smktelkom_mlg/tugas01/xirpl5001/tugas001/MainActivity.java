package id.sch.smktelkom_mlg.tugas01.xirpl5001.tugas001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    EditText etNama;
    EditText etKelas;
    //    RadioButton lk,pr;
    RadioGroup jk;
    Spinner status;
    CheckBox pp, pk, dd, prs, asb;
    Button bOK;
    String tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.nm);
        etKelas = (EditText) findViewById(R.id.kls);
       /* lk = (RadioButton) findViewById(R.id.lk);
        pr = (RadioButton) findViewById(R.id.pr); */
        status = (Spinner) findViewById(R.id.jk);
        pp = (CheckBox) findViewById(R.id.pp);
        pk = (CheckBox) findViewById(R.id.pk);
        dd = (CheckBox) findViewById(R.id.dd);
        prs = (CheckBox) findViewById(R.id.prs);
        asb = (CheckBox) findViewById(R.id.asb);
        bOK = (Button) findViewById(R.id.ok);


        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doProcess();
            }
        });
    }

    private void doProcess() {
        if (isValid()) {
            String nama = etNama.getText().toString();
            String kelas = etKelas.getText().toString();
            tvHasil = ("Terimakasih " + nama + " kelas " + kelas + " telah berpartisipasi");
        }
    }

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();
        String kelas = etKelas.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("nama belum diisi");
            valid = false;
        } else {
            etNama.setError(null);
        }

        if (kelas.isEmpty()) {
            etKelas.setError("Kelas belum diisi");
            valid = false;
        } else {
            etKelas.setError(null);
        }
        return valid;
    }
}
