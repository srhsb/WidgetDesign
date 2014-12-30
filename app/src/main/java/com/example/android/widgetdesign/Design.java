package com.example.android.widgetdesign;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;


/**
 * Implementation of App Widget functionality.
 */
public class Design extends AppWidgetProvider {

    //Max recent matches 3 allowed
    public static int liveCount=0;
    public static int recentCount=3;

    //Max matches 3 allowed
    private static int liveCountFromServer= 0;
    private final static int recentCountFromServer= 3;


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;




        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        RemoteViews viewCentre = new RemoteViews(context.getPackageName(), R.layout.centre);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.design);



        if (intent.getAction()!=null && intent.getAction().equals("Match1")) {
            Log.i("in Match2", intent.getAction());

            views.setTextViewText(R.id.status, "Match #2");
            views.setImageViewResource(R.id.dot, R.drawable.dot_center);
            views.setImageViewResource(R.id.back, R.drawable.back);
            views.setImageViewResource(R.id.forw, R.drawable.right);
            if(liveCountFromServer == 2)
            {
                intent.setAction("RMatch3");

            }
            else
            {
                intent.setAction("Match3");
            }
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.forw, pi);

            PendingIntent pib = PendingIntent.getBroadcast(context, 0, new Intent(context,Design.class).setAction("First"), PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.back, pib);

            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context, Design.class), views);


//            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context,Design.class),new RemoteViews(context.getPackageName(), R.layout.centre));
        }
        else if (intent.getAction()!=null && intent.getAction().equals("First")) {
            Log.i("in Match1", intent.getAction());
            views.setImageViewResource(R.id.dot,R.drawable.dots);
            views.setImageViewResource(R.id.back,R.drawable.back_dim);
            views.setTextViewText(R.id.status, "Match #1");
            if(liveCountFromServer == 1)
            {
                intent.setAction("RMatch1");
            }
            else {
                intent.setAction("Match1");
            }

            PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.forw, pi);

            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context, Design.class), views);

        }

        else if (intent.getAction()!=null && intent.getAction().equals("Match3")) {

            Log.i("in Match3", intent.getAction());
            views.setImageViewResource(R.id.dot,R.drawable.dot_end);
            views.setImageViewResource(R.id.forw,R.drawable.right_dim);
            views.setTextViewText(R.id.status, "Match #3");
            intent.setAction("Match1");
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.back, pi);

            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context, Design.class), views);
//            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context,Design.class),new RemoteViews(context.getPackageName(), R.layout.centre));
        }

        else if (intent.getAction()!=null && intent.getAction().equals("RMatch1")) {
            Log.i("in RMatch2", intent.getAction());

            viewCentre.setTextViewText(R.id.status, "RMatch #2");
            viewCentre.setImageViewResource(R.id.dot, R.drawable.dot_center);
            viewCentre.setImageViewResource(R.id.backC, R.drawable.back);
            viewCentre.setImageViewResource(R.id.forwC, R.drawable.right);
            intent.setAction("RMatch3");
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            viewCentre.setOnClickPendingIntent(R.id.forwC, pi);

            if(liveCountFromServer == 1)
            {
                PendingIntent pib = PendingIntent.getBroadcast(context, 0, new Intent(context,Design.class).setAction("First"), PendingIntent.FLAG_UPDATE_CURRENT);
                viewCentre.setOnClickPendingIntent(R.id.backC, pib);

            }
            else {
                PendingIntent pib = PendingIntent.getBroadcast(context, 0, new Intent(context, Design.class).setAction("RFirst"), PendingIntent.FLAG_UPDATE_CURRENT);
                viewCentre.setOnClickPendingIntent(R.id.backC, pib);
            }
            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context, Design.class), viewCentre);


//            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context,Design.class),new RemoteviewCentre(context.getPackageName(), R.layout.centre));
        }
        else if (intent.getAction()!=null && intent.getAction().equals("RFirst")) {
            Log.i("in RMatch1", intent.getAction());
            viewCentre.setImageViewResource(R.id.dot,R.drawable.dots);
            viewCentre.setImageViewResource(R.id.backC,R.drawable.back_dim);
            viewCentre.setTextViewText(R.id.status, "RMatch #1");
            intent.setAction("RMatch1");
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            viewCentre.setOnClickPendingIntent(R.id.forwC, pi);

            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context, Design.class), viewCentre);

        }

        else if (intent.getAction()!=null && intent.getAction().equals("RMatch3")) {

            Log.i("in RMatch3", intent.getAction());
            viewCentre.setImageViewResource(R.id.dot,R.drawable.dot_end);
            viewCentre.setImageViewResource(R.id.forwC,R.drawable.right_dim);
            viewCentre.setTextViewText(R.id.status, "RMatch #3");
            if(liveCountFromServer == 2)
            {
                intent.setAction("Match1");
            }
            else{
                intent.setAction("RMatch1");
            }

            PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            viewCentre.setOnClickPendingIntent(R.id.backC, pi);

            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context, Design.class), viewCentre);
//            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context,Design.class),new RemoteViews(context.getPackageName(), R.layout.centre));
        }


//        else if (intent.getAction()!=null && intent.getAction().equals("Back")) {
//
//            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context, Design.class), new RemoteViews(context.getPackageName(), R.layout.centre));
//
//        }

        Log.i("Intent from main -->>", intent.getAction());

    }
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        if(liveCountFromServer>=1) {

            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.design);
            views.setTextViewText(R.id.status,"Match 1");
            Intent intent = new Intent(context, Design.class);
            if(liveCountFromServer == 1)
            {
                intent.setAction("RMatch1");

            }
            else {
                intent.setAction("Match1");
            }

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            views.setOnClickPendingIntent(R.id.forw, pendingIntent);
            views.setImageViewResource(R.id.back,R.drawable.back_dim);
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
        else
        {

            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.centre);
            views.setTextViewText(R.id.status,"RMatch 1");
            Intent intent = new Intent(context, Design.class);
            intent.setAction("RMatch1");

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            views.setOnClickPendingIntent(R.id.forwC, pendingIntent);
            views.setImageViewResource(R.id.backC,R.drawable.back_dim);

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);

        }
    }

}


