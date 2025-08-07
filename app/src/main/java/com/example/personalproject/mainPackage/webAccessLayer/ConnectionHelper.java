package com.example.personalproject.mainPackage.webAccessLayer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Date;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.conn.ConnectTimeoutException;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.example.personalproject.mainPackage.MyApplication;
import com.example.personalproject.mainPackage.common.AppConstants;
import com.example.personalproject.mainPackage.common.Preference;
import com.example.personalproject.mainPackage.utilities.LogUtils;

/**
 * Class to setting url connection
 */
public class ConnectionHelper
{
	public static final int TIMEOUT_CONNECT_MILLIS 	= 30000;
	public static final int TIMEOUT_READ_MILLIS 	= TIMEOUT_CONNECT_MILLIS - 5000;
	//Initializing Variable
	
	public interface ConnectionExceptionListener
	{
		public void onConnectionException(Object msg);
	}
	public interface ConnectionHelperListener
	{
		public void onResponseRetrieved(String msg);
	}
	
	private String strRequest,methodName;
	private DefaultHandler handler;
	/**
	 * Method to setting url connection
	 * @param strUrl
	 * @param handler
	 */
	
	private ConnectionExceptionListener listener;
	
	public ConnectionHelper(ConnectionExceptionListener listener)
	{
		this.listener = listener;
	}


	private final class UIHandler extends Handler
	{
	    public static final int DISPLAY_UI_DIALOG = 1;
	    public UIHandler(Looper looper)
	    {
	        super(looper);
	    }

	    @Override
	    public void handleMessage(Message msg)
	    {
	        switch(msg.what)
	        {
		        case UIHandler.DISPLAY_UI_DIALOG:
		        {
		        	if(listener != null)
					{
						listener.onConnectionException(msg.obj);
					}
		        }
		        default:
		            break;
	        }
	    }
	}

	//to handle UI request
	 public void handleUIRequest(String message)
	 {
		 try
		 {
			 Thread uiThread = new HandlerThread("UIHandler");
			 uiThread.start();
			 UIHandler uiHandler = new UIHandler(((HandlerThread) uiThread).getLooper());
	
			 Message msg = uiHandler.obtainMessage(UIHandler.DISPLAY_UI_DIALOG);
			 msg.obj = message;
			 uiHandler.sendMessage(msg);
		 }
		 catch (Exception e) 
		 {
			e.printStackTrace();
		 }
	 }
	 
	public static void writeIntoLog(String str, InputStream is) throws IOException
	{
		try
		{
			 BufferedInputStream bis = new BufferedInputStream(is);
			 FileOutputStream fosOrderFile = new FileOutputStream(AppConstants.DATABASE_PATH+"OrderResponse.xml");
			 BufferedOutputStream bossOrderFile = new BufferedOutputStream(fosOrderFile);
			 deleteLogFile(Environment.getExternalStorageDirectory().toString()+"/OrderLog.txt");
			 FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/OrderLog.txt", true);
			 BufferedOutputStream bos = new BufferedOutputStream(fos);
			 
			 bos.write(str.getBytes());
			 
			 byte byt[] = new byte[1024];
			 int noBytes;
			 
			 while((noBytes = bis.read(byt)) != -1)
			 {	 
				 bossOrderFile.write(byt,0,noBytes);
				 bos.write(byt,0,noBytes);
			 }
			 
			 bossOrderFile.flush();
			 bossOrderFile.close();
			 fosOrderFile.close();
			 bos.flush();
			 bos.close();
			 fos.close();
			 bis.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	 }
	
	public static void writeIntoVanstockLog(String str, InputStream is) throws IOException
	{
		try
		{
			BufferedInputStream bis = new BufferedInputStream(is);
			FileOutputStream fosOrderFile = new FileOutputStream(AppConstants.DATABASE_PATH+"OrderResponse.xml");
			BufferedOutputStream bossOrderFile = new BufferedOutputStream(fosOrderFile);
			deleteLogFile(Environment.getExternalStorageDirectory().toString()+"/VanStockLog.txt");
			FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/VanStockLog.txt", true);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			bos.write(str.getBytes());
			
			byte byt[] = new byte[1024];
			int noBytes;
			
			while((noBytes = bis.read(byt)) != -1)
			{	 
				bossOrderFile.write(byt,0,noBytes);
				bos.write(byt,0,noBytes);
			}
			
			bossOrderFile.flush();
			bossOrderFile.close();
			fosOrderFile.close();
			bos.flush();
			bos.close();
			fos.close();
			bis.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public static void writeIntoVanStockIssueLog(String str) throws IOException
	{
		try
		{
			deleteLogFile(Environment.getExternalStorageDirectory().toString()+"/VanStockLog.txt");
			FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/VanStockLog.txt", true);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(str.getBytes());
			
			bos.flush();
			bos.close();
			fos.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void writeIntoDeliveryLog(String str, InputStream is) throws IOException
	{
		try
		{
			 BufferedInputStream bis = new BufferedInputStream(is);
			 FileOutputStream fosOrderFile = new FileOutputStream(AppConstants.DATABASE_PATH+"DeliveryResponse.xml");
			 BufferedOutputStream bossOrderFile = new BufferedOutputStream(fosOrderFile);
			 deleteLogFile(Environment.getExternalStorageDirectory().toString()+"/DeliveryLog.txt");
			 FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/DeliveryLog.txt", true);
			 BufferedOutputStream bos = new BufferedOutputStream(fos);
			 
			 bos.write(str.getBytes());
			 
			 byte byt[] = new byte[1024];
			 int noBytes;
			 
			 while((noBytes = bis.read(byt)) != -1)
			 {	 
				 bossOrderFile.write(byt,0,noBytes);
				 bos.write(byt,0,noBytes);
			 }
			 
			 bossOrderFile.flush();
			 bossOrderFile.close();
			 fosOrderFile.close();
			 bos.flush();
			 bos.close();
			 fos.close();
			 bis.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	 }
	
	public static void writeIntoLog(String str) throws IOException
	{
		try
		{
			deleteLogFile(Environment.getExternalStorageDirectory().toString()+"/OrderLog.txt");
			FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/OrderLog.txt", true);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(str.getBytes());
			
			bos.flush();
			bos.close();
			fos.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void writeIntoCrashLog(String str) throws IOException
	{
		try
		{
			deleteLogFile(Environment.getExternalStorageDirectory().toString()+"/CrashLog.txt");
			FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/CrashLog.txt", true);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(str.getBytes());
			
			bos.flush();
			bos.close();
			fos.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static void writeIntoUploadDataIssueLog(String str) throws IOException
	{
		synchronized (MyApplication.LOG_LOCK){
			try
			{
				deleteLogFile(Environment.getExternalStorageDirectory().toString()+"/UploadIssueLog.txt");
				FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/UploadIssueLog.txt", true);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				bos.write(str.getBytes());

				bos.flush();
				bos.close();
				fos.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void writePreferenceIntoLog(String str) throws IOException
	{
		try
		{
			deleteLogFile(Environment.getExternalStorageDirectory().toString()+"/PreferenceLog.txt");
			FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/PreferenceLog.txt", true);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(str.getBytes());
			
			bos.flush();
			bos.close();
			fos.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void writeIntoSyncLog(String str) throws IOException
	{
		try
		{
			deleteLogFile(Environment.getExternalStorageDirectory().toString()+"/SyncLog.txt");
			FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/SyncLog.txt", true);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(str.getBytes());
			
			bos.flush();
			bos.close();
			fos.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void writeIntoLog(String str, String fileName) throws IOException
	{
		try
		{
			deleteLogFile(Environment.getExternalStorageDirectory().toString()+"/"+fileName+".xml");
			FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/"+fileName+".xml", true);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(str.getBytes());
			
			bos.flush();
			bos.close();
			fos.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	 }
	
	public static void writeIntoLogForDeliveryAgent(String str) throws IOException
	{
		try
		{
			deleteLogFile(Environment.getExternalStorageDirectory().toString()+"/DeliveryLog.txt");
			FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/DeliveryLog.txt", true);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(str.getBytes());
			 
			bos.flush();
			bos.close();
			fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	 }
	
	public static void writeIntoLogForMasterDataFile(String str) throws IOException
	{
		try
		{
			deleteLogFile(Environment.getExternalStorageDirectory().toString()+"/MasterLog.txt");
			FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/MasterLog.txt", true);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(str.getBytes());
			 
			bos.flush();
			bos.close();
			fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	 }
	
	public static void writeErrorLogForDeliveryAgent(String str) throws IOException
	{
		try
		{
			 deleteLogFile(Environment.getExternalStorageDirectory().toString()+"/DeliveryErrorLog.txt");
			 FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/DeliveryErrorLog.txt", true);
			 BufferedOutputStream bos = new BufferedOutputStream(fos);
			 bos.write(str.getBytes());
			 
			 bos.flush();
			 bos.close();
			 fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	 }
	 
	 /**
	 * This method stores InputStream data into a file at specified location
	 * @param is
	 */
	public static void convertResponseToFile(InputStream is, String method) throws IOException
	{
		try
		{
			 BufferedInputStream bis 	= 	new BufferedInputStream(is);
			 FileOutputStream fos 		= 	new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/"+method+"Response.xml");
			 BufferedOutputStream bos 	= 	new BufferedOutputStream(fos);
			 
			 byte byt[] = new byte[1024];
			 int noBytes;
			 
			 while((noBytes = bis.read(byt)) != -1)
				 bos.write(byt,0,noBytes);
			 
			 bos.flush();
			 bos.close();
			 fos.close();
			 bis.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	 }
	
	private void writeIntoLog(String type,String methodName, String strRequest, String success)
	{
		try 
		{
			if(type.equalsIgnoreCase("all"))
			{
				writeIntoLogForDeliveryAgent("\n--------------------------------------------------------");
				writeIntoLogForDeliveryAgent("\n Posting Time: " + new Date().toString());
//				writeIntoLogForDeliveryAgent("\n URL: " + ServiceURLs.MAIN_URL);
//				writeIntoLogForDeliveryAgent("\n SoapAction: " + ServiceURLs.SOAPAction+methodName);
				writeIntoLogForDeliveryAgent("\n--------------------------------------------------------");
				writeIntoLogForDeliveryAgent("\n Request: " + strRequest);
				writeIntoLogForDeliveryAgent("\n Response: " + success);
			}
			else if(type.equalsIgnoreCase("vanstock"))
			{
				writeIntoLogForVanStockLog("\n--------------------------------------------------------");
				writeIntoLogForVanStockLog("\n Posting Time: " + new Date().toString());
//				writeIntoLogForVanStockLog("\n URL: " + ServiceURLs.MAIN_URL);
//				writeIntoLogForVanStockLog("\n SoapAction: " + ServiceURLs.SOAPAction+methodName);
				writeIntoLogForVanStockLog("\n--------------------------------------------------------");
				writeIntoLogForVanStockLog("\n Request: " + strRequest);
				writeIntoLogForVanStockLog("\n Response: " + success);
			}
			else
			{
				writeErrorLogForDeliveryAgent("\n--------------------------------------------------------");
		  		writeErrorLogForDeliveryAgent("\n Posting Time: " + new Date().toString());
//		  		writeErrorLogForDeliveryAgent("\n URL: " + ServiceURLs.MAIN_URL);
//		  		writeErrorLogForDeliveryAgent("\n SoapAction: " + ServiceURLs.SOAPAction+methodName);
		  		writeErrorLogForDeliveryAgent("\n--------------------------------------------------------");
		  		writeErrorLogForDeliveryAgent("\n Request: " + strRequest);
		  		writeErrorLogForDeliveryAgent("\n Response: " + success);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void writeIntoLogForVanStockLog(String str) throws IOException
	{
		try
		{
			deleteLogFile(Environment.getExternalStorageDirectory().toString()+"/VanStockLog.txt");
			FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/VanStockLog.txt", true);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(str.getBytes());
			
			bos.flush();
			bos.close();
			fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void deleteLogFile(String path)
	{
		try
		{
			File file = new File(path);
			if(file.exists())
			{
				long sizeInMB = file.length()/1048576;
				if(sizeInMB >= 5)
					file.delete();
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void writeErrorLog(String str, String path) throws IOException
	{
//		try
//		{
//			 FileOutputStream fos = new FileOutputStream(path, true);
//			 BufferedOutputStream bos = new BufferedOutputStream(fos);
//			 bos.write(str.getBytes());
//			 
//			 bos.flush();
//			 bos.close();
//			 fos.close();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
	 }

	public InputStream getInputStream(String url,String xmlString)
	{
		InputStream is=null;
		try {
			HttpURLConnection connection=(HttpURLConnection)new URL(url).openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setConnectTimeout(TIMEOUT_CONNECT_MILLIS);
			connection.setReadTimeout(TIMEOUT_READ_MILLIS);
			connection.setRequestMethod("POST");
			//If it is soap Url: then you need to add another request property as SOAPAction
			
			connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
			PrintWriter pos = new PrintWriter(connection.getOutputStream());
			pos.write(xmlString);
			pos.flush();
			is=connection.getInputStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return is;
	}
	
}
