package my.tutorial.tutorial4part5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class AddRow extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
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


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    private DatabaseManager mydManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_row);
        toolbar  = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        Log.d("Item Selector", String.valueOf(item.getItemId()));
        switch (item.getItemId())
        {

            case R.id.add_row:
                startActivity(new Intent(AddRow.this, AddRow.class));
                break;
            case R.id.retrieve_rows:
                startActivity(new Intent(AddRow.this, RetrieveRow.class));
                break;
            case R.id.nav_main:
                startActivity(new Intent(AddRow.this, MainActivity.class));
                break;
        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

}