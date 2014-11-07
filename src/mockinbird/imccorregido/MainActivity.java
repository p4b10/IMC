package mockinbird.imccorregido;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String LOGTAG="MainActivity";
	
	private IndiceMasaCorporal imc;
	EditText editPeso;
	EditText editAltura;
	TextView textViewResultado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Establecer identificadores
		editPeso = (EditText) findViewById(R.id.editPeso);
		editAltura = (EditText) findViewById(R.id.editAltura);
		textViewResultado = (TextView) findViewById(R.id.textViewResultado);
		editAltura.setBackgroundColor(Color.rgb(240, 240, 250));
		editPeso.setBackgroundColor(Color.rgb(240, 240, 250));
	}

	
	public void onFocusText1(View v){
		
		editAltura.setBackgroundColor(Color.rgb(240, 240, 250));

	}
	
	public void onFocusText2(View v){
		
		editPeso.setBackgroundColor(Color.rgb(240, 240, 250));
	
	}
	/**
	 * onButtonClickCalcularIMC
	 */
	public void onButtonClickCalcularIMC(View v) {

		String peso;
		String altura;
		String resultado;
		try {
			editAltura.setBackgroundColor(Color.rgb(240, 240, 250));
			editPeso.setBackgroundColor(Color.rgb(240, 240, 250));
			peso = editPeso.getText().toString();
			altura = editAltura.getText().toString();

			imc = new IndiceMasaCorporal(peso, altura);
			
			resultado = "Valor IMC = " + imc.valorIndiceMasaCorporal() + " - ";
			resultado = resultado.concat(imc.clasificacionOMS());
			textViewResultado.setText(resultado);
			textViewResultado.setTextColor(Color.BLUE);
			Log.e(LOGTAG, imc.toString());
			
		} catch (IndiceMasaCorporalException e) {
			if (e.isErrorPeso()) {
				editPeso.setBackgroundColor(Color.rgb(230, 60, 75));
				String texto = "El peso debe de ser un n�mero entre 10 y 300";
				Toast.makeText(getApplicationContext(), texto, 
						Toast.LENGTH_SHORT).show();
				}
			if (e.isErrorAltura()){
				editAltura.setBackgroundColor(Color.rgb(230, 60, 75));}
			String texto = "Error entrada de datos";
			Toast.makeText(getApplicationContext(), texto, 
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Log.e(LOGTAG, e.getMessage());
		}
	}
	
	
	public void onClickButtonAcercaDe(View v) {
		try {
		Intent i = new Intent(this, AcercaDe.class);
		startActivity(i);
		} catch(Exception e) {
			Log.e(LOGTAG, e.getMessage());
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
