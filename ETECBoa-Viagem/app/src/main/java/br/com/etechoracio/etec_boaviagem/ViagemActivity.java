package br.com.etechoracio.etec_boaviagem;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import br.com.etechoracio.etec_boaviagem.enums.TipoViagemEnum;
import br.com.etechoracio.etec_boaviagem.model.Viagem;
import br.com.etechoracio.etec_boaviagem.utils.DateTimeUtils;

public class ViagemActivity extends AppCompatActivity {

    private Button btndataChegada;
    private Button btndataSaida;
    private Button DataSaida;
    private EditText editDestino;
    private EditText editQuantidadePessoas;
    private EditText editOrcamento;
    private RadioGroup groupTipoViagem;

    public void OnSalvar(View View) {
        Viagem viagem = new Viagem();
        viagem.setDestino(editDestino.getText().toString());
        viagem.setDataChegada(DateTimeUtils.toDate(btndataChegada.getText().toString()));
        viagem.setDataSaida(DateTimeUtils.toDate(btndataSaida.getText().toString()));
        viagem.setOrcamento(Double.valueOf(editOrcamento.getText().toString()));
        viagem.setQuantidadePessoas(Integer.valueOf(editQuantidadePessoas.getText().toString()));

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Viagem");
        String id = myRef.push().getKey();
        myRef.child(id).setValue(viagem);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        viagem.setId(id);

        builder.setTitle("Sucesso");
        builder.setMessage("Registro inserido com Sucesso");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();



        int tipo =groupTipoViagem.getCheckedRadioButtonId();
        if (tipo ==R.id.radioLazer){
            viagem.setTipoViagem(TipoViagemEnum.LAZER);

        }
        showInsertMessage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagem);
        editDestino = findViewById((R.id.editDestino));
        editQuantidadePessoas = findViewById(R.id.quantidadedePessoas);
        editOrcamento = findViewById((R.id.editOrcamento));
        groupTipoViagem = findViewById(R.id.groupTipoViagem);


        btndataChegada = findViewById(R.id.btndataChegada);
        btndataSaida = findViewById(R.id.btndataSaida);
    }

    private DatePickerDialog.OnDateSetListener datachegada = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {

            btndataChegada.setText(DateTimeUtils.FormatDate(dia, mes + 1, ano));
            ;
        }
    };

    protected Dialog onCreateDialog(int id) {
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        switch (id) {
            case R.id.btndataChegada:
                return new DatePickerDialog(this, datachegada, ano, mes, dia);
            case R.id.btndataSaida:
                return new DatePickerDialog(this, datasaida, ano, mes, dia);
        }
        return null;
    }

    public void onSelecionarData(View view) {
        showDialog(view.getId());
    }


    private DatePickerDialog.OnDateSetListener datasaida = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {

            btndataSaida.setText(DateTimeUtils.FormatDate(dia, mes + 1, ano));
        }
    };

    protected Dialog onCreateDialogg(int id) {
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        switch (id) {
            case R.id.btndataSaida:
                return new DatePickerDialog(this, datasaida, ano, mes, dia);
        }
        return null;
    }

    public void onSelecionarData2(View view) {
        showDialog(view.getId());
    }

    private void showInsertMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirmação");
        builder.setMessage("inserido com sucesso");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();


            }

        });
     AlertDialog alerta = builder.create();
     alerta.show();
    }

}