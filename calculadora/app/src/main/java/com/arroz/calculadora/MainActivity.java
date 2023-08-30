package com.arroz.calculadora;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Button btn_suma;
    private Button btn_resta;
    private Button btn_division;
    private Button btn_multiplicacion;
    private Button btn_parentesis;
    private Button btn_elevacion;
    private Button btn_raiz;
    private TextView text_respuesta;

    private EditText edit_numero_uno;
    private EditText edit_numero_dos;
    private Switch mi_switch;


    private RadioButton radioButton;
private Spinner funcionalidad;
private ImageButton delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_respuesta = findViewById(R.id.respuesta);
        edit_numero_uno = findViewById(R.id.numero1);
        edit_numero_dos = findViewById(R.id.numero2);
        ImageButton imageButton = findViewById(R.id.delete);

        CheckBox checkBox = findViewById(R.id.checkBox);

        Spinner spinnerFuncionalidades = findViewById(R.id.funcionalidad);

        RadioButton radioButton = findViewById(R.id.radioButton);

        //switch
        LinearLayout botonesLayout = findViewById(R.id.botones_layout);
        Switch miSwitch = findViewById(R.id.mi_switch);
        Button btnSinh = findViewById(R.id.btn_sinh);
        Button btnCosh = findViewById(R.id.btn_cosh);
        Button btnTanh = findViewById(R.id.btn_tanh);


        ///parentesis, raiz, elevacion
        Button btnParentesis = findViewById(R.id.btn_parentesis);
        Button btnElevacion = findViewById(R.id.btn_elevacion);
        Button btnRaiz = findViewById(R.id.btn_raiz);

        //radio button, oculta el spiner y un edit text
        // Asegurándote de que el RadioButton esté sin seleccionar al inicio
        radioButton.setChecked(false);

// Asegurándote de que el Spinner y el EditText estén ocultos al inicio
        spinnerFuncionalidades.setVisibility(View.GONE);
        edit_numero_uno.setVisibility(View.VISIBLE);

// Configurando el escuchador de clics para RadioButton
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerFuncionalidades.getVisibility() == View.GONE) {
                    // Si el Spinner está visible, lo ocultamos y ocultamos el EditText
                    spinnerFuncionalidades.setVisibility(View.VISIBLE);
                    edit_numero_uno.setVisibility(View.GONE);
                } else {
                    // Si el Spinner está oculto, lo mostramos y mostramos el EditText
                    spinnerFuncionalidades.setVisibility(View.GONE);
                    edit_numero_uno.setVisibility(View.VISIBLE);
                    radioButton.setChecked(false);
                }

            }
        });


        //array que se muestra en el spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.funcionalidades_trigonometricas,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFuncionalidades.setAdapter(adapter);

        //spinner

        spinnerFuncionalidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                             @Override
                                                             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                 String opcionSeleccionada = parent.getItemAtPosition(position).toString();
                                                                 String entradaUsuario = edit_numero_dos.getText().toString();

                                                                 // Comprobando si el EditText no está vacío
                                                                 if (!entradaUsuario.isEmpty()) {
                                                                     double valorUsuario = Double.parseDouble(entradaUsuario);
                                                                     double resultados;

                                                                     switch (opcionSeleccionada) {
                                                                         case "Sin":
                                                                             resultados = Math.sin(Math.toRadians(valorUsuario));
                                                                             break;
                                                                         case "Cos":
                                                                             resultados = Math.cos(Math.toRadians(valorUsuario));
                                                                             break;
                                                                         case "Tan":
                                                                             resultados = Math.tan(Math.toRadians(valorUsuario));
                                                                             break;
                                                                         default:
                                                                             throw new IllegalStateException("Opción desconocida: " + opcionSeleccionada);
                                                                     }

                                                                     // Mostrando el resultado en el TextView
                                                                     text_respuesta.setText(String.valueOf(resultados));
                                                                 }
                                                             }


                                                             @Override
                                                             public void onNothingSelected(AdapterView<?> parent) {
                                                             }
                                                         });

        checkBox.setChecked(true);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    text_respuesta.setVisibility(View.VISIBLE);
                } else {
                    text_respuesta.setVisibility(View.GONE);
                }
            }
        });



        btn_suma = findViewById(R.id.button_suma);
        btn_suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_respuesta.setText( suma( Integer.parseInt(edit_numero_uno.getText().toString()),Integer.parseInt(edit_numero_dos.getText().toString()) )+"");
            }
        });


        btn_division = findViewById(R.id.button_division);
        btn_division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numerador = Integer.parseInt(edit_numero_uno.getText().toString());
                int denominador = Integer.parseInt(edit_numero_dos.getText().toString());
                if (denominador == 0) {
                    // Mostrar un mensaje de error
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("No se puede dividir por cero");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                } else {
                    text_respuesta.setText(division(Integer.parseInt(edit_numero_uno.getText().toString()), Integer.parseInt(edit_numero_dos.getText().toString())) + "");

                }
            }
        });

        btn_multiplicacion = findViewById(R.id.button_multiplicacion);
        btn_multiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_respuesta.setText( multiplicacion( Integer.parseInt(edit_numero_uno.getText().toString()),Integer.parseInt(edit_numero_dos.getText().toString()) )+"");

            }
        });

        btn_resta = findViewById(R.id.button_resta);
        btn_resta.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                text_respuesta.setText( resta( Integer.parseInt(edit_numero_uno.getText().toString()),Integer.parseInt(edit_numero_dos.getText().toString()) )+"");

            }
        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_numero_uno.setText("");  // Limpiar el EditText
                edit_numero_dos.setText("");  // Limpiar el EditText
                text_respuesta.setText("0");  // Establecer el TextView en 0
            }
        });


    // Configurando el listener para el Switch
miSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                // Si el Switch está activado, mostrar los botones
                botonesLayout.setVisibility(View.VISIBLE);
                edit_numero_uno.setVisibility(View.GONE);
            } else {
                // Si el Switch está desactivado, ocultar los botones
                botonesLayout.setVisibility(View.GONE);
                edit_numero_uno.setVisibility(View.VISIBLE);
            }
        }
    });

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            String entradaUsuario = edit_numero_uno.getText().toString();

            // Comprobando si el EditText no está vacío
            if (!entradaUsuario.isEmpty()) {
                double valorUsuario = Double.parseDouble(entradaUsuario);
                double resultado;
                String respuesta;

                int id = v.getId();
                if (id == R.id.btn_sinh) {
                    respuesta = String.valueOf(Math.sinh(valorUsuario));
                } else if (id == R.id.btn_cosh) {
                    respuesta = String.valueOf(Math.cosh(valorUsuario));
                } else if (id == R.id.btn_tanh) {
                    respuesta = String.valueOf(Math.tanh(valorUsuario));
                }else if (v.getId() == R.id.btn_parentesis) {
                    // Añadiendo paréntesis alrededor del valor del usuario
                    respuesta = "(" + entradaUsuario + ")";
                } else if (v.getId() == R.id.btn_elevacion) {
                    // Elevando el valor del usuario a la potencia x
                    resultado = Math.pow(valorUsuario, valorUsuario); // sustituye "x" por el valor deseado
                    respuesta = String.valueOf(resultado);
                } else if (v.getId() == R.id.btn_raiz) {
                    // Calculando la raíz cuadrada del valor del usuario
                    resultado = Math.sqrt(valorUsuario);
                    respuesta = String.valueOf(resultado);
                } else {
                    throw new IllegalStateException("Botón desconocido.");
                }

                // Mostrando el resultado en el TextView
                text_respuesta.setText(String.valueOf(respuesta));
            }
        }
    };

        btnSinh.setOnClickListener(buttonClickListener);
        btnCosh.setOnClickListener(buttonClickListener);
        btnTanh.setOnClickListener(buttonClickListener);

        btnParentesis.setOnClickListener(buttonClickListener);
        btnElevacion.setOnClickListener(buttonClickListener);
        btnRaiz.setOnClickListener(buttonClickListener);
    }


    public double suma (int a, int b){
        return a+b;
    }

    public double resta (int a, int b){
        return a-b;
    }

    public double multiplicacion (int a, int b){
        return a*b;
    }

    public double division (int a, int b){
        int respuesta = 0;

        if (b!=0){
            respuesta=a/b;
        }

        return respuesta;
    }
}