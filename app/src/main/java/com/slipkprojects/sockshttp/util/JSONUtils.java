package com.slipkprojects.sockshttp.util;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JSONUtils
{
    public static Comparator getComparator(final String tagJSON, final int type)
    {
        Comparator c =  new Comparator()
        {
            public int compare(Object a, Object b)
            {
                try
                {
                    JSONObject ja = (JSONObject)a;
                    JSONObject    jb = (JSONObject)b;

                    switch (type)
                    {
                        case 1:// String
                            return ja.optString(tagJSON, "").toLowerCase()
								.compareTo(jb.optString(tagJSON, "").toLowerCase());
                        case 2:// int
                            int valA =  ja.getInt(tagJSON);
                            int valB =  jb.getInt(tagJSON);
                            if(valA > valB)
                                return 1;
                            if(valA < valB)
                                return -1;

                        case 3:// double
                            String v1 = ja.getString(tagJSON).replace(",",".");
                            String v2 = jb.getString(tagJSON).replace(",",".");

                            double valAd = new Double(v1);// ja.getDouble(tagJSON);
                            double valBd = new Double(v2);//  jb.getDouble(tagJSON);
                            if(valAd > valBd)
                                return 1;
                            if(valAd < valBd)
                                return -1;

                    }
                }
                catch (Exception e)
                {
                    // Toast.makeText(context, e.getMessage(), 1).show();
                }
                return 0;
            }
        };

        return c;
    }

    public static JSONArray sort(JSONArray array, Comparator c){
        List asList = new ArrayList(array.length());
        for (int i=0; i<array.length(); i++){
            asList.add(array.opt(i));
        }
        Collections.sort(asList, c);
        JSONArray  res = new JSONArray();
        for (Object o : asList){
            res.put(o);
        }
        return res;
    }

    public static JSONArray sortByType(JSONArray array, int type) throws Exception{
        List asList = new ArrayList(array.length());
        for (int i=0; i<array.length(); i++){
            if(array.getJSONObject(i).getInt("Type") == type){
                asList.add(array.opt(i));
            }
        }
        JSONArray  res = new JSONArray();
        for (Object o : asList){
            res.put(o);
        }
        return res;
    }
}

