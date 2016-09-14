package id.sch.smktelkom_mlg.tugas01.xirpl5001.tugas001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    EditText etNama, etKelas;
    //    RadioButton lk,pr;
    RadioGroup jk;
    Spinner status, jrsn;
    String[][] ajrsn = {
            {"?"},
            {"24RPL", "24TKL"},
            {"25RPL", "25TKJ"},
            {"24RPL", "24TKJ", "25RPL", "25TKJ"}
    };
    ArrayList<String> listJurusan = new ArrayList<>();
    ArrayAdapter<String> adapter;
    CheckBox pp, pk, dd, prs, asb;
    Button bOK;
    TextView tvnama, tvkelas, tvjk, tvstatus, tvpilih, tvlmb, tvney;
    int ney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.nm);
        etKelas = (EditText) findViewById(R.id.kls);
        jk = (RadioGroup) findViewById(R.id.jk);
        status = (Spinner) findViewById(R.id.status);
        jrsn = (Spinner) findViewById(R.id.jurusan);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listJurusan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jrsn.setAdapter(adapter);

        pp = (CheckBox) findViewById(R.id.pp);
        pk = (CheckBox) findViewById(R.id.pk);
        dd = (CheckBox) findViewById(R.id.dd);
        prs = (CheckBox) findViewById(R.id.prs);
        asb = (CheckBox) findViewById(R.id.asb);

        bOK = (Button) findViewById(R.id.ok);

        tvnama = (TextView) findViewById(R.id.tvnama);
        tvkelas = (TextView) findViewById(R.id.tvkelas);
        tvjk = (TextView) findViewById(R.id.tvjk);
        tvstatus = (TextView) findViewById(R.id.tvstatus);
        tvpilih = (TextView) findViewById(R.id.tvpilih);
        tvlmb = (TextView) findViewById(R.id.tvlmb);
        tvney = (TextView) findViewById(R.id.tvney);

        pp.setOnCheckedChangeListener(this);
        pk.setOnCheckedChangeListener(this);
        dd.setOnCheckedChangeListener(this);
        prs.setOnCheckedChangeListener(this);
        asb.setOnCheckedChangeListener(this);

        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                listJurusan.clear();
                listJurusan.addAll(Arrays.asList(ajrsn[pos]));
                adapter.notifyDataSetChanged();
                jrsn.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProcess();
                doClick();
                doTekan();
                doPilih();
            }
        });
    }

    private void doProcess() {
        if (isValid()) {
            String nama = etNama.getText().toString();
            String kelas = etKelas.getText().toString();
            tvnama.setText("nama: " + nama);
            tvkelas.setText("kelas: " + kelas);
        }
    }

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();
        String kelas = etKelas.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama belum diisi");
            valid = false;
        } else if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        } else {
            etNama.setError(null);
        }

        if (kelas.isEmpty()) {
            etKelas.setError("kelas belum diisi");
            valid = false;
        } else {
            etKelas.setError(null);
        }

        return valid;
    }

    private void doClick() {
        String hjk = null;

        if (jk.getCheckedRadioButtonId() != -1) {
            RadioButton rb = (RadioButton) findViewById(jk.getCheckedRadioButtonId());
            hjk = rb.getText().toString();
        }

        if (hjk == null) {
            tvjk.setText("Jenis Kelamin     : Belum Dipilih");
        } else {
            tvjk.setText("Jenis Kelamin     : " + hjk);
        }
    }

    private void doTekan() {
        tvstatus.setText("Status:  " + status.getSelectedItem().toString());
        tvpilih.setText("Jurusan:  " + jrsn.getSelectedItem().toString());
    }

    private void doPilih() {
        String lmb = "Lomba yg dipilih: ";
        int startlen = lmb.length();
        if (pp.isChecked()) lmb += pp.getText() + " ";
        if (pk.isChecked()) lmb += pk.getText() + " ";
        if (dd.isChecked()) lmb += dd.getText() + " ";
        if (prs.isChecked()) lmb += prs.getText() + " ";
        if (asb.isChecked()) lmb += asb.getText() + " ";

        if (lmb.length() == startlen) lmb += "tidak ada pilihan";
        tvlmb.setText(lmb);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) ney += 1;
        else ney -= 1;

        tvney.setText("Lomba ( " + ney + " terpilih )");
    }
}
