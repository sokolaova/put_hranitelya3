package Database;

import android.content.Context;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.put_hranitelya.R;

public class DataCollector {
    private Context context;
    private TextView textView1, textView2, textView3, textView5, textView6, textView7, textView8, textView10, textView12, textView13, textView14;
    private RadioGroup radioGroup, radioGroup_front, radiogroup_popularizatsiya;
    private EditText editTextText, editTextText2;


    public DataCollector(Context context, TextView textView1, TextView textView2, TextView textView3, TextView textView5,
                         TextView textView6, TextView textView7, TextView textView8, TextView textView10, TextView textView12,
                         TextView textView13, TextView textView14, RadioGroup radioGroup, RadioGroup radioGroup_front,
                         RadioGroup radiogroup_popularizatsiya, EditText editTextText, EditText editTextText2) {
        this.context = context;
        this.textView1 = textView1;
        this.textView2 = textView2;
        this.textView3 = textView3;
        this.textView5 = textView5;
        this.textView6 = textView6;
        this.textView7 = textView7;
        this.textView8 = textView8;
        this.textView10 = textView10;
        this.textView12 = textView12;
        this.textView13 = textView13;
        this.textView14 = textView14;
        this.radioGroup = radioGroup;
        this.radioGroup_front = radioGroup_front;
        this.radiogroup_popularizatsiya = radiogroup_popularizatsiya;
        this.editTextText = editTextText;
        this.editTextText2 = editTextText2;
    }

    public DBConnect.ResultData collectDataFromUI(long userId) {
        String col1 = textView1.getText().toString() + editTextText.getText().toString();
        String col2 = textView2.getText().toString() + editTextText2.getText().toString();;
        String col3 = textView3.getText().toString();

        String col4, col5, col6;

        int checkedId = radioGroup.getCheckedRadioButtonId();
        if (checkedId == R.id.radio_fed_znacheniye) {
            col4 = textView5.getText().toString();
        } else if (checkedId == R.id.radio_reg_znacheniye) {
            col4 = textView6.getText().toString();
        } else if (checkedId == R.id.radio_munitsip_znacheniye) {
            col4 = textView7.getText().toString();
        } else if (checkedId == R.id.radio_net) {
            col4 = textView8.getText().toString();

        } else {
            col4 = "";
        }

        int checkedId2 = radioGroup_front.getCheckedRadioButtonId();

        if (checkedId2 == R.id.radio_yes) {
            col5 = textView10.getText().toString();
        } else if (checkedId2 == R.id.radio_no) {
            col5 = "";
        } else {
            col5 = "";
        }

        int checkedId3 = radiogroup_popularizatsiya.getCheckedRadioButtonId();

        if (checkedId3 == R.id.radio_lektsiya) {
            col6 = textView12.getText().toString();
        } else if (checkedId3 == R.id.radio_informatsiya) {
            col6 = textView13.getText().toString();
        } else {
            col6 = "";
        }

        String col7 = textView14.getText().toString();

        return new DBConnect.ResultData(userId, col1, col2, col3, col4, col5, col6, col7);
    }
}

