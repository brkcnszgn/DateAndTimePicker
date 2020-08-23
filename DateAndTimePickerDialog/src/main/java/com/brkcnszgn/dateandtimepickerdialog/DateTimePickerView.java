package com.brkcnszgn.dateandtimepickerdialog;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class DateTimePickerView extends LinearLayout {

    private Context context;

    private String mDay;
    private String mMonth;
    private String mYear;
    private String mHour;
    private String mMinute;
    private String title;
    private TextWatcher mTextWatcher;
    private Calendar mCalendar;

    MaterialEditText txtInput;
    RelativeLayout btnPick;
    RelativeLayout layoutBox;
    TextView txtTitle;
    View viewLine;


    public DateTimePickerView(Context context) {
        this(context, null);
        init(context, null, null);
    }

    public DateTimePickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(getContext(), R.layout.view_date_time_picker, this);
        init(context, attrs, view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DateTimePickerView(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        init(context, attrs, null);
    }

    public void init(final Context context, AttributeSet attrs, View view) {
        this.context = context;
        txtInput = view.findViewById(R.id.txtInput);
        btnPick = view.findViewById(R.id.btnPick);
        layoutBox = view.findViewById(R.id.layoutBox);
        txtTitle = view.findViewById(R.id.txtTitle);
        viewLine = view.findViewById(R.id.viewLine);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DateTimePickerView);
        try {
            title = typedArray.getString(R.styleable.DateTimePickerView_dtpv_title);

        } finally {
            typedArray.recycle();
        }

        txtTitle.setText(title);
        txtInput.setHint(title);

        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, -25, 0, 0);
        layoutBox.setLayoutParams(lp);

        txtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    if (txtTitle.getVisibility() == VISIBLE) {
                        Animations.fadeOut(txtTitle);
                    }
                    txtTitle.setVisibility(INVISIBLE);
                } else {
                    if (txtTitle.getVisibility() == INVISIBLE) {
                        Animations.fadeIn(txtTitle);
                    }
                    txtTitle.setVisibility(VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mCalendar = Calendar.getInstance();
        mYear = String.valueOf(mCalendar.get(Calendar.YEAR));

        mMonth = String.format("%02d", mCalendar.get(Calendar.MONTH) + 1);
        mDay = String.format("%02d", mCalendar.get(Calendar.DAY_OF_MONTH));
        mHour = String.format("%02d", mCalendar.get(Calendar.HOUR_OF_DAY));
        mMinute = String.format("%02d", mCalendar.get(Calendar.MINUTE));
    }

    public String getText() {
        return Objects.requireNonNull(txtInput.getText()).toString();
    }

    public String getDate() {
        if (mMonth.length() == 1) {
            mMonth = "0" + mMonth;
        }
        return mYear + "-" + mMonth + "-" + mDay + "T" + mHour + ":" + mMinute + ":00.000Z";
    }


    public void setOnClickListener(final ClickListener l) {
        btnPick.setOnClickListener(v -> {
            openDialog();
            l.onClick();
        });

        txtInput.setOnClickListener(v -> {
            openDialog();
            l.onClick();
        });

    }

    public void openDialog() {
        new DatePickerDialog(context, R.style.DialogTheme, (view, year, month, dayOfMonth) -> {
            month += 1;
            mDay = String.format("%02d", dayOfMonth);
            mMonth = String.format("%02d", month);
            mYear = String.valueOf(year);
            txtInput.setText(mDay + "." + mMonth + "." + mYear + " 00:00");

            mCalendar.set(year, month, dayOfMonth);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    openTimePickerDialog();
                }
            }, 100);

        }, Integer.parseInt(mYear), Integer.parseInt(mMonth) - 1, Integer.parseInt(mDay)).show();

    }

    public void openTimePickerDialog() {
        new TimePickerDialog(context, R.style.DialogTheme, (view, hourOfDay, minute) -> {
            mHour = String.format("%02d", hourOfDay);
            mMinute = String.format("%02d", minute);
            mCalendar.set(Integer.valueOf(mYear), Integer.valueOf(mMonth) - 1, Integer.valueOf(mDay), hourOfDay, minute);
            txtInput.setText(mDay + "." + mMonth + "." + mYear + " " + mHour + ":" + mMinute);
        }, Integer.parseInt(mHour), Integer.parseInt(mMinute), true).show();
    }

    public void cleanText() {
        if (mTextWatcher != null) {
            txtInput.removeTextChangedListener(mTextWatcher);
            txtInput.setText("");
            setDefault();
            setTextWatcher();
        }
    }

    public void setValidation() {
        setError();
        setTextWatcher();
    }

    private void setTextWatcher() {
        txtInput.addTextChangedListener(mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    viewLine.setBackgroundColor(ContextCompat.getColor(context, R.color.color_view_line_error));
                } else {
                    viewLine.setBackgroundColor(ContextCompat.getColor(context, R.color.color_view_line_default));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public Date getDateTime() {
        return mCalendar.getTime();
    }

    public Date getDateTimeTR() {
        mCalendar.add(Calendar.HOUR_OF_DAY, -3);
        return mCalendar.getTime();
    }

    public void setDefault() {
        viewLine.setBackgroundColor(ContextCompat.getColor(context, R.color.color_view_line_default));
    }

    public void setError() {
        viewLine.setBackgroundColor(ContextCompat.getColor(context, R.color.color_view_line_error));

    }

    public void setText(String sdate1) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);

        try {
            Date dateAt = dateFormat.parse(sdate1);

            mCalendar.setTime(dateAt);

            mYear = String.valueOf(mCalendar.get(Calendar.YEAR));
            // mMonth = String.valueOf(mCalendar.get(Calendar.MONTH) + 1);
            mMonth = String.format("%02d", mCalendar.get(Calendar.MONTH) + 1);

            //mDay = String.valueOf(mCalendar.get(Calendar.DAY_OF_MONTH));
            mDay = String.format("%02d", mCalendar.get(Calendar.DAY_OF_MONTH));


            // mHour = String.valueOf(mCalendar.get(Calendar.HOUR_OF_DAY));
            mHour = String.format("%02d", mCalendar.get(Calendar.HOUR_OF_DAY));

            //mMinute = String.valueOf(mCalendar.get(Calendar.MINUTE));
            mMinute = String.format("%02d", mCalendar.get(Calendar.MINUTE));

        } catch (ParseException e) {
            // System.out.println(e.getMessage());
        }


        txtInput.setText(mDay + "." + mMonth + "." + mYear + " " + mHour + ":" + mMinute);
    }
}
