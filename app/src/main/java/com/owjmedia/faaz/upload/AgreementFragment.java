package com.owjmedia.faaz.upload;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceTextView;

/**
 * Created by salman on 1/19/18.
 */

public class AgreementFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.agreement_frg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtAgreement = view.findViewById(R.id.txt_agreement);
        checkboxAgreement = view.findViewById(R.id.checkbox_agreement);
        btnStart = view.findViewById(R.id.btn_start);
        txtAgreement.setText(AppManager.getString(getContext(), Constants.KEYS.AGREEMENT));
        checkboxAgreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    btnStart.setEnabled(true);
                else
                    btnStart.setEnabled(false);
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.replaceFragmentToActivitySlidly(getFragmentManager(), new UploadImageFragment(), R.id.contentFrame);
            }
        });

    }

    TypefaceTextView txtAgreement;
    AppCompatCheckBox checkboxAgreement;
    TypefaceTextView btnStart;

}
