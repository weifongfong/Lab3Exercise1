package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class CourseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course, menu);
        return true;
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

    public void buttonClicked(View v) {
        //Toast t = Toast.makeText(this,"Hello",Toast.LENGTH_SHORT);
        //t.show();
        TextView tvCC = (TextView)findViewById(R.id.etCode);
        TextView tvCR = (TextView)findViewById(R.id.etCR);
        RadioGroup rgG = (RadioGroup)findViewById(R.id.rg1);
        RadioButton rbG = (RadioButton) findViewById(rgG.getCheckedRadioButtonId());
        String Grade = rbG.getText().toString();

        Intent res = new Intent();
        res.putExtra("courseCode", tvCC.getText().toString());
        res.putExtra("Credit", tvCR.getText().toString());
        res.putExtra("Grade", Grade);
        setResult(RESULT_OK, res);
        finish();

    }
}
