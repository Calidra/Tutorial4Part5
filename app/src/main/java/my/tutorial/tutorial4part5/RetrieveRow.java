package my.tutorial.tutorial4part5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RetrieveRow extends AppCompatActivity {

    private DatabaseManager mydManager;
    String response_server;
    ListView list;
    ArrayList<StudentInfo> studentReturn;
    Button checklist;
    TextView displayResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_row);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mydManager = new DatabaseManager(RetrieveRow.this);
        studentReturn = new ArrayList<StudentInfo>();
        studentReturn = mydManager.retrieveRows();
        list =  findViewById(R.id.listView);
        displayResults = findViewById(R.id.resp3);
        ArrayAdapter<StudentInfo> adapter = new ArrayAdapter<StudentInfo>(this, android.R.layout.simple_list_item_1, android.R.id.text1, studentReturn);
        list.setAdapter(adapter);

        // use your custom layout
        CustomAdapter adapter2 = new CustomAdapter(this, studentReturn);
        list= (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter2);



//
//        list.setOnItemClickListener((parent, v, position, id) -> {
//            //uncomment line 40 to highlight item choosed when CHOICE_MODE_MULTIPLE is on
//            //       v.setBackgroundResource(R.drawable.ic_launcher_background);
//            String item = (String) list.getAdapter().getItem(position);
//            Toast.makeText(getApplicationContext(), item + " selected", Toast.LENGTH_LONG).show();
//        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            startActivity(new Intent(RetrieveRow.this, AddRow.class));
            return true;
        }

        if (id == R.id.action_retrieve) {
            startActivity(new Intent(RetrieveRow.this, RetrieveRow.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void buttonClicked2(View view)
    {
        String stringTest = "";
        for(int i = 0; i < studentReturn.size(); i++)
        {
            stringTest =  stringTest + studentReturn.get(i).studentID + ": Happy: " + studentReturn.get(i).happy + ": Ready: " + studentReturn.get(i).ready + ": Completed: " + studentReturn.get(i).completed +"\n";
        }
        displayResults.setText(stringTest);
    }
}