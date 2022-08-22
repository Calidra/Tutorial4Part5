package my.tutorial.tutorial4part5;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CustomAdapter extends ArrayAdapter<StudentInfo> {
    //private final Context context;
    //private final ArrayList<String> values;

    public CustomAdapter(Context context, ArrayList<StudentInfo> v) {
        super(context, R.layout.rowlayout, v);
//        this.context = context;
//        this.values = v;
    }
    public View getView(int position, View convertView, ViewGroup parent) {


        // convertView which is recyclable view
        View currentItemView = convertView;



        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.rowlayout, parent, false);
        }
        final StudentInfo currentNumberPosition = getItem(position);
        // get the position of the view from the ArrayAdapter
        //currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        ImageView numbersImage = currentItemView.findViewById(R.id.icon);
        assert currentNumberPosition != null;
        numbersImage.setImageResource(R.drawable.image4);

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView textView1 = currentItemView.findViewById(R.id.label);
        final String toPost = currentNumberPosition.studentID + " \n" + currentNumberPosition.firstName + " \n" + currentNumberPosition.lastName + " \n" + currentNumberPosition.gender + " \n" + currentNumberPosition.yearOfBirth;
        textView1.setText(toPost);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), toPost, Toast.LENGTH_LONG).show();
            }
        });


        //get the check boxes
        CheckBox happy = currentItemView.findViewById(R.id.checkBox1);
        CheckBox completed = currentItemView.findViewById(R.id.checkBox3);

        happy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(currentNumberPosition.happy)
                {
                    currentNumberPosition.happy = false;
                }
                else
                {
                    currentNumberPosition.happy = true;
                }

            }
        });


        completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(currentNumberPosition.completed)
                {
                    currentNumberPosition.completed = false;
                }
                else
                {
                    currentNumberPosition.completed = true;
                }
            }
        });


        Button buttonClicked = currentItemView.findViewById(R.id.check_button);

        buttonClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String returnValue = "";
                if(happy.isChecked())
                {
                    returnValue = returnValue + "Happy ";
                }


                if(completed.isChecked())
                {
                    returnValue = returnValue + "Completed";
                }

                Toast.makeText(getContext(), returnValue, Toast.LENGTH_LONG).show();

            }
        });



        // then return the recyclable view
        return currentItemView;
    }
}