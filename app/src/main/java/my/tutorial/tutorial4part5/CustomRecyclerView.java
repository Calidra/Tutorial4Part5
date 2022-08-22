package my.tutorial.tutorial4part5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class CustomRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<StudentInfo> data = Collections.emptyList();
    StudentInfo current;
    int currentPos=0;
    String test;

    // create constructor to innitilize context and data sent from MainActivity
    public CustomRecyclerView(Context context, List<StudentInfo> data)
    {
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.rowlayout, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        StudentInfo current=data.get(position);

        myHolder.description.setText("ID: " + current.studentID + "\nName: " + current.firstName + " "
                + current.lastName + "\nBirth: " + current.yearOfBirth );
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView description;
        CheckBox checkbox;
        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            description = (TextView) itemView.findViewById(R.id.label);
            checkbox = itemView.findViewById(R.id.checkBox1);

            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkButton();
                }
            });
        }

        public void checkButton()
        {
            int position = this.getAdapterPosition();
            StudentInfo student = data.get(position);
            if(student.happy)
            {
                student.happy = false;
            }
            else
            {
                student.happy = true;
            }
        }


        @Override
        public void onClick(View v)
        {
//            int position = this.getAdapterPosition();
//            DataGroceryList grocerylist = data.get(position);
//            String shoppingID = grocerylist.getGroceryID();
//            String description = grocerylist.getDescription();
//            String date = grocerylist.getDateTime();
//
//            Intent intent = new Intent(context, HomePage.class);
//            intent.putExtra("id", shoppingID);
//            intent.putExtra("description", description);
//            intent.putExtra("date", date);
//            context.startActivity(intent);
        }

    }

}
