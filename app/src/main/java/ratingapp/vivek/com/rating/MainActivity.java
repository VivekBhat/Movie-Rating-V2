package ratingapp.vivek.com.rating;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity implements RatingBar.OnRatingBarChangeListener {

    private EditText movieName;
    private EditText userReview;
    private EditText releaseYear;
    private EditText movieDuration;
    private EditText movieStarring;
    private EditText movieDirector;
    private RatingBar yourRating;
    private Button mSaveButton;
    private Spinner mGenres;
    private ArrayAdapter<String> mAdapter;
    private String [] mGenresData = {"-Select Genre-","Action","Adventure" ,"Animation" ,
            "Biography","Comedy","Crime" };
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*initialize variables*/
        initialize();
    }

    private void initialize()
    {
        movieName = (EditText) findViewById(R.id.editText2);
        userReview= (EditText) findViewById(R.id.editText1);
        releaseYear = (EditText) findViewById(R.id.editText3);
        movieDuration = (EditText) findViewById(R.id.editText4);
        movieStarring = (EditText) findViewById(R.id.editText5);
        movieDirector = (EditText) findViewById(R.id.editText6);
        yourRating = (RatingBar)findViewById(R.id.RatingBar1);
        mSaveButton = (Button) findViewById(R.id.button1);
        yourRating.setOnRatingBarChangeListener(this);

        mSaveButton.setOnClickListener(new onButtonClick());

        mGenres = (Spinner) findViewById(R.id.spinner1);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,mGenresData);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenres.setAdapter(mAdapter);

        movieDirector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mAutoCompleteIntent = new Intent(MainActivity.this,DirectorName.class);
                startActivityForResult(mAutoCompleteIntent,0123456);
            }
        });
    }

    public class onButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View arg0) {
            /* obtain values filled by the user in String Variables */
            String str1 = releaseYear.getText().toString();
            String str2 = movieDuration.getText().toString();
            String str3 = movieStarring.getText().toString();
            String str4 = movieDirector.getText().toString();
            String str5 = movieName.getText().toString();
            String str  = userReview.getText().toString();
            if (mGenres.getSelectedItem().toString().equalsIgnoreCase("-Select Genre-")){
                Toast.makeText(MainActivity.this,"Please Select Valid Genre",Toast.LENGTH_SHORT).show();
                return;
                }

            if (    str.equalsIgnoreCase("")||
                    str1.equalsIgnoreCase("")||
                    str2.equalsIgnoreCase("")||
                    str3.equalsIgnoreCase("")||
                    str4.equalsIgnoreCase("")||
                    str5.equalsIgnoreCase(""))
            {
                Toast.makeText(MainActivity.this," Please fill all the fields"
                        ,Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this," Validation Successful",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if ((requestCode == 0123456) && (resultCode == Activity.RESULT_OK)) {
                Bundle myResult = data.getExtras();
                String vResults = myResult.getString("key");
                movieDirector.setText(vResults);
            }
        }
        catch(Exception e)
            {
                movieDirector.setText("Oops" + requestCode + " " + resultCode);
            }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
        yourRating.setRating(arg1);
    }
}
