package com.example.mealer24.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer24.model.Cuisinier;
import com.example.mealer24.R;
import com.example.mealer24.model.Repas;
import com.example.mealer24.Utilities.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* This is the screen for a cuisinier account where
* they can make changes to the status of a repas to remove it
* from the "list" of repas du jour and see all their current list of repas du jour.
**/

public class AddRepasActivity extends AppCompatActivity {
    private DatabaseReference db;
    private String userEmail;
    private TextView addRepasText;
    private Button addingRepasButton;
    private EditText nameRepas;
    private EditText typeRepas;
    private EditText typeCuisine;
    private EditText priceRepas;
    private EditText descriptionRepas;
    private EditText ingredients;
    private EditText allergies;

    public void initialiseLayoutVariables(){
        addRepasText = findViewById(R.id.addRepasText);
        addingRepasButton = findViewById(R.id.buttonAddRepas);
        nameRepas = findViewById(R.id.nomRepasPlainText);
        typeRepas = findViewById(R.id.typeDeRepasPlainText);
        typeCuisine = findViewById(R.id.typeDeCuisinePlainText);
        priceRepas =(EditText) findViewById(R.id.prixNumberDecimal);
        descriptionRepas = findViewById(R.id.descriptionTextMultiLine);
        ingredients = findViewById(R.id.ingredientsTextMultiLine);
        allergies = findViewById(R.id.allergiesTextMultiLine);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repas);
        initialiseLayoutVariables();


        priceRepas.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(5, 2)});
        userEmail = getIntent().getStringExtra("email");
        db = Utils.getAccountDatabaseReference(Utils.CUISINIER_ROLE, userEmail);

        addingRepasButton.setOnClickListener(view -> {addRepas();});

    }

    //adds a new repas (that will be created in the process) to the list of repas of the cuisinier
    private void addRepas(){

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Get the cuisinier from database
                Cuisinier cuisinier = snapshot.getValue(Cuisinier.class);
                Repas newRepas = createRepas();
                //create a new id for the repas
                String key = db.child(Utils.DB_REPAS_PATH).push().getKey();
                //set the id and the cuisnier email for future database references
                newRepas.setId(key);
                newRepas.setCuisinierEmail(cuisinier.getEmail());
                //send the class to a map then update the database
                //at the cuisnier's mesRepas path
                db.child(Utils.DB_REPAS_PATH).child(key).updateChildren(newRepas.toMapRepas());

                Toast.makeText(AddRepasActivity.this, "Meal added successfully.", Toast.LENGTH_SHORT).show();
                sendToRepasPage(); //send back to the repas page after it's been added to database
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    //reads the input fields of the cook and creates a repas object
    public Repas createRepas(){
        //name of repas
        String repasName = nameRepas.getText().toString();
        //type of repas
        String repasType = typeRepas.getText().toString();
        //type de cuisine
        String repasCuisine = typeCuisine.getText().toString();
        //description
        String repasDescription = descriptionRepas.getText().toString();
        //ingredients
        String repasIngredients = ingredients.getText().toString();
        //allergies
        String repasAllergies = allergies.getText().toString();
        //price
        String repasPrice = priceRepas.getText().toString();
        double repasPriceDouble = Double.parseDouble(repasPrice); //convert price to double

        return new Repas(repasDescription, repasName, true, repasType, repasCuisine, repasIngredients, repasAllergies, repasPriceDouble, false);

    }

    //send the client user towards the repas page
    public void sendToRepasPage(){
        Intent intent = new Intent(this, RepasActivity.class);
        intent.putExtra("email", userEmail);
        startActivity(intent);
    }

    //makes sure the price is in the correct "format"
    public class DecimalDigitsInputFilter implements InputFilter
    {
        Pattern pattern;

        public DecimalDigitsInputFilter(int digitsBeforeDecimal, int digitsAfterDecimal)
        {
            pattern = Pattern.compile("(([1-9]{1}[0-9]{0," + (digitsBeforeDecimal - 1) + "})?||[0]{1})((\\.[0-9]{0," + digitsAfterDecimal + "})?)||(\\.)?");
        }

        @Override public CharSequence filter(CharSequence source, int sourceStart, int sourceEnd, Spanned destination, int destinationStart, int destinationEnd)
        {
            // Remove the string out of destination that is to be replaced.
            String newString = destination.toString().substring(0, destinationStart) + destination.toString().substring(destinationEnd, destination.toString().length());

            // Add the new string in.
            newString = newString.substring(0, destinationStart) + source.toString() + newString.substring(destinationStart, newString.length());

            // Now check if the new string is valid.
            Matcher matcher = pattern.matcher(newString);

            if(matcher.matches())
            {
                // Returning null indicates that the input is valid.
                return null;
            }

            // Returning the empty string indicates the input is invalid.
            return "";
        }
    }


}