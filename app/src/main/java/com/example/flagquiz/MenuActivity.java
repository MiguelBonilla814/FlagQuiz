package com.example.flagquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MenuActivity extends AppCompatActivity implements DialogFragmentElegir.SingleChoiseListener{

    private Toolbar toolbar_menu;
    private ImageButton button_volver_menu;
    private ListView listView_items;
    private String numero_botones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        toolbar_menu = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar_menu);
        button_volver_menu = toolbar_menu.findViewById(R.id.imageButton_volver);
        listView_items = findViewById(R.id.listView);

        HashMap<String, String> data = new HashMap<>();
        data.put("Number of Choices", "Display 2, 4, 6 or 8 guess buttons");
        data.put("Regions","Regions of include in the quiz");
        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_item,
                new String[] {"First Line", "Second Line"},
                new int[]{R.id.text1, R.id.text2});
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()){
            HashMap<String, String> resultadoData = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultadoData.put("First Line",pair.getKey().toString());
            resultadoData.put("Second Line", pair.getValue().toString());
            listItems.add(resultadoData);
        }

        listView_items.setAdapter(adapter);

        button_volver_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        listView_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), listItems.get(position).toString(), Toast.LENGTH_SHORT).show();
                if (listItems.get(position).toString().equals("{Second Line=Regions of include in the quiz, First Line=Regions}"))
                {
                    DialogFragment choiceDialog = new DialogFragmentElegir();
                    choiceDialog.setCancelable(false);
                    choiceDialog.show(getSupportFragmentManager(), "Single Choose Dialog");
                }
                else {
                    CheckBoxRegions
                }

            }
        });
    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position){
        numero_botones = list[position];
        Toast.makeText(this, numero_botones.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNegativeButtonClicked(){
        numero_botones = "0";
    }


    public class CheckBoxRegions implements DialogFragmentRegions.onMultiChoiceListener{
        @Override
        public void onPositiveButtonClicked(String[] list, ArrayList<String> selectedItemsList){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Regions");
            for (String srt : selectedItemsList){
                stringBuilder.append(srt + " ");
            }
            Toast.makeText(getApplicationContext(), stringBuilder.toString(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNegativeButtonClicked(){
            Toast.makeText(getApplicationContext(), "Nulo", Toast.LENGTH_LONG).show();
        }
    }
}