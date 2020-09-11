package com.sabari.bottomnav;
        import android.content.Context;
        import android.content.res.Configuration;

        import java.util.Locale;

public class LocaleHelper {
    public static void setLocale(Context context, String locale) {
        Locale localeObject = new Locale(locale);
        Locale.setDefault(localeObject);
        Configuration config = new Configuration(context.getResources().getConfiguration());
        config.locale = localeObject;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());


    }
}

