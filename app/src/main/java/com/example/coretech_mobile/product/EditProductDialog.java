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

public class EditProductDialog extends AppCompatDialogFragment {

    EditText productId, productName, productDescription, productPrice, productQuantity;
    UpdateProductDialogListener productDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.product_dialog_layout, null);

        initAndSetIfNonNullEditTexts(view);

        builder.setView(view)
                .setTitle("New product")
                .setNegativeButton("Cancel", (dialog, which) -> {
                    Toast.makeText(getContext(), "Cancel Pressed", Toast.LENGTH_LONG).show();

                })
                .setPositiveButton("Ok", (dialog, which) -> {
                    Long id = Long.parseLong(productId.getText().toString());
                    String name = productName.getText().toString();
                    String description = productDescription.getText().toString();
                    Long price = Long.parseLong(productPrice.getText().toString());
                    Long quntity = Long.parseLong(productQuantity.getText().toString());
                    Product product = new Product(id, name, description, price, quntity);
                    productDialogListener.updateProduct(product);
                });
        return builder.create();
    }

    private void initAndSetIfNonNullEditTexts(View view) {
        if(getArguments() != null) {
            Long id = getArguments().getLong("id");
            String name = getArguments().getString("name");
            String description = getArguments().getString("description");
            Long price = getArguments().getLong("price");
            Long quantity = getArguments().getLong("quantity");

            productId = view.findViewById(R.id.product_id);
            productName = view.findViewById(R.id.product_name);
            productDescription = view.findViewById(R.id.product_description);
            productPrice = view.findViewById(R.id.price);
            productQuantity = view.findViewById(R.id.quantity);

            productId.setText(String.valueOf(id));
            productName.setText(name);
            productDescription.setText(description);
            productPrice.setText(String.valueOf(price));
            productQuantity.setText(String.valueOf(quantity));
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            productDialogListener = (UpdateProductDialogListener) context;
        }catch (ClassCastException classCastException){
            throw new ClassCastException(context.toString() + "must implement ProductDialogListener");
        }
    }

    public interface UpdateProductDialogListener {
        void updateProduct(Product product);
    }
}
