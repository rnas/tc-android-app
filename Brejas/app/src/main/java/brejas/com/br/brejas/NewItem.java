package brejas.com.br.brejas;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import brejas.com.br.brejas.database.BeersDatabase;
import brejas.com.br.brejas.model.Beer;

public class NewItem extends AppCompatActivity {

    List<String> package_types = new ArrayList<String>();

    Beer editing = null;

    Spinner spType;
    EditText txName;
    EditText txBrand;
    EditText txUnits;
    EditText txContent;

    String formError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        // initalizes the elements
        spType      = (Spinner)  findViewById(R.id.sp_package_type);
        txName      = (EditText) findViewById(R.id.et_name);
        txBrand     = (EditText) findViewById(R.id.et_brand);
        txUnits     = (EditText) findViewById(R.id.et_units);
        txContent   = (EditText) findViewById(R.id.et_content);

        initItemsSpinner();
    }

    void initItemsSpinner() {

        package_types .add("Bottle");
        package_types .add("Can");
        package_types .add("Other");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, package_types);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(adapter);

    }

    void submit(View view) {

        if ( validateDataEntry() ) {

            if (editing == null) {

                // TODO : type
                Beer newItem = new Beer(txName.getText().toString(), txBrand.getText().toString(), "Bottle", Integer.parseInt(txContent.getText().toString()), Integer.parseInt(txUnits.getText().toString()));

                BeersDatabase db = new BeersDatabase(getBaseContext());

                db.addItem(newItem);

                Intent intent = new Intent();
                intent.putExtra("new_item", (Parcelable) newItem);
                setResult(RESULT_OK, intent);
                finish();

            } else {
                editing.setName(txName.getText().toString());
                editing.setBrand(txBrand.getText().toString());
                editing.setUnits(Integer.parseInt(txUnits.getText().toString()));
                editing.setContent(Integer.parseInt(txContent.getText().toString()));
            }

        } else {
            // TODO : Alert the error
            Log.i("BR", "validation error");
            Log.i("BR", formError);
        }

    }

    Boolean validateDataEntry() {
        if (txName.getText().length() == 0) {
            formError = getString(R.string.add_err_name);
            return false;
        }

        if (txBrand.getText().length() == 0) {
            formError = getString(R.string.add_err_brand);
            return false;
        }

        if (txContent.getText().length() == 0) {
            formError = getString(R.string.add_err_content);
            return false;
        }

        if (spType.getSelectedItemPosition() == -1) {
            formError = getString(R.string.add_err_pack);
            return false;
        }

        if (txUnits.getText().length() == 0) {
            formError = getString(R.string.add_err_units);
            return false;
        }

        return true;
    }
}
