# DateAndTimePicker

<div align="center">
	<img src="https://user-images.githubusercontent.com/34453671/90985821-168e6d80-e587-11ea-9d79-1e8fba46deee.gif">
</div>


## Prerequisites

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

## Dependency

Add this to your module's `build.gradle` file (make sure the version matches the JitPack badge above):

```gradle
dependencies {
	...
	 implementation 'com.github.brkcnszgn:DateAndTimePicker:0.0.6'
}
```

## Configuration

This step is optional, but if you want you can configure some DateAndTimePicker parameters. Place this anywhere in your app:

```java
 <com.brkcnszgn.dateandtimepickerdialog.DateTimePickerView
        android:layout_marginTop="16dp"
        android:id="@+id/dt"
        android:layout_height="wrap_content"
        app:dtpv_icon_color="@color/colorAccent"
        android:layout_width="match_parent"
        app:dtpv_textColor="@color/colorPrimary"
        app:dtpv_title="Date" />
```

app parameters:

```java
app:dtpv_title="Date" - Title
 app:dtpv_icon_color="@color/black" - iconColor
 app:dtpv_textColor="@color/black" - titleColor
 


## Usage
 dtPicker.getText(); -> Get time for string 
 dtPicker.setValidation(); -> select or notSelect validation
 dtPicker.setText(); -> 
 dtPicker.cleanText(); -> cleanData
 dtPicker.getDateTime -> get time for Date
 dtPicker.getDateTimeTR -> Turkish date
 dtPicker.setDefault -> clean all Data
 dtPicker.titleColor -> title and icon color change
 
 
 dateTimePickerView = findViewById(R.id.dt);
      //  dateTimePickerView.titleColor(R.color.color_black);
        dateTimePickerView.setOnClickListener(new ClickListener() {
            @Override
            public void onClick() {
            }
        });
```

