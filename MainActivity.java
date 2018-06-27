public class MainActivity extends AppCompatActivity {

    Button btEntrar;
    EditText etUser;
    EditText etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btEntrar = (Button) findViewById(R.id.btAcceder);
        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);


        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread tr = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        final String resultado = enviarDatosGET(etUser.getText().toString(),etPass.getText().toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int r=obtenerDatosJson(resultado);
                                if (r>0){
                                    Intent intent = new Intent(getApplicationContext(),DatosUsuario.class);
                                    intent.putExtra("cod",etUser.getText().toString());
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(),"usuario o pass incorrectos", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                };
               tr.start();
            }
        });


    }
    //1 - ENVIAMOS DATOS
    public String enviarDatosGET(String usu, String pass){
        URL url =null;
        String linea="";
        int respuesta=0;
        StringBuilder resul= null;

        try {
            url = new URL("http://192.168.43.128/webservice/conectar2.php?usu="+usu+"&pass="+pass);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            respuesta = connection.getResponseCode();
            resul = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK){
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                //LLENAMOS RESUL CON READER
                while ( (linea=reader.readLine()) !=null ){
                    resul.append(linea);
                }
            }

        } catch (Exception e){}


        return resul.toString();
    }

    //2 - METODO PARA SABER SI ENVIARDATOSGET TIENE DATOS O NO
    public int obtenerDatosJson(String respuesta){
        int res=0;
        try{
            JSONObject json = new JSONObject(respuesta);
            if (json.length()>0){
                res=1;
            }
        }catch (Exception e){}

        return res;

    }