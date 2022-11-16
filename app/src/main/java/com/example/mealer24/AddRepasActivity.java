package com.example.mealer24;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repas);
        userEmail = getIntent().getStringExtra("email");
        db = Utils.getAccountDatabaseReference(Utils.CUISINIER_ROLE, userEmail);
        initialiseLayoutVariables();
    }
    public void initialiseLayoutVariables(){
        addRepasText = findViewById(R.id.addRepasText);
        addingRepasButton = findViewById(R.id.buttonAddRepas);
        nameRepas = findViewById(R.id.nomRepasPlainText);
        typeRepas = findViewById(R.id.typeDeRepasPlainText);
        typeCuisine = findViewById(R.id.typeDeCuisinePlainText);
        priceRepas = findViewById(R.id.prixNumberDecimal);
        descriptionRepas = findViewById(R.id.descriptionTextMultiLine);
        ingredients = findViewById(R.id.ingredientsTextMultiLine);
        allergies = findViewById(R.id.allergiesTextMultiLine);
    }

    public void createRepas(){

        //name of repas
        String repasName = nameRepas.getText().toString();
        UTF8Encoder encodedname = new UTF8Encoder(repasName);
        String encodedNameAsString = encodedname.getEncodedString();

        //type of repas
        String repasType = typeRepas.getText().toString();
        UTF8Encoder encodedtype = new UTF8Encoder(repasType);
        String encodedTypeAsString = encodedtype.getEncodedString();

        //type de cuisine
        String repasCuisine = typeCuisine.getText().toString();
        UTF8Encoder encodedCuisine = new UTF8Encoder(repasCuisine);
        String encodedCuisineAsString = encodedCuisine.getEncodedString();

        //description
        String repasDescription = descriptionRepas.getText().toString();
        UTF8Encoder encodeddescription = new UTF8Encoder(repasDescription);
        String encodedDescriptionAsString = encodeddescription.getEncodedString();

        //ingredients
        String repasIngredients = ingredients.getText().toString();
        UTF8Encoder encodedingredients = new UTF8Encoder(repasIngredients);
        String encodedIngredientsAsString = encodedingredients.getEncodedString();

        //allergies
        String repasAllergies = allergies.getText().toString();
        UTF8Encoder encodedallergies = new UTF8Encoder(repasAllergies);
        String encodedAllergiesAsString = encodedallergies.getEncodedString();

        //price //shouldn't this be int?
        String repasPrice = priceRepas.getText().toString();
        UTF8Encoder encodedprice = new UTF8Encoder(repasPrice);
        String encodedPriceAsInteger = encodedprice.getEncodedString();
    }
    private void addRepas(){

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Cuisinier cuisinier = snapshot.getValue(Cuisinier.class);
                Repas newRepas = new Repas(cuisinier, "food", "apple", true, "apple", "", "", "", 0);
                String key = db.child("mesRepas").push().getKey();
                newRepas.setId(key);
//                cuisinier.addToListOfRepas(newRepas);
                Map<String, Object> repasMap = newRepas.toMapRepas();
                db.child("mesRepas").child(key).updateChildren(repasMap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}