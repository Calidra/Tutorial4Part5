package my.tutorial.tutorial4part5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddRow extends AppCompatActivity {
    Spinner spinnerGender;
    String  malefemale;
    EditText studentID;
    EditText firstname;
    EditText lastname;
    EditText yearofbirth;
    TextView response;
    String string_studentid;
    String string_firstname;
    String string_lastname;
    String string_yearofbirth;


    private DatabaseManager mydManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_row);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinnerGender= (Spinner)findViewById(R.id.spinner_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.Pizza_Array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);
        studentID = findViewById(R.id.studentid_edittext);
        firstname = findViewById(R.id.firstname_edittext);
        lastname = findViewById(R.id.lastname_edittext);
        yearofbirth = findViewById(R.id.yearofbirth_edittext);
        response = findViewById(R.id.response);
    }

    public void buttonClicked(View view) {

        mydManager = new DatabaseManager(AddRow.this);

        string_studentid = studentID.getText().toString();
        string_firstname = firstname.getText().toString();
        string_lastname = lastname.getText().toString();
        string_yearofbirth = yearofbirth.getText().toString();
        malefemale = String.valueOf(spinnerGender.getSelectedItem());
        boolean recInserted = false;
        recInserted = mydManager.addRow(Integer.parseInt(string_studentid), string_firstname, string_lastname, string_yearofbirth, malefemale);

        if (recInserted) {
            response.setText("The row in the products table is inserted.");
        }
        else {
            response.setText("Sorry, errors when inserting to DB");
        }
        //clear the form
        studentID.setText("");
        firstname.setText("");
        lastname.setText("");
        yearofbirth.setText("");
        spinnerGender.setSelection(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            startActivity(new Intent(AddRow.this, AddRow.class));
            return true;
        }

        if (id == R.id.action_retrieve) {
            startActivity(new Intent(AddRow.this, RetrieveRow.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}