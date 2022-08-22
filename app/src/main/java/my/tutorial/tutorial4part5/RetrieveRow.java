package my.tutorial.tutorial4part5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class RetrieveRow extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseManager mydManager;
    String response_server;
    ListView list;
    ArrayList<StudentInfo> studentReturn;
    Button checklist;
    TextView displayResults;

    private RecyclerView mRVShoppingList;
    private CustomRecyclerView mAdapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_row);
        displayResults = findViewById(R.id.resp3);
        toolbar  = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mydManager = new DatabaseManager(RetrieveRow.this);
        studentReturn = new ArrayList<StudentInfo>();
        studentReturn = mydManager.retrieveRows();


        // Setup and Handover data to recyclerview
        mRVShoppingList = (RecyclerView) findViewById(R.id.list);
        mAdapter = new CustomRecyclerView(RetrieveRow.this, studentReturn);
        mRVShoppingList.setAdapter(mAdapter);
        mRVShoppingList.setLayoutManager(new LinearLayoutManager(RetrieveRow.this));
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        Log.d("Item Selector", String.valueOf(item.getItemId()));
        switch (item.getItemId())
        {

            case R.id.add_row:
                startActivity(new Intent(RetrieveRow.this, AddRow.class));
                break;
            case R.id.retrieve_rows:
                startActivity(new Intent(RetrieveRow.this, RetrieveRow.class));
                break;
            case R.id.nav_main:
                startActivity(new Intent(RetrieveRow.this, MainActivity.class));
                break;
        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    public void buttonClicked2(View view)
    {
        String stringTest = "";
        for(int i = 0; i < studentReturn.size(); i++)
        {
            if(studentReturn.get(i).happy)
            {
                stringTest =  stringTest + studentReturn.get(i).studentID + "\n";
            }

        }
        displayResults.setText(stringTest);
    }
}