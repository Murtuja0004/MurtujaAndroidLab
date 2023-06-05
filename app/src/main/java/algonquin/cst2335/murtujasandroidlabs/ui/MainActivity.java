package algonquin.cst2335.murtujasandroidlabs.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import algonquin.cst2335.murtujasandroidlabs.R;
import algonquin.cst2335.murtujasandroidlabs.data.MainViewModel;
import algonquin.cst2335.murtujasandroidlabs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        TextView mytext = variableBinding.textview;
        EditText myedittext = variableBinding.myedittext;
        Button mybutton = variableBinding.button;
        CheckBox mycheckbox = variableBinding.mycheckbox;
        Switch myswitch = variableBinding.myswitch;
        RadioButton myradiobutton = variableBinding.myradiobutton;
        ImageView myimageview = variableBinding.myimageview;
        ImageButton myimagebutton = variableBinding.myimagebutton;

        model = new ViewModelProvider(this).get(MainViewModel.class);

        //variableBinding.myedittext.setText(model.editString);
        variableBinding.button.setOnClickListener(click ->
                {
                    model.editString.postValue(variableBinding.myedittext.getText().toString());
                }
        );

        model.editString.observe(this, s -> {
            variableBinding.textview.setText("Your edit text has: "+ s);
        });
        model.checker.observe(this, selected -> {
            variableBinding.mycheckbox.setChecked(selected);
            variableBinding.myradiobutton.setChecked(selected);
            variableBinding.myswitch.setChecked(selected);

            String toast_message = "The value is now: " + selected;
            Toast.makeText(MainActivity.this, toast_message, Toast.LENGTH_SHORT).show();
        });

        mycheckbox.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.checker.postValue(isChecked);
        } );
        myswitch.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.checker.postValue(isChecked);
        } );
        myradiobutton.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.checker.postValue(isChecked);
        } );

        myimagebutton.setOnClickListener(v -> {
            int height = v.getHeight();
            int width = v.getWidth();

            CharSequence text = "The width = " + width + " and height = " + height;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();

        });

    }

}