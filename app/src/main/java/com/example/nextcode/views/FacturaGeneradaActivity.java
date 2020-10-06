package com.example.nextcode.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nextcode.R;
import com.example.nextcode.models.Facturas;
import com.example.nextcode.nextcodeApi.ApiService;
import com.example.nextcode.nextcodeApi.RetrofitClient;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacturaGeneradaActivity extends AppCompatActivity {

    Calendar c = Calendar.getInstance();
    String fecha = Integer.toString(c.get(Calendar.YEAR)) + "-" + Integer.toString(c.get(Calendar.MONTH)+1)
                   + "-" + Integer.toString(c.get(Calendar.DATE));

    private Context context = this;

    double subtotalA =  IngresoMenuActivity.getSubtotal();
    double ivaA = IngresoMenuActivity.getSubtotal()*0.12;
    double totalA = subtotalA+ivaA;

    private static final String TAG = "NextCode";

    private String serie = "017-007";
    private int secuencial = 47;
    private String fecha_emision = fecha;
    private String subtotal = String.valueOf(subtotalA);
    private String iva = String.valueOf(ivaA);
    private String total = String.valueOf(totalA);
    private String subtotalB = String.format("%.2f",subtotalA);
    private String ivaB = String.format("%.2f",ivaA);
    private String totalB = String.format("%.2f",totalA);
    private String estado_pago = "pagado";
    private int usuario_id = MainActivity.getUsuario_id();
    private String status = "A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura_generada);

        registrarFactura();
    }

    //Realiza el procedimiento Post para Factura y verifica que se ingrese los campos para el registro.
    public void registrarFactura(){

        ApiService service = RetrofitClient.getRetrofit().create(ApiService.class);

        if(!TextUtils.isEmpty(serie) && secuencial!=0 && !TextUtils.isEmpty(fecha_emision)
                && !TextUtils.isEmpty(subtotal) && !TextUtils.isEmpty(iva) && !TextUtils.isEmpty(total)
                && !TextUtils.isEmpty(estado_pago) && usuario_id!=0 && !TextUtils.isEmpty(status)) {

            status = "A";
            service.registrarFactura(serie, secuencial, fecha_emision,
                    subtotal, iva, total,
                    estado_pago, usuario_id, status)
                    .enqueue(new Callback<Facturas>() {

                        @Override
                        public void onResponse(Call<Facturas> call, Response<Facturas> response) {

                            if(response.isSuccessful()) {
                                Log.i(TAG, "Usuario Registrado." + response.body().toString());
                                mostrarFacturaGenerada();
                                Toast.makeText(context, "Se registró la Factura Exitosamente." , Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Facturas> call, Throwable t) {
                            Log.e(TAG, "No se pudo registrar la factura.");
                        }
                    });
        }else{
            Toast.makeText(this, "No tiene Planes a Facturar" , Toast.LENGTH_SHORT).show();
        }

    }

    public void mostrarFacturaGenerada(){

        TextView textViewSerieF;
        TextView textViewFechaEmisionF;
        TextView textViewSubtotalF;
        TextView textViewIvaF;
        TextView textViewTotalF;
        TextView textViewEstadoPagoF;

        textViewSerieF =(TextView) findViewById(R.id.textViewSerieF);
        textViewFechaEmisionF =(TextView) findViewById(R.id.textViewFechaEmisionF);
        textViewSubtotalF =(TextView) findViewById(R.id.textViewSubtotalF);
        textViewIvaF =(TextView) findViewById(R.id.textViewIvaF);
        textViewTotalF =(TextView) findViewById(R.id.textViewTotalF);
        textViewEstadoPagoF =(TextView) findViewById(R.id.textViewEstadoPagoF);

        textViewSerieF.setText(serie);
        textViewFechaEmisionF.setText(fecha_emision);
        textViewSubtotalF.setText(subtotalB);
        textViewIvaF.setText(ivaB);
        textViewTotalF.setText(totalB);
        textViewEstadoPagoF.setText(estado_pago);

    }

}
