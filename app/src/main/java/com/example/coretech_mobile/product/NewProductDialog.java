package com.example.coretech_mobile.product;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.coretech_mobile.R;
import com.example.coretech_mobile.model.Product;

public class NewProductDialog extends AppCompatDialogFragment {

    EditText productName, productDescription, productPrice, productQuantity;
    NewProductDialogListener productDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.product_dialog_layout, null);

        productName = view.findViewById(R.id.product_name);
        productDescription = view.findViewById(R.id.product_description);
        productPrice = view.findViewById(R.id.price);
        productQuantity = view.findViewById(R.id.quantity);
        builder.setView(view)
                .setTitle("Nowy Produkt")
                .setNegativeButton("Cancel", (dialog, which) -> {
                    Toast.makeText(getContext(), "Cancel Pressed", Toast.LENGTH_LONG).show();

                })
                .setPositiveButton("Ok", (dialog, which) -> {
                    String name = productName.getText().toString();
                    String description = productDescription.getText().toString();
                    Long price = Long.parseLong(productPrice.getText().toString());
                    Long quntity = Long.parseLong(productQuantity.getText().toString());
                    Product product = new Product(name, description, price, quntity);
                    productDialogListener.saveProduct(product);
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            productDialogListener = (NewProductDialogListener) context;
        }catch (ClassCastException classCastException){
            throw new ClassCastException(context.toString() + "must implement ProductDialogListener");
        }
    }

    public interface NewProductDialogListener {
        void saveProduct(Product product);
    }
}
