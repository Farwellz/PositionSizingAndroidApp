package fr.farwellz.rrcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText yourMaxCapital;
    EditText yourRiskPercent;
    EditText yourStopLossPrice;
    EditText leverageInputEdit;
    EditText yourEntryPrice;
    TextView theResult;
    Button longTrade;
    Button shortTrade;

    float stopped, risk, maxCapital, resulting, entry, losePercent, riskEqual, leverageUsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yourMaxCapital = findViewById(R.id.maxCap);
        yourRiskPercent = findViewById(R.id.riskPercent);
        leverageInputEdit = findViewById(R.id.leverageInput);
        yourStopLossPrice = findViewById(R.id.stopLossPrice);
        yourEntryPrice = findViewById(R.id.entryPrice);
        theResult = findViewById(R.id.result);
        longTrade = findViewById(R.id.longTrade);
        shortTrade = findViewById(R.id.shortTrade);

        longTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strYourMaxCapital = yourMaxCapital.getText().toString();
                String strYourRiskPercent = yourRiskPercent.getText().toString();
                String strLeverageInputEdit = leverageInputEdit.getText().toString();
                String strYourStopLossPrice = yourStopLossPrice.getText().toString();
                String strYourEntryPrice = yourEntryPrice.getText().toString();

                if (TextUtils.isEmpty(strYourMaxCapital)) {
                    yourMaxCapital.setError("This field can not be empty!");
                    return;
                }
                if (TextUtils.isEmpty(strYourRiskPercent)) {
                    yourRiskPercent.setError("This field can not be empty!");
                    return;
                }
                if (TextUtils.isEmpty(strYourStopLossPrice)) {
                    yourStopLossPrice.setError("This field can not be empty!");
                    return;
                }
                if (TextUtils.isEmpty(strYourEntryPrice)) {
                    yourEntryPrice.setError("This field can not be empty!");
                    return;
                }
                if (TextUtils.isEmpty(strLeverageInputEdit)) {
                    leverageInputEdit.setText("1");
                }

                maxCapital = Float.parseFloat(yourMaxCapital.getText().toString());
                risk = Float.parseFloat(yourRiskPercent.getText().toString());
                stopped = Float.parseFloat(yourStopLossPrice.getText().toString());
                entry = Float.parseFloat(yourEntryPrice.getText().toString());
                leverageUsed = Float.parseFloat(leverageInputEdit.getText().toString());

                if (risk > 100) {
                    yourRiskPercent.setError("It can not be more than 100%! And shouldn't be 100% either!");
                    return;
                }

                losePercent = (1 - (stopped / entry)) * leverageUsed;
                riskEqual = (risk * maxCapital) / 100;
                resulting = riskEqual / losePercent;

                float results = (float) Math.round(resulting * 100) / 100;

                if (results < 0) {
                    theResult.setText("You should go short, not long. risk=");
                }
                if (results >= maxCapital) {
                    theResult.setText("With this stop loss, if your risk is " + Float.toString(riskEqual) + "$, then you can put all your capital into it.");
                }
                if (results > 0 && results < maxCapital) {
                    theResult.setText("With this stop loss, if your risk is " + Float.toString(riskEqual) + "$, then your position should be of : " + Float.toString(results) + "$");
                }
            }
        });

        shortTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strYourMaxCapital = yourMaxCapital.getText().toString();
                String strYourRiskPercent = yourRiskPercent.getText().toString();
                String strLeverageInputEdit = leverageInputEdit.getText().toString();
                String strYourStopLossPrice = yourStopLossPrice.getText().toString();
                String strYourEntryPrice = yourEntryPrice.getText().toString();

                if (TextUtils.isEmpty(strYourMaxCapital)) {
                    yourMaxCapital.setError("This field can not be empty!");
                    return;
                }
                if (TextUtils.isEmpty(strYourRiskPercent)) {
                    yourRiskPercent.setError("This field can not be empty!");
                    return;
                }
                if (TextUtils.isEmpty(strYourStopLossPrice)) {
                    yourStopLossPrice.setError("This field can not be empty!");
                    return;
                }
                if (TextUtils.isEmpty(strYourEntryPrice)) {
                    yourEntryPrice.setError("This field can not be empty!");
                    return;
                }
                if (TextUtils.isEmpty(strLeverageInputEdit)) {
                    leverageInputEdit.setText("1");
                }

                maxCapital = Float.parseFloat(yourMaxCapital.getText().toString());
                risk = Float.parseFloat(yourRiskPercent.getText().toString());
                stopped = Float.parseFloat(yourStopLossPrice.getText().toString());
                entry = Float.parseFloat(yourEntryPrice.getText().toString());
                leverageUsed = Float.parseFloat(leverageInputEdit.getText().toString());

                if (risk > 100) {
                    yourRiskPercent.setError("It can not be more than 100%! And shouldn't be 100% either!");
                    return;
                }

                losePercent = (1 - (entry / stopped)) * leverageUsed;
                riskEqual = (risk * maxCapital) / 100;
                resulting = riskEqual / losePercent;

                float results = (float) Math.round(resulting * 100) / 100;

                if (results < 0) {
                    theResult.setText("You should go long, not short.");
                }
                if (results >= maxCapital && results > 0) {
                    theResult.setText("With this stop loss, if your risk is " + Float.toString(riskEqual) + "$, then you can put all your capital into it.");
                }
                if (results > 0 && results < maxCapital) {
                    theResult.setText("With this stop loss, if your risk is " + Float.toString(riskEqual) + "$, then your position should be of : " + Float.toString(results) + "$");
                }
            }

        });
    }
}