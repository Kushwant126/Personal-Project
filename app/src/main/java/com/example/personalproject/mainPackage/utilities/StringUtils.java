package com.example.personalproject.mainPackage.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import com.example.personalproject.mainPackage.common.AppConstants;
import com.example.personalproject.mainPackage.common.Preference;
import com.example.personalproject.mainPackage.dataobject.CustomerPerfectStoreMonthlyScoreDO;

public class StringUtils 
{
	/**
	 * This method returns an int value
	 * @param str
	 */
	public static int getIntSlow(String str)
	{
		int value = 0;
		
		if(str == null || str.equalsIgnoreCase("") || str.contains("T") || str.equalsIgnoreCase("null") || str.contains(":"))
			return value;
		
		str = str.replace(",", "");
		
		if(str.contains("."))
			return (int) getFloat(str);
		
		try
		{
			value = Integer.parseInt(str);
		}
		catch (Exception e)
		{
			value = (int) getFloat(str);
			LogUtils.errorLog("StringUtils", "Error occurred while parsing as integer"+e.toString());
		}
		return value;
	}
	public static int getInt(String str)
	{
		try
		{
			return Integer.parseInt(str);
		}
		catch(Exception e)
		{
			
		}
		return getIntSlow(str);
	}
	/**
	 * This method returns an int value
	 * @param str
	 */
	public static boolean getBoolean(String str)
	{
		boolean value = false;
		
		if(str == null || str.equalsIgnoreCase(""))
			return value;
		
		try
		{
			value = Boolean.parseBoolean(str);
		}
		catch (Exception e)
		{
			LogUtils.errorLog("StringUtils", "Error occurred while parsing as boolean"+e.toString());
		}
		return value;
	}
	
	public static double round(String number, int precision)
	{
//		precision = 6;
		precision = AppConstants.NUMBER_OF_DECIMALS_TO_ROUND;
		double temp = getDouble(""+number); 
		return  ((int)(temp * (int)Math.pow(10, precision) + .5)  / Math.pow(10, precision));
	}
	public static double roundOthers(String number, int precision)
	{
//		precision = 2;
		precision = AppConstants.NUMBER_OF_DECIMALS_TO_ROUND;
		double temp = getDouble(""+number); 
		return  ((int)(temp * (int)Math.pow(10, precision) + .5)  / Math.pow(10, precision));
	}
	
	public static double round(double number, int precision)
	{
//		precision = 6;
		precision = AppConstants.NUMBER_OF_DECIMALS_TO_ROUND;
		return  ((int)(number * (int)Math.pow(10, precision) + .5)  / Math.pow(10, precision));
	}

	public static double roundNormal(double number, int precision)
	{
		precision = AppConstants.NUMBER_OF_DECIMALS_TO_ROUND;
		return  ((int)(number * (int)Math.pow(10, precision) + .5)  / Math.pow(10, precision));
	}
	
	public static double roundDouble(double value, int places) {
		places = AppConstants.NUMBER_OF_DECIMALS_TO_ROUND;
		if (places < 0)
			throw new IllegalArgumentException();

//		long factor = (long) Math.pow(10, places);
//		value = value * factor;
//		long tmp = Math.round(value);
//		return (double) tmp / factor;
		return roundDoublePlacesOriginal(value, places);
	}
	
	public static double roundDoublePlaces(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();
//		places = AppConstants.NUMBER_OF_DECIMALS_TO_ROUND;

//		long factor = (long) Math.pow(10, places);
//		value = value * factor;
//		long tmp = Math.round(value);
//		return (double) tmp / factor;
		return roundDoublePlacesOriginal(value, places);
	}

	//Use this for custom number of decimals
	public static double roundDoubleNew(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();
		return roundDoublePlacesOriginal(value, places);
	}

	public static double roundDoublePlacesOriginal(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		value = roundDoubleExact(value,5);
		long factor = (long) Math.pow(10, places+1);
		long multiple = (long) Math.pow(10, places);
//		//=====================If condition is added on 30th NOV 20
//		if(value<0)
//			value = (-1)*value*factor+5;
//		else
			value = value*factor+5;

		value = value/factor;
		value = Math.floor(roundDoubleExact(value*multiple,5))/multiple;

		return (double) value;
	}

	public static double roundDoubleNew1(double value, int places) {///added by rajaprasad 04-10-2023
		if (places < 0) throw new IllegalArgumentException();
		return roundDoublePlacesOriginal1(value, places);
	}
	public static double roundDoublePlacesOriginal1(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		value = roundDoubleExact(value,5);
		long factor = (long) Math.pow(10, places+1);
		long multiple = (long) Math.pow(10, places);
//		//=====================If condition is added on 30th NOV 20
//		if(value<0)
//			value = (-1)*value*factor+5;
//		else
		//value = value*factor+5;
		value = value*factor;

		value = value/factor;
		value = Math.floor(roundDoubleExact(value*multiple,5))/multiple;

		return (double) value;
	}
	public static double roundDoubleExact(double value, int places) {
//		if (places < 0) throw new IllegalArgumentException();
//
//		long factor = (long) Math.pow(10, places);
//		value = value * factor;
//		long tmp = Math.round(value);
//		return (double) tmp / factor;

		boolean isNegative = false;
		if (value < 0) {
			isNegative = true;
			value = -value;
		}
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		if (isNegative)
			return -(double) tmp / factor;
		else
			return (double) tmp / factor;
	}

	public static float roundFloat(float value, int places) {

		try {
			places = AppConstants.NUMBER_OF_DECIMALS_TO_ROUND;
			if (places < 0)
				throw new IllegalArgumentException();

			long factor = (long) Math.pow(10, places);
			value = value * factor;
			long tmp = Math.round(value);
			return (float) tmp / factor;

		} catch (Exception e) {
			return value;
		}
	}
	
	public static float roundFloatPlaces(float value, int places) {
		
		try {
			if (places < 0)
				throw new IllegalArgumentException();
			
			long factor = (long) Math.pow(10, places);
			value = value * factor;
			long tmp = Math.round(value);
			return (float) tmp / factor;
			
		} catch (Exception e) {
			return value;
		}
	}

	public static float roundFloatNew(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();
		return roundFloatPlacesOriginal(value, places);
	}

	public static float roundFloatPlacesOriginal(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		value = roundFloatExact(value,4);
		long factor = (long) Math.pow(10, places+1);
		long multiple = (long) Math.pow(10, places);
		value = value*factor+5;
		value = value/factor;
		value = Math.floor(roundDoubleExact(value*multiple,4))/multiple;

		return (float) value;
	}

	public static float roundFloatExact(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (float) tmp / factor;
	}
	
	public String getImageNameFromUrl(String url)
	{
		String imgName = "";
		
		if(url.contains("*/"))
		{
			imgName = url.substring(url.indexOf("/img/")+5);
			imgName = imgName.replace("/","");
			imgName = imgName.replace("*","");			
		}
		else
		{
			imgName = url.substring(url.lastIndexOf("/"));
			
			imgName = imgName.substring(0,imgName.indexOf("."));	
		}
		
		return imgName;
	}
	/**
	 * This method returns an String value
	 * @param str
	 */
	public static String getString(boolean str)
	{
		String value = "";
		try
		{
			value = String.valueOf((str));
		}
		catch (Exception e)
		{
			LogUtils.errorLog("StringUtils", "Error occurred while getString"+e.toString());
		}
		return value;
	}
	/**
	 * This method returns an String value
	 * @param str
	 */
	public static String getString(int str)
	{
		String value = "";
		try
		{
			value = String.valueOf((str));
		}
		catch (Exception e)
		{
			LogUtils.errorLog("StringUtils", "Error occurred while getString"+e.toString());
		}
		return value;
	}
	public static String getString(Long str){
		String value = "";
		try
		{
			value = String.valueOf((str));
		}
		catch (Exception e)
		{
			LogUtils.errorLog("StringUtils", "Error occurred while getString"+e.toString());
		}
		return value;
	}

	/**
	 * This method returns current location as a String value
	 */
//	public static String getLocation()
//	{
//		return AppConstants.locationName;
//	}
	/**
	 * This method returns boolean if given String is a valid email
	 * @param string
	 */
	public static boolean isValidEmail(String string)
	{
		 final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
	                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
	                "\\@" +
	                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
	                "(" +
	                "\\." +
	                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
	                ")+"
	            );
		Matcher matcher = EMAIL_ADDRESS_PATTERN.matcher(string);
		boolean value = matcher.matches();
		return value;
	}
	/**
	 * This method returns float value
	 * @param string
	 */
	public static float getFloatSlow(String string) 
	{
		float value = 0f;
		
		if(string == null || string.equalsIgnoreCase("") || string.equalsIgnoreCase(".") || string.equalsIgnoreCase("null") || string.contains("T"))
			return value;
		
		string = string.replace(",", "");
		
		try
		{
			value = Float.parseFloat(string);
		}
		catch(Exception e)
		{
			LogUtils.errorLog("StringUtils", "Error occurred while getFloat"+e.toString());
		}
		
		return value;
	}
	
	public static float getFloat(String string) 
	{
		try
		{
			return Float.parseFloat(string);
		}
		catch(Exception e)
		{
		}
		
		return getFloatSlow(string);
	}
	
	/**
	 * This method returns long value
	 * @param string
	 */
	public static long getLong(String string) 
	{
		long value = 0;
		
		if(string == null || string.equalsIgnoreCase(""))
			return value;
		
		string = string.replace(",", "");
		
		try
		{
			value = Long.parseLong(string);
		}
		catch(Exception e)
		{
			LogUtils.errorLog("StringUtils", "Error occurred while getLong"+e.toString());
		}
		
		return value;
	}
	/**
	 * This method returns int value
	 * @param str
	 */
	public static int toInt(String str)
	{
		int value = -1;
		
		if(str == null || str.equalsIgnoreCase(""))
			return value;
		
		try
		{
			value = Integer.parseInt(str);
		}
		catch (Exception e)
		{
			LogUtils.errorLog("StringUtils", "Error occurred while toInt"+e.toString());
		}
		return value;
	}
	/**
	 * This method returns a String value
	 * @param checkString
	 */
	public static String checkString(String checkString)
	{
		return checkString != null ? checkString : "";
	}
	
	public static String setSuperScriptForNumber(int num)
	{
		String supStr = num+"";
		
		switch(num)
		{
		case 1 : 
			supStr +="<sup><small>st</small></sup>";
			break;
		case 2 : 
			supStr +="<sup><small>nd</small></sup>";
			break;
		case 3 : 
			supStr +="<sup><small>rd</small></sup>";
			break;
		default : 
			supStr +="<sup><small>th</small></sup>";
		}
		
		return supStr;
	}
	
	public static void write(String data,BufferedWriter out) throws IOException
	{
		out.write(data);
		out.newLine();
	}
	
	public static void printViaBluetooth(Context context,String fileName)
	{
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) 
		{
			// Device does not support Bluetooth
		}
		else
		{
			try
			{
				Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory()+ "/"+fileName));
		        Intent int_email = new Intent(); 
		        int_email.setAction(Intent.ACTION_SEND);
		        int_email.setType("image/*");
		        int_email.putExtra(Intent.EXTRA_STREAM,uri); 
		        if(Build.MODEL.equalsIgnoreCase("GT-P1000"))
		        {
		        	int_email.setClassName("com.android.bluetooth", "com.broadcom.bt.app.opp.OppLauncherActivity");
		        }
		        else
		        {
		        	int_email.setClassName("com.android.bluetooth", "com.android.bluetooth.opp.BluetoothOppLauncherActivity");
		        }
		        ((Activity) context).startActivityForResult(int_email,77);
			}
			catch (Exception e) 
			{
			}
		}
	}
	
	/**
	 * This method returns an int value
	 * @param str
	 */
	public static double getDouble(String str)
	{
		double value = 0;
		
		if(str == null || str.equalsIgnoreCase(""))
			return value;
		
		else if(str.contains(","))
			str = str.replace(",", "");
		
		try
		{
			value = Double.parseDouble(str);
		}
		catch (Exception e)
		{
			LogUtils.errorLog("StringUtils", "Error occurred while parsing as integer"+e.toString());
		}
		return value;
	}
	
	/**
	 * method to convert Stream to String
	 * @param inputStream
	 * @return String
	 * @throws IOException
	 */
	public static String convertStreamToString(InputStream inputStream) throws IOException
	{
		//Initialize variables
		String responce = "";
		
		if (inputStream != null)
		{
			Writer writer = new StringWriter();
		    char[] buffer = new char[1024];
		    try
		    {
		       //Reader
		       Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		       int n;
		       while ((n = reader.read(buffer)) != -1)
		       {
		    	   //writing
		            writer.write(buffer, 0, n);
		       }
		       responce =  writer.toString();
		       writer.close();
		    }
		    finally 
		    {
		    	//closing InputStream
		    	inputStream.close();
		    }
		    
		    return responce;
		}
		else 
		{       
			return "";
		}
    }
	
	public static String getUniqueUUID()
	{
		 UUID uuid = UUID.randomUUID();
	     String randomUUIDString = uuid.toString();
	     return randomUUIDString;
	}
	
	public static String geMonthlyKPIUUID(String site, Preference preference, String PerfectStoreGroupId)
	{
		CustomerPerfectStoreMonthlyScoreDO customerPerfectStoreMonthlyScoreDO=new CustomerPerfectStoreMonthlyScoreDO();
		customerPerfectStoreMonthlyScoreDO.CustomerCode=site;
		customerPerfectStoreMonthlyScoreDO.Year=CalendarUtils.getTargetCurrentYear();
		customerPerfectStoreMonthlyScoreDO.Month=CalendarUtils.getTargetCurrentMonth();
		customerPerfectStoreMonthlyScoreDO.SalesOrgCode=preference.getStringFromPreference(Preference.ORG_CODE, "");
		customerPerfectStoreMonthlyScoreDO.RouteCode=preference.getStringFromPreference(Preference.ROUTE_CODE, "");
		customerPerfectStoreMonthlyScoreDO.UserCode=preference.getStringFromPreference(Preference.EMP_NO, "");
		customerPerfectStoreMonthlyScoreDO.CustomerPerfectStoreMonthlyScoreUID =
				PerfectStoreGroupId + "_" +
						customerPerfectStoreMonthlyScoreDO.SalesOrgCode + "_" +
						customerPerfectStoreMonthlyScoreDO.RouteCode + "_" +
						customerPerfectStoreMonthlyScoreDO.UserCode + "_" +
						site + "_" +
						customerPerfectStoreMonthlyScoreDO.Month + "_" +
						customerPerfectStoreMonthlyScoreDO.Year + "_" +
						CalendarUtils.getCurrentDateCollections1();

		return customerPerfectStoreMonthlyScoreDO.CustomerPerfectStoreMonthlyScoreUID;
	}

	/**
	 * This method returns an String value
	 * @param floatValue
	 */
	public static String getStringFromDouble(double floatValue)
	{
		String value = "";
		try
		{
			value = String.valueOf(floatValue);
		}
		catch (Exception e)
		{
		}
		
		if(!value.equalsIgnoreCase(""))
		{
			if(value.split("\\.").length == 1)
				return value+".00";
			else
			{
				if(value.split("\\.")[1].length() == 2)
					return value;
				else if(value.split("\\.")[1].length() == 1)
					return value+"0";
				else if(value.split("\\.")[1].length() > 2)
					return value.split("\\.")[0]+"."+value.split("\\.")[1].substring(0, 2);
			}
		}
		return value;
	}
	public static boolean isEmpty(String string){

		if(string == null || string.equalsIgnoreCase("")){
			return true;
		}
		return false;
	}

	public static String getStringAfterNullCheck(String data){
		if(!TextUtils.isEmpty(data)){
			return data;
		}else{
			return "";
		}
	}
	public String getStringAfterNullChecAndOnEmptyReturnNA(String data){
		if(!TextUtils.isEmpty(data)){
			return data;
		}else{
			return "N/A";
		}
	}

}
