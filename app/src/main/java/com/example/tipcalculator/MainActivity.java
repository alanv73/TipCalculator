package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    double dblBillAmount;
    double dblOtherTip;

    String strBillAmount;
    String strOtherTip;

    EditText etBillAmount;
    EditText etOtherTip;
    TextView tvTipTotal;
    TextView tvPercent;

    DecimalFormat currency = new DecimalFormat("$###,###.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBillAmount = (EditText) findViewById(R.id.editTextBillAmount);
        etOtherTip = (EditText) findViewById(R.id.editTextOtherTip);
        tvTipTotal = (TextView) findViewById(R.id.textViewTipTotal);
        tvPercent = (TextView) findViewById(R.id.textViewPercent);

//        etOtherTip.setEnabled(false);
        etOtherTip.setVisibility(View.INVISIBLE);
        tvPercent.setVisibility(View.INVISIBLE);

        etBillAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                RadioGroup rgTip = (RadioGroup) findViewById(R.id.radioGroup);

                int selected = rgTip.getCheckedRadioButtonId();

                if (selected != -1) {
                    RadioButton rb = (RadioButton) findViewById(rgTip.getCheckedRadioButtonId());
                    rb.callOnClick();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etOtherTip.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                recalcOther();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    protected void onRestart() {

        super.onRestart();
    }

    public void onRadioGroup1Click(View v) {

        strBillAmount = etBillAmount.getText().toString();

        if (!TextUtils.isEmpty(strBillAmount)) {

            dblBillAmount = Double.parseDouble(strBillAmount);

            switch (v.getId()) {

                case R.id.radioTen:
//                    etOtherTip.setEnabled(false);
                    etOtherTip.setVisibility(View.INVISIBLE);
                    tvPercent.setVisibility(View.INVISIBLE);
                    tvTipTotal.setText(String.format("Tip: %s\n\nTotal Bill: %s",
                            currency.format(dblBillAmount * 0.1), currency.format(dblBillAmount * 1.1)));
                    break;

                case R.id.radioFifteen:
//                    etOtherTip.setEnabled(false);
                    etOtherTip.setVisibility(View.INVISIBLE);
                    tvPercent.setVisibility(View.INVISIBLE);
                    tvTipTotal.setText(String.format("Tip: %s\n\nTotal Bill: %s",
                            currency.format(dblBillAmount * 0.15), currency.format(dblBillAmount * 1.15)));
                    break;

                case R.id.radioTwenty:
//                    etOtherTip.setEnabled(false);
                    etOtherTip.setVisibility(View.INVISIBLE);
                    tvPercent.setVisibility(View.INVISIBLE);
                    tvTipTotal.setText(String.format("Tip: %s\n\nTotal Bill: %s",
                            currency.format(dblBillAmount * 0.2), currency.format(dblBillAmount * 1.2)));
                    break;

                case R.id.radioOther:
//                    etOtherTip.setEnabled(true);
                    etOtherTip.setVisibility(View.VISIBLE);
                    tvPercent.setVisibility(View.VISIBLE);

                    if(TextUtils.isEmpty(strOtherTip)) {
                        etOtherTip.requestFocus();
                        showSoftKeyboard(etOtherTip);
                    }

                    recalcOther();
                    break;
            } // end switch

        } // end if
        else {

            Toast.makeText(getApplicationContext(), "Please enter bill amount", Toast.LENGTH_SHORT).show();
            RadioGroup rgTip = (RadioGroup) findViewById(R.id.radioGroup);
            RadioButton rb = (RadioButton) findViewById(rgTip.getCheckedRadioButtonId());
            rgTip.clearCheck();

        } // end else

    } // end onRadioGroupClick

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void recalcOther(){

        strOtherTip = etOtherTip.getText().toString();
        if(!TextUtils.isEmpty(strOtherTip)) {
            dblOtherTip = Double.parseDouble(strOtherTip);
            tvTipTotal.setText(String.format("Tip: %s\n\nTotal Bill: %s",
                    currency.format(dblBillAmount * dblOtherTip / 100),
                    currency.format(dblBillAmount * (1 + (dblOtherTip / 100)))));
        }
        else {
            tvTipTotal.setText("");
        }

    }

}
