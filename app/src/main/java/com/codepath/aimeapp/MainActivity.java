package com.codepath.aimeapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.FileUtils;



public class MainActivity extends AppCompatActivity {
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView LVITEMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readItems();
        //items = new ArrayList<>();

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        LVITEMS = (ListView) findViewById(R.id.LVITEMS);
        LVITEMS.setAdapter(itemsAdapter);

        // items.add("First item");
        // items.add("Second item");
        setupListViewListener();

    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.editText);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
writeItems();
        Toast.makeText(getApplicationContext(), "Un nouveau item vient d'ajouter", Toast.LENGTH_SHORT).show();
    }

    private void setupListViewListener() {
        Log.i("MainActivity", "Setting up listener On list view ");
        LVITEMS.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override

            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("MainActivity", "Item remove from the list:" + position);
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                 writeItems();
                return true;
            }

        });
    }

    private File  getDatafile(){

        return new File(getFilesDir(), "todo.text");
    }
    private void readItems() {
        try {
            items= new ArrayList <>(FileUtils.readLines(getDatafile(), Charset.defaultCharset()));
        } catch
        (IOException e){
            Log.e("MainActivity","Erreur de lire le fichier", e);
            items=new ArrayList<>();
        }
    }
    public  void writeItems(){
        try {
            FileUtils.writeLines(getDatafile(),items);
        }
        catch
        (IOException e){
            Log.e("MainActivity","Erreur de ecrire le fichier", e);
        }
    }
}


