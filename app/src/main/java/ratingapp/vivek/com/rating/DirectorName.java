package ratingapp.vivek.com.rating;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

/**
 * Created by Vivek Bhat on 02-01-2016.
 */
public class DirectorName extends Activity {
    private AutoCompleteTextView mDirectorName;
    private Button mFinishButton;
    static final String [] mDirectorNameData
            = new String[] {"abcd" , "efgh", "2","326","1321"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        mDirectorName = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        mFinishButton = (Button) findViewById(R.id.button1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,mDirectorNameData);

        mDirectorName.setAdapter(adapter);

        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mIntent = new Intent(DirectorName.this,MainActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("key", mDirectorName.getText().toString());

                mIntent.putExtras(mBundle);
                setResult(Activity.RESULT_OK,mIntent);
                finish();
            }
        });
    }
}
