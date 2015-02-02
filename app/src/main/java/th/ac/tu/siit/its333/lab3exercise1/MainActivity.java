package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<String> listGrades;
    TextView tvGp, tvGPA, tvCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();
        tvGp = (TextView)findViewById(R.id.tvGP);
        tvGPA =(TextView)findViewById(R.id.tvGPA);
        tvCredit = (TextView)findViewById(R.id.tvCR);

        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
    }

    public void buttonClicked(View v) {
        //Toast t = Toast.makeText(this,"Hello",Toast.LENGTH_SHORT);
        //t.show();
        int id = v.getId();
        Intent i;
        switch (id){
            case R.id.button4:
                i = new Intent(this, CourseListActivity.class);
                startActivityForResult(i, 2);
            break;
            case R.id.button2:
                i = new Intent(this, CourseActivity.class);
                startActivityForResult(i, 1);
            break;
            case R.id.button:
                listCodes = new ArrayList<String>();
                listCredits = new ArrayList<Integer>();
                listGrades = new ArrayList<String>();
            break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Values from child activity
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //String result = data.getStringExtra("retValue");
                //tvResult.setText(result);
                listCodes.add(data.getStringExtra("courseCode"));
                listCredits.add(Integer.parseInt(data.getStringExtra("Credit")));
                listGrades.add(data.getStringExtra("Grade"));

                //Toast t = Toast.makeText(this,"Hello",Toast.LENGTH_SHORT);
                //t.show();

                tvCredit.setText(sumCredit()+"");
                tvGp.setText(sumGradePoint()+"");
                tvGPA.setText(calculateGPA()+"");
            }
            else if (resultCode == RESULT_CANCELED) {
                //tvResult.setText("CANCELED");
            }
        }
    }

    private int sumCredit(){
        int sum = 0;
        for(int i=0; i<listCredits.size();i++){
            sum+=listCredits.get(i);
        }
        return sum;
    }

    private double sumGradePoint(){
        int sum = 0;
        for(int i=0; i<listGrades.size();i++){
            switch(listGrades.get(i)){
                case "A": sum+=(4*listCredits.get(i)); break;
                case "B+" : sum+=3.5*listCredits.get(i); break;
                case "B": sum+=3*listCredits.get(i); break;
                case "C+" : sum+=2.5*listCredits.get(i); break;
                case "C": sum+=2*listCredits.get(i); break;
                case "D+" : sum+=1.5*listCredits.get(i); break;
                case "D": sum+=1*listCredits.get(i); break;
                case "F" : sum+=0*listCredits.get(i); break;

            }
        }
        return sum;
    }

    private double lookUpGradePoint(String Grade){
        double gradePoint = 0;

        switch (Grade){
            case "A":
                gradePoint = 4.0;
                break;
            case "B+":
                gradePoint = 3.5;
                break;
            case "B":
                gradePoint = 3.0;
                break;
            case "C+":
                gradePoint = 2.5;
                break;
            case "C":
                gradePoint = 2.0;
                break;
            case "D+":
                gradePoint = 1.5;
                break;
            case "D":
                gradePoint = 1.0;
                break;
            default:
                gradePoint = 0.0;
                break;

        }
        return gradePoint;
    }

    private double calculateGPA(){
        double sum1 = 0,sum2=0;
        for(int i=0; i<listCodes.size();i++){
            sum1 += listCredits.get(i)*lookUpGradePoint(listGrades.get(i));
            sum2 += listCredits.get(i);
        }
        return (sum1/sum2);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
