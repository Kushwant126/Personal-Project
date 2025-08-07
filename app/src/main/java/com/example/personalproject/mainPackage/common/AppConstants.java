package com.example.personalproject.mainPackage.common;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Environment;
import android.widget.LinearLayout;

import com.example.personalproject.R;
import com.example.personalproject.mainPackage.dataobject.MenuDO;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class AppConstants {


    public static final String NORMAL_PERMISSIONS =
            "android.permission.SYSTEM_ALERT_WINDOW" +
                    "android.permission.WRITE_SETTINGS," +
                    "com.mediatek.permission.CTA_ENABLE_BT," +
                    "com.winit.alrashed.permission.MAPS_RECEIVE," +
                    "com.winit.yaumi.permission.C2D_MESSAGE," +
                    "com.google.android.providers.gsf.permission.READ_GSERVICES," +
                    "android.permission.SDCARD_WRITE," +
                    "com.winit.centurion.permission.MAPS_RECEIVE";

    public static final String PASSCODE = "PASSCODE";
    public static int DIVICE_WIDTH, DIVICE_HEIGHT;
    public static int SURVEY_TYPE = 1;
    public static final int SURVEY_FROM_DASHBOARD = 1;
    public static final int SURVEY_FROM_CUSTOMER = 2;
    public static String APPLICATION_ID = "com.winit.alseer.salesman";
    public static String ACTION_SQLITE_FILE_DOWNLOAD = "com.winit.alseer.salesman.ACTION_SQLITE_FILE_DOWNLOAD";
    public static boolean isServeyCompleted = false;
    public static int PDC_DATE_CONSTANT = 1;
    public static String ALARM_TIME_LOAD_NEW_REQ_1 = "14:00";
    public static String ALARM_TIME_LOAD_NEW_REQ_2 = "15:30";
    public static int PERFECT_STORE_UN_UPLOAD_STATUS = 9;
    public static int ALARM_TIME_REQ_CODE1 = 1;
    public static int ALARM_TIME_REQ_CODE2 = 2;
    public static String ALARM_TIME = "com.winit.alseer.salesman.PARAMETER.ALARM_TIME";
    public static String ACTION_MENUCLICK = "com.winit.alseer.salesman.ACTION.MENUCLICK";
    public static String ACTION_LOGOUT = "com.winit.alseer.salesman.ACTION.LOGOUT";
    public static String ACTION_EOTSYNC = "com.winit.alseer.salesman.ACTION.EOTSYNC";
    public static String ACTION_NETWORK_CHANGE_RECEIVER = "android.net.conn.CONNECTIVITY_CHANGE";
    public static String ACTION_HOUSE_LIST1 = "com.winit.alseer.salesman.ACTION_HOUSE_LIST_NEW1";
    public static String ACTION_HOUSE_LIST = "com.winit.alseer.salesman.ACTION_HOUSE_LIST_NEW";
    public static String ACTION_HOUSE_LIST_NEW = "com.winit.alseer.salesman.ACTION_HOUSE_LIST";
    public static String ACTION_HOUSE_LIST_NEW_LOAD = "com.winit.alseer.salesman.ACTION_HOUSE_LIST_LOAD";
    public static String ACTION_GOTO_TELEORDERS = "com.winit.alseer.salesman.ACTION_GOTO_TELEORDERS";
    public static String ACTION_GOTO_SETTINGS_FINISH = "com.winit.alseer.salesman.ACTION_GOTO_SETTINGS_FINISH";
    public static String ACTION_GOTO_AR = "com.winit.alseer.salesman.ACTION_GOTO_AR";
    public static String ACTION_GOTO_CRLMAIN = "com.winit.alseer.salesman.ACTION_GOTO_CRLMAIN";
    public static String ACTION_GOTO_CRL = "com.winit.alseer.salesman.ACTION_GOTO_CRL";
    public static String ACTION_GOTO_HOME = "com.winit.alseer.salesman.ACTION_GOTO_HOME";
    public static String ACTION_GOTO_JOURNEY = "com.winit.alseer.salesman.ACTION_GOTO_JOURNEY";
    public static String ACTION_GOTO_LOGIN = "com.winit.alseer.salesman.ACTION_GOTO_LOGIN";
    public static String ACTION_GOTO_HOME1 = "com.winit.alseer.salesman.ACTION_GOTO_HOME1";
    public static String ACTION_GOTO_NEXT_DAY_JOURNEY = "com.winit.alseer.salesman.ACTION_GOTO_NEXT_DAY_JOURNEY";
    public static String ACTION_REFRESH_LOAD_REQUEST = "com.winit.alseer.salesman.ACTION_REFRESH_LOAD_REQUEST";
    public static String ACTION_UPLOAD_DATA = "com.winit.alseer.salesman.ACTION_UPLOAD_DATA";
    public static final String DATE_KEY = "DATE_KEY";
    public static String ACTION_NOTIFY_AUDIT = "com.winit.alseer.salesman.ACTION_NOTIFY_AUDIT";
    public static String ACTION_GOTO_ORDER = "com.winit.alseer.salesman.ACTION_GOTO_ORDER";
    public static long TIME_30_DAYS = -2592000000L;
    public static int LOAD_STOCK = 1;
    public static int UNLOAD_STOCK = 2;
    public static int UNLOAD_RETURN_STOCK = 3;
    public static int UNLOAD_RECONCILED_STOCK = 4;
    public static String baskinLogoPath = "";
    public static String CategoryIconsPath;
    public static String productCatalogPath;
    public static String LastItemClicked = "";
    public static String LastItemLevel = "";
    public boolean isDayStarted = false;
    public static int STORE_SIGN_START = 1;
    public static int STORE_SIGN_END = 2;
    public static int SALES_SIGN_START = 3;
    public static int SALES_SIGN_END = 4;
    public static String DATABASE_PATH = "";
    public static String DATABASE_NAME = "salesman.sqlite";
    public static String DATABASE_SALES_HISTORY_NAME = "saleshistory.sqlite";
    public static String DATABASE_NAME_CPS = "salesmancpsdetails.sqlite";

    public static String PREFS_NAME = "krprefs.xml";
    public static String ORDER_LOG = "OrderLog.txt";
    public static String PAYMENT_LOG = "PaymentLog.txt";
    public static String DELIVERY_LOG = "DeliveryLog.txt";
    public static String DELIVERY_ERROR_LOG = "DeliveryErrorLog.txt";
    public static String SYNC_LOG = "SyncLog.txt";
    public static String DEFAULT_TIME_LOG = "1900-01-01";
    public static String PAYMENT_ERROR_MSG = "";
    public static int REC_ORDER_MONTHS = 3;
    public static String VARIANCE_LOG = "VanStockLog.txt";
    public static String CRASH_LOG = "CrashLog.txt";
    public static String UPLOAD_ISSUE_LOG = "UploadIssueLog.txt";
    public static String PREFERENCE_LOG = "PreferenceLog.txt";

    public static final String ORDER_BY = "";
    public static final String IMAGE_TYPE_PAYMENT = "PAYMENT";

    public static final String NUMBER_OF_DECIMALS = "NUMBER_OF_DECIMALS";
    public static int NUMBER_OF_DECIMALS_TO_ROUND = 2;

    public static final int DIRECT = 100, PENDING_INVOICE = 200;

    public static String ChequeCheckListOptions = "Cheque � Verification Check List";
    public static final String UAE_SALES_ORG_CODE = "103";
    public static final String SETTING_IMEOPTION = "IMEICategory";

    public static final String CREDIT_CARD_VAS = "Credit Card Service Charge";
    public static final String CREDIT_CARD_VAS_INV = "CC_VAS_INV";
    public static final String VAS_INV_TYPE = "VAS";

    public static String RETRUN_UNLOAD = "1";
    public static String DEFAULT_TIME = "1900-01-01T12:00:00";
    public static String DEFAULT_TIME_BACKEND = "1900-01-01T00:00:00";
    public static String FCM_SERVER_KEY = "AAAA6l7Mx2Q:APA91bHgrkwiYOyCwXPp3S0Z3_S-NUcUAhE9-miD5L-ZFDHjvyKLCaQVBFsQ4jxHwmY8BeFxtOe0-1pXUTMATs_GBSWbvy98L5j7Y0h09Xw-Z2Rd9mpjx2KurYH0nqMxGiTyUXeXB1iM";
    public static Typeface SanFranciscoDisplay_Regular, SanFranciscoDisplay_Medium, SanFranciscoDisplay_Semibold, SanFranciscoDisplay_Bold;
    public static Typeface montserrat_black, montserrat_light,montserrat_extralight, montserrat_thin, montserrat_extrabold;


    //for store close
    public static final String Store_close = "STORE CLOSE";

    public static final String langEnglish = "English";
    public static final String langArabic = "Arabic";

    public static final String TIME_NEED_TO_SPEND_IN_A_STORE = "10";

    public static final int TYPE_VANINVENTORY = 200;
    public static final int TYPE_WAREHOUSEINVENTORY = 300;
    public static final int Store_Check = 1000;
    public static final int Capture_Inventory = 1001;
    public static final int STATUS_TYPE_CUSTOMER_INFO = 1001;
    public static final int STATUS_TYPE_CAPTURE_ASSET = 1010;
    public static final int STATUS_TYPE_STORE_CHECK = 1002;

    public static final int ORDER_PUSH_LIMIT = 15;
    public static final int MAX_ORDER_PUSH_LIMIT = 2;
    public static final int SYNC_COUNT = 400;
    public static int Activity_Progress_count = 0;
    public static int Activity_Completed = 0;

    public static final int PRINT_TYPE_WITHOUT_INV = -100;
    public static final int PRINT_TYPE_WITH_INV = 100;
    public static final int PRINT_TYPE_FOUR_INCH = 830;
    public static final int PRINT_TYPE_THREE_INCH = 560;
    public static boolean isFromVan = false;
    public static final float FILES_Amount = 0.12f;
    public static final float FILES_Amount_OMR = 0.012f;

    public static String ROUTE_TYPE_AMBIENT = "Ambient";
    public static String ROUTE_TYPE_FROZEN = "Frozen";

    public static String SALES_ORG_SAUDI = "Saudi";
    public static String SALES_ORG_BAHRAIN = "Bahrain";
    public static String SALES_ORG_JORDAN = "Jordan";
    public static String SALES_ORG_KUWAIT = "Kuwait";
    public static String SALES_ORG_QATAR = "Qatar";

    public static String SALES_ORG_CODE_SAUDI_ = "1";
    public static String SALES_ORG_CODE_BAHRAIN = "2";
    public static String SALES_ORG_CODE_QATAR = "3";
    public static String SALES_ORG_CODE_JORDAN = "4";
    public static String SALES_ORG_CODE_KUWAIT = "5";
    public static String SALES_ORG_CODE_IRAQ = "airq";

    public static final String PRICE_CODE_BASE_PRICE = "1";
    public static final String PRICE_CODE_PROMO_PRICE = "2";
    public static final String PRICE_CODE_CUSTOMER_PRICE = "3";


    //for Journey plan
    public static String ALL = "ALL";
    public static String DASHBOARD = "Dashboard";
    public static final String PENDING = "PENDING";
    public static final String VISITED = "VISITED";
    public static final String DELIVERED = "DELIVERED";
    public static final String MISSED = "MISSED";
    public static final String ZERO_SALES_TAB = "ZERO SALES";

    public static final String UNPLANNEDVISIT = "UNPLANNED VISIT";

    public static String PROMOTION_ITEM_CODES = "T551";
    public static String Planogram_ISBeforeCaptureAllow = "IS_Planogram_Before_Capture_Allow";

    /********************For user Dashborad****************/

    public static String Code_StoreCheck = "KPI_SC";
    public static String Code_Tasks = "KPI_TS";
    public static String Code_Assets = "KPI_AS";
    public static String Code_Collections = "KPI_CC";
    public static String Code_SalesOrder = "KPI_SO";
    public static String Code_CaptureAsset = "KPI_CA";
    public static String Code_CaptureDamageOrder = "KPI_DO";
    public static String Code_OrderSummary = "KPI_OS";
    public static String Code_CustomerPayment = "KPI_CP";
    public static String Code_Planogram = "KPI_PP";
    public static String Code_ReturnOrder = "KPI_RO";
    public static String Code_VisibilityDisplay = "KPI_VD";
    public static String Code_MissedOrder = "KPI_MO";
    public static String Code_AdvancedOrder = "KPI_AO";
    public static String Code_MarketReport = "KPI_MR";
    public static String Code_Sampling = "KPI_SR";
    public static String Code_Quotation = "KPI_QR";
    public static String Code_Liquidation = "KPI_LL";
    public static String Code_CustomerInfo = "KPI_CI";
    public static String Code_Expiry_Check = "KPI_EC";
    public static String Code_Activation = "KPI_AA";
    public static String Code_Delivery_Check = "KPI_DC";
    public static String Code_Checkout = "KPI_CO";
    public static String Code_OTHER = "KPI_OO";
    public static String Code_Replacement_Order = "KPI_RR";
    public static String Code_7DaysAdvance_Load = "KPI_7AL";
    public static String Code_SOS = "KPI_SOS";
    public static String Status_SOA = "SOA";
    public static String Status_SOS = "SOS";
    public static String Type_SOS = "SOS";
    public static String Type_COMPETITOR = "COMP";
    public static String Status_Planogram = "Planogram Check";
	public static final int STATUS_TYPE_SURVEYSUMMARY 	= 1008;

    public static String Name_StoreCheck = "Store Check";
    public static String Name_Tasks = "Tasks";
    public static String Name_Assets = "Assets";
    public static String Name_Collections = "Collection";
    public static String Name_SalesOrder = "Sales Order";
    public static String Name_OrderSummary = "Order Summary";
    public static String Name_CustomerPayment = "Customer Payment";
    public static String Name_Planogram = "Planogram";
    public static String Name_ReturnOrder = "Return Order";
    public static String Name_VisibilityDisplay = "Visibility/Display";
    public static String Name_MissedOrder = "Missed Order";
    public static String Name_AdvancedOrder = "Advanced Order";
    public static String Name_MarketReport = "Market Report";
    public static String Name_Sampling = "Sampling";
    public static String Name_Quotation = "Quotation";
    public static String Name_SavedOrder = "Saved Order";
    public static String Name_Liquidation = "Liquidation";
    public static String Name_CustomerInfo = "CustomerInfo";
    public static String Name_Expiry_Check = "Expiry Check";
    public static String Name_Activation = "Activations";
    public static String Name_Delivery_Check = "Delivery Check";
    public static String Name_Checkout = "Check Out";
    public static String Name_Capture_Asset = "Capture Asset";
    public static String Name_Replacement_Order = "Replacement Order";
    public static String Name_7DaysAdvanceLoad = "7 Days Advance Load Request";
    public static String Name_7DaysAdvanceLoad_OS = "Advance Load Request";


    public static String Action_CheckIn = "CheckInOption";
    public static String Action_CheckIn_Skip = "CheckInOption_Skip";
    public static String Type_StoreCheck = "StoreCheck";
    public static String Type_CaptureAsset = "CaptureAsset";
    public static String Type_Survey = "Survey";
    public static String Type_Task = "Task";
    public static String Type_Assets = "Assets";
    public static String Type_Collections = "Collection";
    public static String Type_CrossMerchandising = "Cross_Merch";
    public static String Type_SalesOrder = "SalesOrder";
    public static String Type_PresalesOrder = "Presales";
    public static String Type_ReturnOrder = "ReturnOrder";
    public static String Type_MissedOrder = "MissedOrder";
    public static String Type_AdvancedOrder = "AdvancedOrder";
    public static String Type_Sampling = "Sampling";
    public static String Type_Quotation = "Quotation";
    public static String Type_SavedOrder = "SavedOrder";
    public static String Type_Liquidification = "Liquidification";
    public static String Type_CustomerInfo = "CustomerInfo";
    public static String Type_Expiry_Check = "ExpiryCheck";
    public static String Type_Activation = "Activation";
    public static String Type_Delivery_Check = "DeliveryCheck";
    public static String Type_Checkout = "Check Out";
    public static String Type_Order_Summary = "Order Summary";
    public static String Type_Customer_Payment = "Payment Summary";
    public static String Type_Code_Visibility = "Code Visibilty";
    public static String Type_Code_Task = "Code Task";
    public static String Type_other = "Other";
    public static String Type_Replcement_Order = "Replacement Order";

    //========startDate======
    public static String Status_SYNC = "SYNC";
    public static String Status_DATA_CHECK = "DATA CHECK";
    public static String Status_INTERNET_SPEED = "INTERNET SPEED";
    public static String Status_BATTERY_CHECK = "BATTERY CHECK";
    public static String Type_Attendance = "Attendance";

    public static String Type_CollectionEndorsement = "Collection Endorsement";
    public static String Type_CallEndorsement = "Call Endorsement";
    public static String Type_LoadEndorsement = "Load Endorsement";
    public static String Type_OrderSubmissionEndorsement = "Order Endorsement";
    public static String Type_SupervisorEndorsement = "Supervisor Endorsement";

    public static String LOAD_TYPE_EMERGENCY = "Emergency";
    public static String LOAD_TYPE_REGULAR = "Normal";
    public static String LOAD_TYPE_RECOMENDED = "Recommended";

    public static String OTHER_UOM_BELOW_SELLING_UOM = "OTHER_UOM_BELOW_SELLING_UOM";

    /*for view pager tabs*/


    /*
     * For Duplicate Orders Test
     */
    public static final int MAX_DUPLICATE_ORDER_PUSH_LIMIT = 5;
    public static final int DUPLICATE_ORDER_PUSH_STATUS_1 = -100;
    public static final int DUPLICATE_ORDER_PUSH_STATUS_2 = -1;
    public static final int STATUS_TYPE_PLANOGRAM = 1004;
    public static final int STATUS_TYPE_VISIBILITY = 1003;
    public static final int STATUS_TYPE_MARKET_REPORT = 1006;
    public static final int STATUS_TYPE_BDA = 1005;
    public static final int STATUS_TYPE_SURVEY = 1007;

    public static final String BUNDLED_PROMOTIONS = "'ZA05','ZB05','ZC05'";
    public static final String FOC_PROMOTIONS = "'ZA11','ZB11','ZC11'";

    public static final String THINGS_TO_FOCUS_EVERY_LOGIN = "EveryLogin";
    public static final String THINGS_TO_FOCUS_FIRST_TIME_LOGIN = "OnlyAtFirstLogin";

    public static String Type_Visibility = "Visibility";
    public static String Type_MarketReport = "MarketReport";
    public static String Type_Planogram = "Planogram";
    public static String Type_BDA = "BDA";
    public static String Type_SURVEY = "Survey";


    public static String SUPERVISOR_LOGIN = "SPV0001";
    public static String SUPERVISOR_PASSWORD = "password";

    public static String SequenceNeedtoFollwed = "password";

    public static String SOS_MANDATORY_CATEGORIES = "SOS_MANDATORY_CATEGORIES";
    public static String DATABASE_PATH_CPS = "";
    public static String Collection_VOID = "Collection_Void";


    /*
     * Reasons
     */
    public static String ZERO_SALES = "Zero Sales";
    public static String Invoice_void = "Order void";
    public static String Remote_Collection = "Remote Collection";
    public static String FORCE_CHECKIN = "Force Check In";
    public static String EarlyPaymentDisc = "EarlyPaymentDisc";

    public static String TRAY_ITEMCODE = "30-0101";

    public static String FOC = "FOC";
    public static String DAMAGED_RETURN = "DAMAGED RETURN";
    public static String DAMAGED_ORDER = "DAMAGED ORDER";
    public static String Damage = "Damage";
    public static String Expiry = "Expiry";
    public static String Returns = "Returns";
    public static String SALES = "Sales";

    public static final String REASON_PaymentTerm = "PaymentTerm";
    public static final String REASON_ProspectModeofdelivery = "ProspectModeofdelivery";
    public static final String REASON_ProspectVisitfrequency = "ProspectVisitfrequency";
    public static final String REASON_OutletOffDays = "ProspectModeofdelivery";
    public static final String REASON_WeekDays = "ProspectModeofdelivery";

//	public static String Damage = "Damage";
//	public static String Expiry = "Expiry";
//	public static String Returns = "Returns";

    public static final int PRINT_TYPE_WITH_PRICE = 1;
    public static final int PRINT_TYPE_WITHOUT_PRICE = 2;

    /*
     * Message Types
     */

    public static final int MESSAGE_TYPE_AUDIT = 20;
    public static final int MESSAGE_TYPE_EOT = 50;
    public static final int MESSAGE_TYPE_JP_DIVERT = 60;
    public static final int MESSAGE_TYPE_COLLECTION_SETTELEMENT = 13;

    public static final int USER_PRESELER = 100;
    public static final int USER_VAN_SALES = 200;
    public static final int USER_MERCHANDISER = 300;

    public static String Device_Display_Width = Preference.DEVICE_DISPLAY_WIDTH;
    public static String Device_Display_Height = Preference.DEVICE_DISPLAY_HEIGHT;
    public static int DEVICE_DISPLAY_WIDTH_DEFAULT = 800;
    public static int DEVICE_DISPLAY_HEIGHT_DEFAULT = 1216;

    public static int CAPTURE_IMAGE_WIDTH = 640;
    public static int CAPTURE_IMAGE_HEIGHT = 360;

    public static final String GRVD = "GRVD";
    public static final String GRVG = "GRVG";

    public static final int TRX_TYPE_ORDER = 1;
    public static final int TRX_TYPE_PAYMENT = 2;
    public static final int TRX_TYPE_ADV_LOAD = 3;
    public static final int TRX_TYPE_ORDER_PAYMENT = 3;
    public static final int TRX_TYPE_SURVEY = 4;


    public static final String OVERDUE_TYPE_D = "D";  //  # Days from the date of invoice
    public static final String OVERDUE_TYPE_W = "W"; // # Weeks from the date of invoice
    public static final String OVERDUE_TYPE_DBL = "DBL"; //  # Days from the date of invoice
    public static final String OVERDUE_TYPE_ADVANCE = "ADVANCE"; //=====0
    public static final String OVERDUE_TYPE_CAD = "CAD"; //=====0
    public static final String OVERDUE_TYPE_CASH = "CASH"; //=====0
    public static final String OVERDUE_TYPE_CM = "CM";//  # Current Month END  + date of invoice
    public static final String OVERDUE_TYPE_FULLPAY = "FULLPAY"; //=====0
    public static final String OVERDUE_TYPE_OTHER = "OTHER"; //=====0
    public static final String OVERDUE_TYPE_Prepayment = "Prepayment"; //=====0


    public static final String ONE_MONTH_DATA = "ONE MONTH DATA";
    public static String JOURNEY_CALL = "Journey Call";
    public static String COLLECTIONS = "Collection";
    public static String INVOICES = "Invoices";
    public static String CREDITNOTE = "CreditNote";
    public static String STORECHECK = "D-Check";
    public static String PreSalesOrder = "Presales";
    public static String EOT = "EOT";

    public static String Type_Merchandiser = "Merchandiser";
    public static final int CAMERA_PIC_REQUEST_FOR_MERCHANDIZECAM = 137;

	/*public static String presellerOptionGT[]   = {"Journey Plan","Load Management","My Customers",
		"AR Collection","Stock Inventory","Stock Verification", 
		"Add New Customer","Order Summary", "Payment Summary", 
		"Product Catalog","Today`s Dashboard","EOT", 
		"New Launches","Settings",	"Asset Request",
		"Competitor","Transfer", "About Application",
		"NotifiCations", "Logout","footer"};*/

    public static String CUSTOMER_STATEMENT_SALES_INVOICE = "Sales Order";
    public static String CUSTOMER_STATEMENT_CREDIT_NOTE = "Credit Note";
    public static String CUSTOMER_STATEMENT_PAYMENT = "Payment";
    public static String CUSTOMER_STATEMENT_COLLECTION = "Collection";

    public static int ASSET_STATUS_ALLOCATE = 1;
    public static int ASSET_STATUS_ALLOCATED = 2;
    public static int ASSET_STATUS_RETURN = 3;
    public static int ASSET_STATUS_DELETE = 4;
    public static int ASSET_STATUS_UNLOAD = 0;
    //=================Newly Added====================
    public static int ASSET_STATUS_SWITCHED = 5;
    public static int ASSET_STATUS_SERVICE_REQUESTED = 6;
    public static int ASSET_STATUS_TRANSFER = 7;

    public static int ASSET_STATUS_PENDING = 0;
    public static int ASSET_STATUS_FIRST_LEVEL_APPROVAL = 1;
    public static int ASSET_STATUS_SECOND_LEVEL_APPROVAL = 2;
    public static int ASSET_STATUS_APPROVED_FINAL = 3;

    public static String NO_ASSET = "NO ASSET";
    public static String LOCATION_PULL_OUT = "Location Pull Out";

    public static String ASSET_TYPE_PERMANENT = "PERMANENT";

    public static final String REASON_TYPE_ASSET_CHECK_CONDITION = "Asset_Check_Condition";
    public static final String REASON_TYPE_ASSET_SERVICE_TYPE = "AssetServiceType";

    public static final String ASSSET_GENERAL = "General";
    public static final String ASSSET_TYPE_NEW_REQUEST = "New Asset";
    public static final String ASSSET_TYPE_SERVICE_REQUEST = "Service / Maintenance Request";
    public static final String ASSSET_TYPE_SWITCH_REQUEST = "Switch Request";
    public static final String ASSSET_TYPE_TRANSFER_REQUEST = "Transfer Request";

    public static final String ASSET_EXEC_TYPE_ASSET_CHECK = "Asset Check";
    public static final String ASSET_EXEC_TYPE_SERVICE_REQUEST = "Service Request";

    public static String Asset_Type_Of_Issue = "Asset_Type_Of_Issue";
    public static String Asset_Priroity = "Asset_Priroity";
    public static String Asset_Model = "Asset_Model";

    public static final String UserType_MT = "ModernTrade";
    public static final String UserType_GT = "GeneralTrade";
    public static final String UserType_TT = "TradtionalTrade";
    public static final String UserType_FS = "FoodService";
    public static final String UserType_WS = "WholeSales";
    public static final String PRESALES_COLLECTIONS = "PRESALES_COLLECTIONS";


    public static String Product_Catelog = "ProductCatelog";

    public static String OFFICE_CHECKIN = "OFFICE_CHECKIN";
    public static String OFFICE_CHECKOUT = "OFFICE_CHECKOUT";
    public static String DEPOT_CHECKIN = "DEPOT_CHECKIN";
    public static String DEPOT_CHECKOUT = "DEPOT_CHECKOUT";

    public static String NO_PRICE_ITEMS = "T551,902";


    public static final String ChatGroup = "ChatGroup";
    public static final String IsFromNotification = "IsFromNotification";
    public static final String SupervisorUser = "SupervisorUser";


    public static String CollectorSalesMenuOptions[] = {
            "My Customers",
            "Blocked Customers",
            "Execution Summary",
            "Others",
            "Logout",
            "Footer"
    };
    public static int CollectorMenuOptionIcons[] = {
            R.drawable.mycustomer_icon,
            R.drawable.mycustomer_icon,
            R.drawable.orders_summary_icon,
            R.drawable.about_application_icon,
            R.drawable.logout_menu_icon,
            R.drawable.footer_new
    };


    /* ==============================VanSales User Menu Options ====================================*/

    public static String vanSalesMenuOptions[] = {
            "Today's Journey Plan",
            "Next Day's Journey Plan",
            "My Customers",
            "Blocked Customers",
            "Load Management",
            "Execution Summary",
            "Add New Customer",
            /*"Commissions",*/
            /*"Price List",*/
//			"Product Catalog",
//			"New Launches",
//			"Chat",
//			"Things To Focus",

//			"EOT",
            "End Journey",
//			"Reports",
//			"Fuel Fill",
//			"Car Wash Maintenance",
            "Others",
//			"Gate Keeper",
//			"Endorsement",
//			"Start Journey Request",
//			"Collection Deposite",
//			"Available PassCode",
//			"Load endorsement",
//			"R Vs B capacity",
            "Logout",
            "Footer"

//			,"Chat"
    };
    public static int vanSalesMenuOptionIcons[] = {
            R.drawable.journey_plan_icon,
            R.drawable.next_journey_plan_icon,
            R.drawable.mycustomer_icon,
            R.drawable.mycustomer_icon,
            R.drawable.load_management_icon,
            R.drawable.orders_summary_icon,
            R.drawable.add_necustomer_icon,
//			R.drawable.product_catalogue_new,
//			R.drawable.new_launches_new,
//			R.drawable.messege,
//			R.drawable.new_launches_new,
//			R.drawable.eot_icon,
            R.drawable.eot_icon,
//			R.drawable.orderlistlogo,
//			R.drawable.orderlistlogo,
//			R.drawable.orderlistlogo,
            R.drawable.about_application_icon,
//			R.drawable.mycustomer_icon,
//			R.drawable.journey_plan_icon,
//			R.drawable.orderlistlogo,
//			R.drawable.new_launches_new,
//			R.drawable.new_launches_new,
//			R.drawable.new_launches_new,
//			R.drawable.eot_icon,

            R.drawable.logout_menu_icon,
            R.drawable.footer_new
//			,R.drawable.chat
    };

    /*==================================== Customer Menu Options ===============================*/

    public static String customerMenuOptions[] = {"Customer Dashboard",
//			"Agreement/Contract",
//			"Rebates", 
            "Execution Summary",
            "Active Promotions",
            "Active SP",
            /*"Price list",*/
//			"Product catalog",
            "Others",
            "Check out",
//			"",
            "Footer"
    };
    public static int customerMenuOptionIcons[] = {R.drawable.today_dashboard_icon,
//			R.drawable.arcollection_icon,
//			R.drawable.product_catalog_icon,
            R.drawable.orders_summary_icon,
            R.drawable.active_promotions,
            R.drawable.active_promotions,
            /*	R.drawable.orderlistlogo,*/
//			R.drawable.product_catalogue_new,
            R.drawable.about_application_icon,
            R.drawable.menu_checkout_icon,
//			R.drawable.active_promotions,
            R.drawable.footer_new
    };



    /*=========================================== Presales Menu Options=====================================================*/

    public static String presellerMenuOption[] = {"Journey Plan",
//			"Next Day Journey Plan",
//			"My Customers",
////			"Warehouse Stock",
//			"Execution Summary",
//			"Add New Customer",
//			/*"Product Catalog",
//			"New Launches",*/
//			"EOT",
//			"End Journey",
            "Others",
            "Sruvey",
            "Endorsement",
            "Logout",
            "Footer",
//			"Chat"
    };


    public static int presellerMenuOptionIcon[] = {R.drawable.journey_plan_icon,
//			R.drawable.next_journey_plan_icon,
//			R.drawable.mycustomer_icon,
////			R.drawable.load_management_icon,
//			R.drawable.orders_summary_icon,
//			R.drawable.add_necustomer_icon,
//			/*R.drawable.product_catalogue_new,
//			R.drawable.new_launches_new,*/
//			R.drawable.eot_icon,
//			R.drawable.eot_icon,
            R.drawable.about_application_icon,
            R.drawable.about_application_icon,
            R.drawable.about_application_icon,
            R.drawable.logout_menu_icon,
            R.drawable.footer_new,
//			R.drawable.chat
    };


    /*********************************************Pre Sales Customer Menu Options********************************************/

    public static String presellerCheckedInMenuOption[] = {"Customer Dashboard",
            /*"Agreement/Contract",
            "Rebates",
            "Warehouse Stock", */
            "Execution Summary",
            /*"Price list",
            "Product catalog",*/
            "Others",
            "Check out",
            "Footer"
    };
    public static int presellerCheckedInMenuOptionIcon[] = {R.drawable.today_dashboard_icon,
            /*R.drawable.arcollection_icon,
            R.drawable.product_catalog_icon,
            R.drawable.load_management_icon,*/
            R.drawable.orders_summary_icon,
            /*R.drawable.orderlistlogo,
            R.drawable.product_catalogue_new,*/
            R.drawable.about_application_icon,
            R.drawable.menu_checkout_icon,
            R.drawable.footer_new
    };
    /*======================================================================================================================*/
    public static String vanSalesMenuOption[] = {"Journey Plan", "Next Day Journey Plan", "My Customers", "Load Management", "Execution Summary",
            "Add New Customers", "Collection Settlement", "Messaging", "EOT", "End Journey", "Others", "Things to Focus", "Auditor Login", "Audit Receipts", "Office Check-In/Out", "Logout", "footer"};

    public static int vanSalesMenuOptionIcon[] = {R.drawable.journey_plan_icon, R.drawable.next_journey_plan_icon, R.drawable.mycustomer_icon, R.drawable.load_management_icon, R.drawable.orders_summary_icon,
            R.drawable.add_necustomer_icon, R.drawable.order_summary, R.drawable.messege, R.drawable.eot_icon, R.drawable.eot_icon, R.drawable.about_application_icon, R.drawable.thingstofocus_icon, R.drawable.audit, R.drawable.audit, R.drawable.office_checkin, R.drawable.logout_menu_icon, R.drawable.footer_new};

    public static String merchandiserMenuOption[] = {"My Customers", "Execution Summary", "Others", "Logout", "footer"};

    public static int merchandiserMenuOptionIcon[] = {R.drawable.mycustomer_icon, R.drawable.paymentsummary_icon, R.drawable.about_application_icon, R.drawable.logout_menu_icon, R.drawable.footer_new};
/*

	public static String merchandiserMenuOption[]   = {"Journey Plan","Next Day Journey Plan","My Customers","Others","Things to Focus","Office Check-In/Out","Logout","footer"};

	public static int merchandiserMenuOptionIcon[]   = {R.drawable.journey_plan_icon,R.drawable.next_journey_plan_icon,R.drawable.mycustomer_icon,R.drawable.about_application_icon,R.drawable.thingstofocus_icon,R.drawable.office_checkin,R.drawable.logout_menu_icon,R.drawable.footer_new};
*/

    /******************************************************************************************************/
    //public static String presellerMenuOption[]   = {"Journey Plan","Next Day Journey Plan", "My Customers","Execution Summary","Add New Customers","Collection Settlement","Messaging",
    //												"EOT","Others","Things to Focus","Office Check-In/Out","Logout","footer"};

    //public static int presellerMenuOptionIcon[]   = {R.drawable.journey_plan_icon,R.drawable.next_journey_plan_icon,R.drawable.mycustomer_icon,R.drawable.orders_summary_icon,R.drawable.add_necustomer_icon,
    //	/*R.drawable.eot_icon,*/R.drawable.order_summary,R.drawable.messege,R.drawable.eot_icon,R.drawable.about_application_icon,R.drawable.thingstofocus_icon,R.drawable.office_checkin,R.drawable.logout_menu_icon,R.drawable.footer_new};

    //public static String presellerCheckedInMenuOption[]   = {"Customer Dashboard", "Execution Summary",/* "Product Catalog",*/"Others","Checkout","Add New Customers","Things to Focus"};

    //public static int presellerCheckedInMenuOptionIcon[]   = {R.drawable.next_journey_plan_icon, R.drawable.arcollection_icon,/*R.drawable.product_catalog_icon,*/
    //	R.drawable.about_application_icon,R.drawable.order_summary,R.drawable.add_necustomer_icon,R.drawable.thingstofocus_icon};

    public static String vanSalesCheckedInOption[] = {"Customer Dashboard", "Load Management", "Execution Summary", "Collection Settlement",
            "Checkout", "Add New Customers", "Others", "Things to Focus"};

    public static int vanSalesCheckedInOptionIcon[] = {R.drawable.journey_plan_icon, R.drawable.load_management_icon, R.drawable.arcollection_icon, R.drawable.product_catalog_icon,
            R.drawable.order_summary, R.drawable.add_necustomer_icon, R.drawable.about_application_icon, R.drawable.thingstofocus_icon};

    public static String merchandiserCheckedInOption[] = {"Customer Dashboard",
            "Checkout", "Others", "Things to Focus"};


    public static int merchandiserCheckedInOptionIcon[] = {R.drawable.journey_plan_icon,
            R.drawable.order_summary, R.drawable.about_application_icon, R.drawable.arcollection_icon};

	/*	public static String presellerExecutionSummaryOption[]   = {"Order Summary","Payment Summary","Survey","Competitive Execution",
		"Asset Activities","Order Trace"};

	public static int presellerExecutionSummaryOptionIcon[]   = {R.drawable.order_summary,R.drawable.paymentsummary_icon,R.drawable.invontery,R.drawable.competitor_icon,
		R.drawable.assets_request_icon,R.drawable.paymentsummary_icon};*/
    /******************************************************************************************************/

    public static String loadMangementOption[] = {"Van Stock",/*,"Return Stock","Receive/Verify Stock","Today and next 3 days load","Unload","Audit Info",*/"Audit Return"
//			"Current Stock Report",
            , "Expiry Returns",
            "Unload Stock"
    };
//	public static String loadMangementOption[]   = {"Van Stock"/*,"Return Stock","Receive/Verify Stock","Today and next 3 days load","Unload"*/,"Audit Info","Audit Return"};

    public static int loadMangementOptionIcon[] = {R.drawable.salable_van_stock_icon,/*R.drawable.returnstock_stock,R.drawable.stockverification_icon,R.drawable.dailyload_icon,R.drawable.order_summary,*/R.drawable.order_summary
            , R.drawable.order_summary
            , R.drawable.load_management_icon};
//	public static int loadMangementOptionIcon[]   = {R.drawable.salable_van_stock_icon,/*R.drawable.returnstock_stock,R.drawable.stockverification_icon,R.drawable.dailyload_icon,*/R.drawable.order_summary,R.drawable.order_summary};

    public static String othersOption[] = {/*"Product Catalog",*/"Settings"/*,"Capture Competitor"*/, "About Application"};

    public static int othersOptionIcon[] = {/*R.drawable.product_catalog_icon,*/R.drawable.settings_icon/*,R.drawable.competitor_icon*/, R.drawable.about};

    public static String executionSummaryOption[] = {"Order Summary", "Payment Summary"/*,"Competitive Execution"*//*,"Order Trace"*/, "Log Report"/*,"Score Card"*/, "Return Summary"};

    public static int executionSummaryOptionIcon[] = {R.drawable.order_summary, R.drawable.paymentsummary_icon/*,R.drawable.invontery*//*,R.drawable.competitor_icon*/, R.drawable.assets_request_icon, R.drawable.paymentsummary_icon, R.drawable.assets_request_icon, R.drawable.paymentsummary_icon};


    public static String checkin_executionSummaryOption[] = {"Order Summary", "Payment Summary"/*,"Competitive Execution"*//*,"Order Trace"*/, "Log Report"/*,"Score Card"*/, "Return Summary"};

    public static int checkin_executionSummaryOptionIcon[] = {R.drawable.order_summary, R.drawable.paymentsummary_icon/*,R.drawable.invontery*//*,R.drawable.competitor_icon*/, R.drawable.assets_request_icon, R.drawable.paymentsummary_icon, R.drawable.assets_request_icon, R.drawable.paymentsummary_icon, R.drawable.salable_van_stock_icon};




	/*public static String checkinexecutionSummaryOption[]   = {"Order Summary","Payment Summary","Survey","Competitive Execution",
		"Asset Activities"};

	public static int checkinexecutionSummaryOptionLoogs[]   = {R.drawable.order_summary,R.drawable.paymentsummary_icon,R.drawable.invontery,R.drawable.competitor_icon,R.drawable.assets_request_icon};*/

    /****************************************************************************************/


	/*	
	public static int presellerOptionLoogsGT[] = {R.drawable.journey_plan_icon,R.drawable.load_management_icon,R.drawable.mycustomer_icon,
		R.drawable.arcollection_icon,R.drawable.invontery, R.drawable.stockverification_icon,
		R.drawable.add_necustomer_icon,R.drawable.orders_summary_icon,R.drawable.paymentsummary_icon,
		R.drawable.product_catalog_icon,R.drawable.order_summary, R.drawable.eot_icon,
		R.drawable.new_launch_icon, R.drawable.settings_icon,R.drawable.assets_request_icon,
		R.drawable.competitor_icon,R.drawable.transfer_in,R.drawable.about_application_icon,
		R.drawable.about,R.drawable.logout_menu_icon,R.drawable.footer_new};*/

    public static int OLD_COIN_BOX_SCAN_REQUEST_CODE = 100000000;
    public static int NEW_COIN_BOX_SCAN_REQUEST_CODE = 200;
    public static int VM_PHOTOGRAPH_REQUEST_CODE = 300;
    public static String RETURN_REASONS = "Return request reason";
    public static int REQUEST_CODE = 0;
    public static String VendingMachineName = "";
    public static int invoiceNo = 10;
    public static int customerSiteno = 10;
    public static final String ITEM_TYPE_ORDER = "O";
    public static final String ITEM_TYPE_REPLACE = "R";
    public static final String ITEM_TYPE_DEAL_ITEM = "Z";
    public static final String ITEM_TYPE_PROMO = "F";

    public static boolean isRecomendedChanged = false;
    public static String SUB_CHANEL = "Grocery_123";

    public static boolean isTaskCompleted = false;

    public static boolean ENABLE_FIELDS = true;

    public static final String SALESMAN_PRESELLER = "PreSales";
    public static final String PRESELLER = "Preseller";
    public static final String SALESMAN_VAN_SALES = "VanSales";
    public static final String SALESMAN_MERCHANDISER = "Merchandiser";
    public static final String SALESMAN_AUDITOR = "Auditor";
    public static final String SALESMAN_COLLECTOR = "Collector";

    public static final String MUST_HAVE = "Must Have";
    public static final String NEW_LAUNCHES = "New Launches";
    public static final String FAVOURRITE = "Favourite";

    public static final String NEW_LAUNCH = "NEW_LAUNCH";
    public static final String THINGS_TO_FOCUS = "THINGS_TO_FOCUS";

    public static final int SALES_ORDER_TYPE = 1, ADVANCE_ORDER_TYPE = 0;
    public static final String PROMO_TYPE_RANGE = "RP";


    public static String CURRENCY_UAE = "AED";
    public static String CURRENCY_SAUDI = "SAR";
    public static String CURRENCY_OMAN = "OMR";
    public static String CURRENCY_USD = "USD";

    public static String KPI_APPROVAL_REQD = "KPI_APPROVAL_REQD";

    public static String PAYMENT_TYPE_STC = "STC";
    public static String BARCODE = "BARCODE";

    public static String RETURN_PRINT_TITLE = "TAX CREDIT NOTE";
    public static String SALES_PRINT_TITLE = "Sales";

    public static String HHOrder = "HH order";
    public static String APPORDER = "App order";
    public static String ADVANCE_ORDER = "Advance order";
    public static String TELEORDER = "Tele order";
    public static String CURRENTORDER = "Current Order";
    public static String FREE_DELIVERY_ORDER = "Free Delivery";
    public static long TIME_FOR_BACKGROUND_TASK = 10 * 60000;
    public static String CATEGORY = "4G";

    public static boolean CheckIN;
    public static boolean isSumeryVisited;
    public static String SKIPPED_CUSTOMERS;
    public static String MISSED_CUSTOMERS;
    public static ArrayList<String> skippedCustomerSitIds;
    public static ArrayList<String> missedCustomerSitIds;
    public static String strCheckIN;

    public static final String UNLOAD = "Unload";
    public static final String Movement = "Movement";
    public static final String NormalLoad = "NormalLoad";
    public static final String Verify = "Verify";
    public static final String Customer = "Customer";
    public static final String Order = "Order";
    public static final String DiscountCreditNote = "DiscountCreditNote";
    public static final String AdvanceLoadReq = "7DaysAdvanceLoadReq";
    public static final String GRV = "GRV";
    public static final String Misc = "Misc";
    public static final String CreditNote = "CreditNote";
    public static final String Draft = "Draft";
    public static final String Receipt = "Receipt";
    public static final String RETURNORDER = "HH Return Order";
    public static final String REPLACEMETORDER = "Replace Order";
    public static final String LPO_ORDER = "LPO Order";
    public static final String MOVE_ORDER = "MOVE Order";
    public static final String TRNS_TYPE_IN = "IN";
    public static final String TRNS_TYPE_OUT = "OUT";
    public static String SKIP_JOURNEY_PLAN = "Skip Customer";
    public static String SKIP_SCRATCH_CARD = "Skip Scratch Card";
    public static final String asset = "Asset";
    public static float DEVICE_DENSITY = 0;
    public static int DEVICE_WIDTH = 0;
    public static int DEVICE_HEIGHT = 0;

    public static String APPFOLDERNAME = "AlSeer";
    public static String APPMEDIAFOLDERNAME = "AlSeerImages";
    public static String APPFOLDERPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + APPFOLDERNAME + "/";
    public static String APPMEDIAFOLDERPATH = APPFOLDERPATH + APPMEDIAFOLDERNAME + "/";
    public static File APPFOLDER = new File(APPFOLDERPATH);
    public static File APPMEDIAFOLDER = new File(APPMEDIAFOLDERPATH);

    public static final int MAX_IMAGE_SIZE = 700;
    public static String imagePath = "";
    public static Bitmap assetbarcodeimagePath;
    public static Bitmap assettempimagePath;
    public static int DENISITY;
    public static int SARBelow2000 = 1;
    public static int SARBelow3000 = 2;
    public static int SARBelow1000 = 3;
    public static int SARAbove4000 = 4;
    public static int SARZero = 0;

    public static String Task1 = "Capture real photo of the shelf to validate the accuracy of the plan";
    public static String Task2 = "Capture Competitor Promotions & Marketing initiatives";
    public static String Task3 = "Consumer behaviour survey for Frozen products under brand building strategy";

    public static String Task_Title1 = "Capture Shelf Photo";
    public static String Task_Title2 = "Competitor Promotions";
    public static String Task_Title3 = "In store - Consumer Behaviour Survey";

    public static final int DISCOUNT_ALL_ITEM = 0;
    public static final int DISCOUNT_ITEM = 1;
    public static final int DISCOUNT_CATEGORY = 2;
    public static final int DISCOUNT_BRAND = 3;


    public static final int DISCOUNT_PERCENTAGE = 0;
    public static final int DISCOUNT_AMOUNT = 1;
    public static final int TYPE_ORDER = 1;

    public static final String CUSTOMER_CHANNEL_MODERN = "19.MODERN TRADE";
    public static final String CUSTOMER_CHANNEL_GENERAL = "19.GENERAL TRADE";
    public static final String CUSTOMER_CHANNEL_HORECA = "19.HORECA";
    public static final String CUSTOMER_CHANNEL_PARLOUR = "19.DELIVERY SERVICE";


    public static final String CUSTOMER_TYPE_CREDIT = "CREDIT";
    public static final String CUSTOMER_TYPE_CASH = "CASH";

    public static final String REASON_TYPE_CREDIT = "CREDIT";
    public static final String REASON_TYPE_CASH = "CASH";

    public static final String DOC_NAME_VAT = "VAT";
    public static final String DOC_NAME_COMP_REG = "COMP_REG";
    public static final String DOC_NAME_MUNICIPALITY = "MUNICIPALITY";
    public static final String DOC_NAME_IQAMA = "IQAMA";

    public static final String CUSTOMER_CREDIT_TYPE_GC = "General Credit";
    public static final String CUSTOMER_CREDIT_TYPE_TC = "Temporary Credit";//============Route CreditLimit Enable

    public static final String PAYMENT_NOTE_CASH = "CASH";
    public static final String PAYMENT_NOTE_CHEQUE = "CHEQUE";
    public static final String PAYMENT_NOTE_ONLINE = "ONLINE";
    public static final String PAYMENT_NOTE_POS = "POS";
    public static final String PAYMENT_NOTE_CREDIT_CARD = "CR";

    public static final String ROUTE_START_TYPE = "S";
    public static final String ROUTE_END_TYPE = "End";

    public static final String ROUTE_TYPE_PRESALES = "Pre Sales";
    public static final String ROUTE_TYPE_VANSALES = "Van Sales";
    public static final String SELECT = "Select";

    public static final String MODE_OF_DELIVERY_PRESALES = "Pre-Sell";
    public static final String MODE_OF_DELIVERY_VANSALES = "Van-Sell";


    public static final int ORDER_PAYMENT_MODE_CASH = 1;
    public static final int ORDER_PAYMENT_MODE_CREDIT = 2;

    public static final int MAXIMUM_DISATNCE_OUTLET = 2000;
    public static int GCMRegistrationAttempts;
    //	public final static String SENDER_ID = "586523106653";//"715019169923";
    public final static String SENDER_ID = "702392795826";//"715019169923";//751276589472
    public final static int MaximumGCMRegistrationAttempts = 3;

    public static final String CUSTOMER_SIGN = "customer";
    public static final String SALESMAN_SIGN = "salesman";

    public static final String ACCOUNT_COPY = "ACCOUNT_COPY";
    public static final String COLLECTION_COPY = "COLLECTION_COPY";
    public static HashMap<String, String> hmSurvey;

    public static final String DOT_MATRIX = "DotMatrix";//DotMatrix
    public static final String THERMAL = "Thermal";//Thermal


    public static String currentLat = "N/A";
    public static String currentLng = "N/A";

    //PAYMENT
    public static final String NOTE_TYPE_1000 = "1000_RUPEE_NOTE";
    public static final String NOTE_TYPE_500 = "500_RUPEE_NOTE";
    public static final String NOTE_TYPE_200 = "200_RUPEE_NOTE";
    public static final String NOTE_TYPE_100 = "100_RUPEE_NOTE";

    public static final int NOTE_1000_VALUE = 1000;
    public static final int NOTE_500_VALUE = 500;
    public static final int NOTE_200_VALUE = 200;
    public static final int NOTE_100_VALUE = 100;
    public static final int CART_TYPE = 100;

    public static final String PRIMARY_SHELF_PLACEMENT = "Secondary Shelf Placement";
    public static final String POSM_AVAILABILITY = "Store Visibility";


    public static String iconpaths = "";

    public static OptionsNames getMediaOptionsTypes(String option) {
        if (option.equalsIgnoreCase("IMAGE"))
            return OptionsNames.IMAGE;
        else if (option.equalsIgnoreCase("VIDEO"))
            return OptionsNames.VIDEO;
        else if (option.equalsIgnoreCase("AUDIO"))
            return OptionsNames.AUDIO;
        return null;
    }

    //	public static String CURRECNY_CODE = " SAR";
    public static int DEVICE_DISPLAY_HEIGHT;
    public static final int KEYBOARD_OFFSET = 20;


    public static Vector<String> hashmapKeySetLayout(HashMap<String, LinearLayout> hmdetails) {
        Set<String> keySet = hmdetails.keySet();
        Iterator<String> iterator = keySet.iterator();
        Vector<String> vecCategoryIds = new Vector<String>();
        while (iterator.hasNext())
            vecCategoryIds.add(iterator.next());

        return vecCategoryIds;
    }

    public static OptionsNames getOptionsTypes(String option) {
        if (option.equalsIgnoreCase("CHECKBOX"))
            return OptionsNames.CHECKBOX;
        else if (option.equalsIgnoreCase("IMAGE") || option.equalsIgnoreCase("VIDEO") || option.equalsIgnoreCase("AUDIO"))
            return OptionsNames.MEDIA;
        else if (option.equalsIgnoreCase("YESNO"))
            return OptionsNames.YESNO;
        else if (option.equalsIgnoreCase("RADIO"))
            return OptionsNames.RADIO;
        else if (option.equalsIgnoreCase("SINGLE_LINE") || option.equalsIgnoreCase("MULTI_LINE") || option.equalsIgnoreCase("NUMERIC"))
            return OptionsNames.SINGLE_LINE;
        else if (option.equalsIgnoreCase("DROPDOWN"))
            return OptionsNames.DROPDOWN;
        else if (option.equalsIgnoreCase("EMOTION"))
            return OptionsNames.EMOTION;
        else if (option.equalsIgnoreCase("STAR"))
            return OptionsNames.STAR;
        else if (option.equalsIgnoreCase("TABLE"))
            return OptionsNames.TABLE;

        return null;

    }

    public static OptionsNames getFormOptionsTypes(String option) {
        if (option.equalsIgnoreCase("checkbox"))
            return OptionsNames.CHECKBOX;
        else if (option.equalsIgnoreCase("radio"))
            return OptionsNames.RADIO;
        else if (option.equalsIgnoreCase("text") || option.equalsIgnoreCase("number"))
            return OptionsNames.SINGLE_LINE;
        else if (option.equalsIgnoreCase("date"))
            return OptionsNames.DATE;
        else if (option.equalsIgnoreCase("time"))
            return OptionsNames.TIME;
        else if (option.equalsIgnoreCase("dropdown"))
            return OptionsNames.DROPDOWN;
        else if (option.equalsIgnoreCase("userdefined"))
            return OptionsNames.USERDEFINED;
        return OptionsNames.SINGLE_LINE;

    }

    /*
     * Version Management Constants
     */
    public final static int MAJOR_APP_UPDATE = 102;
    public final static int NORMAL_APP_UPDATE = 101;
    public final static int MINOR_APP_UPDATE = 100;

    public final static int VER_CHANGED = 100;
    public final static int VER_NOT_CHANGED = 101;
    public final static int VER_NO_BUTTON_CLICK = 102;
    public final static int VER_DO_EOT = 103;
    public final static int VER_DO_EOT_ADEOT = 104;
    public final static int VER_DO_ADEOT = 105;
    public final static int VER_UNABLE_TO_UPGRADE = 106;
    public static final int CALL_FROM_LOGIN = 1;


    //LOAD
    public static final String LOAD_VAN = "VL";
    public static final String UNLOAD_VAN = "VU";
    public static final String TRANSFER = "TL";
    public static final String MOVEMENT_VAN = "MI";
    public static final String MOVEMENT_AUDIT = "EX";
    public static final String ORDER_VOID = "OV";
    public static final String GOLDEN_STORE = "PerfectStorePercentage";

    public static final String LOAD_VAN_PENDING = "Pending";
    public static final String LOAD_VAN_PROCESSED = "Approved";//Processed
    public static final String LOAD_VAN_SUPERVISOR = "Supervisor Approved";//Processed
    public static final String LOAD_VAN_LOGISTICS = "Logistics Approved";//Processed
    public static final String LOAD_VAN_PROCESSED_ERP = "Processed ERP";
    public static final String LOAD_VAN_COLLECTED = "Collected";
    public static final String LOAD_VAN_REJECTED = "Rejected";

    public static final String SKU_CONTROLLER1_EDITED = "Sku Controller1";
    public static final String Editable = "Editable";
    public static final String SALESMAN_EDITED = "Edited";
    public static final String SUPERVISOR_EDITED = "Supervisor";
    public static final String AM_BM_EDITED = "AM/BM";
    public static final String SKU_CONTROLLER2_EDITED = "Sku Controller2";

    public static final int GOLDEN_STORE_VAL = 1;
    public static String PARTIAL_PAYMENT = "Partial Payment";
    public static boolean IS_KR_NETWORK_REACHABLE = false;
    public static boolean MULTICURRENCY_CURRENCY_SELECTION_ORDER = true;

    public static String CUSTOMER_IMAGE = "Customer Image";

    public static final String DECIMAL_FORMAT_PRICE = "#,##,##,###,###.###";
    public static final String DECIMAL_FORMAT_PRICE_NEW = "##,###,###,###";
    public static final int MINIMUM_FRACTION_DIGITS = 2;
    public static final int MAXIMUM_FRACTION_DIGITS = 2;

    public static final String REASON_NOTAVAILABLE = "1";
    public static final String REASON_AVAILABLE = "2";
    public static final String REASON_MODERATE = "3";
    public static final String REASON_CAPTURE = "4";
    public static final String REASON_NOTDONE = "5";


    public static final int COLLECT_PAYMENT = 100;
    public static final int DONE = 200;
    public static final int PRINT = 300;
    public static final int EMAIL = 400;
    public static final int ISSTAMPED = 500;
    public static final int SHARE = 600;

    public static String Execution_Summary = "";
    public static String CheckIn_Execution_Summary = "";
    public static String Load_Management = "";
    public static String Others = "";

    public static Vector<MenuDO> loadMenu() {
        Vector<MenuDO> vecMenuDOs = new Vector<MenuDO>();

        for (int i = 0; i < vanSalesMenuOptions.length; i++) {
            MenuDO menuDO = new MenuDO();
            menuDO.menuName = vanSalesMenuOptions[i];
            menuDO.menuImage = vanSalesMenuOptionIcons[i];

            if (menuDO.menuName.equalsIgnoreCase(AppConstants.Load_Management)) {
                for (int j = 0; j < loadMangementOption.length; j++) {
                    MenuDO loadmenuDO = new MenuDO();
                    loadmenuDO.menuName = loadMangementOption[j];
                    loadmenuDO.menuImage = loadMangementOptionIcon[j];
                    menuDO.vecMenuDOs.add(loadmenuDO);
                }

            } else if (menuDO.menuName.equalsIgnoreCase(AppConstants.Execution_Summary)) {
                for (int j = 0; j < executionSummaryOption.length; j++) {
                    MenuDO executionMenuDO = new MenuDO();
                    executionMenuDO.menuName = executionSummaryOption[j];
                    executionMenuDO.menuImage = executionSummaryOptionIcon[j];
                    menuDO.vecMenuDOs.add(executionMenuDO);
                }
            } else if (menuDO.menuName.equalsIgnoreCase(AppConstants.Others)) {
                for (int j = 0; j < othersOption.length; j++) {
                    MenuDO executionMenuDO = new MenuDO();
                    executionMenuDO.menuName = othersOption[j];
                    executionMenuDO.menuImage = othersOptionIcon[j];
                    menuDO.vecMenuDOs.add(executionMenuDO);
                }
            }

            vecMenuDOs.add(menuDO);
        }

        return vecMenuDOs;
    }

    public static Vector<MenuDO> loadCheckinMenuForVanSales() {
        Vector<MenuDO> vecMenuDOs = new Vector<MenuDO>();

        for (int i = 0; i < customerMenuOptions.length; i++) {
            MenuDO menuDO = new MenuDO();
            menuDO.menuName = customerMenuOptions[i];
            menuDO.menuImage = customerMenuOptionIcons[i];

            if (menuDO.menuName.equalsIgnoreCase(AppConstants.Load_Management)) {
                for (int j = 0; j < loadMangementOption.length; j++) {
                    MenuDO loadmenuDO = new MenuDO();
                    loadmenuDO.menuName = loadMangementOption[j];
                    loadmenuDO.menuImage = loadMangementOptionIcon[j];
                    menuDO.vecMenuDOs.add(loadmenuDO);
                }

            } else if (menuDO.menuName.equalsIgnoreCase(AppConstants.Execution_Summary)) {
                for (int j = 0; j < executionSummaryOption.length; j++) {
                    MenuDO executionMenuDO = new MenuDO();
                    executionMenuDO.menuName = executionSummaryOption[j];
                    executionMenuDO.menuImage = executionSummaryOptionIcon[j];
                    menuDO.vecMenuDOs.add(executionMenuDO);
                }
            } else if (menuDO.menuName.equalsIgnoreCase(AppConstants.Others)) {
                for (int j = 0; j < othersOption.length; j++) {
                    MenuDO executionMenuDO = new MenuDO();
                    executionMenuDO.menuName = othersOption[j];
                    executionMenuDO.menuImage = othersOptionIcon[j];
                    menuDO.vecMenuDOs.add(executionMenuDO);
                }
            }

            vecMenuDOs.add(menuDO);
        }

        return vecMenuDOs;
    }

    public static Vector<MenuDO> loadOutsideCheckinMenuForVanSales() {
        Vector<MenuDO> vecMenuDOs = new Vector<MenuDO>();

        for (int i = 0; i < customerMenuOptions.length; i++) {
            MenuDO menuDO = new MenuDO();
            menuDO.menuName = customerMenuOptions[i];
            menuDO.menuImage = customerMenuOptionIcons[i];

            if (menuDO.menuName.equalsIgnoreCase(AppConstants.Load_Management)) {
                for (int j = 0; j < loadMangementOption.length; j++) {
                    MenuDO loadmenuDO = new MenuDO();
                    loadmenuDO.menuName = loadMangementOption[j];
                    loadmenuDO.menuImage = loadMangementOptionIcon[j];
                    menuDO.vecMenuDOs.add(loadmenuDO);
                }

            }
//			else if(menuDO.menuName.equalsIgnoreCase(AppConstants.Execution_Summary))
//			{
//				for (int j = 0; j < executionSummaryOption.length; j++)
//				{
//					MenuDO executionMenuDO = new MenuDO();
//					executionMenuDO.menuName = executionSummaryOption[j];
//					executionMenuDO.menuImage = executionSummaryOptionIcon[j];
//					menuDO.vecMenuDOs.add(executionMenuDO);
//				}
//			}
            else if (menuDO.menuName.equalsIgnoreCase(AppConstants.Execution_Summary)) {
                for (int j = 0; j < checkin_executionSummaryOption.length; j++) {
                    MenuDO executionMenuDO = new MenuDO();
                    executionMenuDO.menuName = checkin_executionSummaryOption[j];
                    executionMenuDO.menuImage = checkin_executionSummaryOptionIcon[j];
                    menuDO.vecMenuDOs.add(executionMenuDO);
                }
            } else if (menuDO.menuName.equalsIgnoreCase(AppConstants.Others)) {
                for (int j = 0; j < othersOption.length; j++) {
                    MenuDO executionMenuDO = new MenuDO();
                    executionMenuDO.menuName = othersOption[j];
                    executionMenuDO.menuImage = othersOptionIcon[j];
                    menuDO.vecMenuDOs.add(executionMenuDO);
                }
            }

            vecMenuDOs.add(menuDO);
        }

        return vecMenuDOs;
    }

    public static Vector<MenuDO> loadMenuForMerchandiser() {
        Vector<MenuDO> vecMenuDOs = new Vector<MenuDO>();

        for (int i = 0; i < merchandiserMenuOptionIcon.length; i++) {
            MenuDO menuDO = new MenuDO();
            menuDO.menuName = merchandiserMenuOption[i];
            menuDO.menuImage = merchandiserMenuOptionIcon[i];

            if (menuDO.menuName.equalsIgnoreCase(AppConstants.Others)) {
                for (int j = 0; j < othersOption.length; j++) {
                    MenuDO executionMenuDO = new MenuDO();
                    executionMenuDO.menuName = othersOption[j];
                    executionMenuDO.menuImage = othersOptionIcon[j];
                    menuDO.vecMenuDOs.add(executionMenuDO);
                }
            }

            if (menuDO.menuName.equalsIgnoreCase(AppConstants.Execution_Summary)) {
                for (int j = 0; j < executionSummaryOption.length; j++) {
                    MenuDO executionMenuDO = new MenuDO();
                    executionMenuDO.menuName = executionSummaryOption[j];
                    executionMenuDO.menuImage = executionSummaryOptionIcon[j];
                    menuDO.vecMenuDOs.add(executionMenuDO);
                }
            }

            vecMenuDOs.add(menuDO);
        }

        return vecMenuDOs;
    }

    public static Vector<MenuDO> loadCheckinMenuForMerchandiser() {
        Vector<MenuDO> vecMenuDOs = new Vector<MenuDO>();

        for (int i = 0; i < merchandiserCheckedInOption.length; i++) {
            MenuDO menuDO = new MenuDO();
            menuDO.menuName = merchandiserCheckedInOption[i];
            menuDO.menuImage = merchandiserCheckedInOptionIcon[i];

            if (menuDO.menuName.equalsIgnoreCase(AppConstants.Others)) {
                for (int j = 0; j < othersOption.length; j++) {
                    MenuDO executionMenuDO = new MenuDO();
                    executionMenuDO.menuName = othersOption[j];
                    executionMenuDO.menuImage = othersOptionIcon[j];
                    menuDO.vecMenuDOs.add(executionMenuDO);
                }
            }

            vecMenuDOs.add(menuDO);
        }

        return vecMenuDOs;
    }

    public static Vector<MenuDO> loadMenuforPreseller() {
        Vector<MenuDO> vecMenuDOs = new Vector<MenuDO>();

        for (int i = 0; i < presellerMenuOption.length; i++) {
            MenuDO menuDO = new MenuDO();
            menuDO.menuName = presellerMenuOption[i];
            menuDO.menuImage = presellerMenuOptionIcon[i];

            //			if(SALESMAN_TYPE.equalsIgnoreCase(Preference.PRESELLER))
            //			{
            if (menuDO.menuName.equalsIgnoreCase(AppConstants.Execution_Summary)) {
                for (int j = 0; j < executionSummaryOption.length; j++) {
                    MenuDO executionMenuDO = new MenuDO();
                    executionMenuDO.menuName = executionSummaryOption[j];
                    executionMenuDO.menuImage = executionSummaryOptionIcon[j];
                    menuDO.vecMenuDOs.add(executionMenuDO);
                }
            }
            //			}
            //			else
            //			{
            //				if(menuDO.menuName.equalsIgnoreCase("Execution Summary"))
            //				{
            //					for (int j = 0; j < executionSummaryPresellerOption.length; j++)
            //					{
            //						MenuDO executionMenuDO = new MenuDO();
            //						executionMenuDO.menuName = executionSummaryPresellerOption[j];
            //						executionMenuDO.menuImage = executionSummaryPresellerOptionLoogs[j];
            //						menuDO.vecMenuDOs.add(executionMenuDO);
            //					}
            //				}
            //			}

            else if (menuDO.menuName.equalsIgnoreCase(AppConstants.Others)) {
                for (int j = 0; j < othersOption.length; j++) {
                    MenuDO executionMenuDO = new MenuDO();
                    executionMenuDO.menuName = othersOption[j];
                    executionMenuDO.menuImage = othersOptionIcon[j];
                    menuDO.vecMenuDOs.add(executionMenuDO);
                }
            }
            vecMenuDOs.add(menuDO);
        }
        return vecMenuDOs;
    }

    public static Vector<MenuDO> loadCheckinMenuforPreseller() {
        Vector<MenuDO> vecMenuDOs = new Vector<MenuDO>();

        for (int i = 0; i < presellerCheckedInMenuOption.length; i++) {
            MenuDO menuDO = new MenuDO();
            menuDO.menuName = presellerCheckedInMenuOption[i];
            menuDO.menuImage = presellerCheckedInMenuOptionIcon[i];

            if (menuDO.menuName.equalsIgnoreCase(AppConstants.Execution_Summary)) {
                for (int j = 0; j < executionSummaryOption.length; j++) {
                    MenuDO executionMenuDO = new MenuDO();
                    executionMenuDO.menuName = executionSummaryOption[j];
                    executionMenuDO.menuImage = executionSummaryOptionIcon[j];
                    menuDO.vecMenuDOs.add(executionMenuDO);
                }
            } else if (menuDO.menuName.equalsIgnoreCase(AppConstants.Others)) {
                for (int j = 0; j < othersOption.length; j++) {
                    MenuDO executionMenuDO = new MenuDO();
                    executionMenuDO.menuName = othersOption[j];
                    executionMenuDO.menuImage = othersOptionIcon[j];
                    menuDO.vecMenuDOs.add(executionMenuDO);
                }
            }

            vecMenuDOs.add(menuDO);
        }
        return vecMenuDOs;
    }

    private static final int UPLOAD_DATA_PENDING_STATUS = 0;
    private static final int UPLOAD_DATA_PUSHED_STATUS = 1;
    public static final String SALES_ORG_PG = "1030";

    public static final int TRX_ERROR_DUPLICATE = -1;
    public static final int TRX_ERROR_MIDDLEWARE = -100;
    public static final int Receipt_Sequence_Repetition = 2;
    public static final String ACTION_GOTO_MESAAGE_FINISH = "ACTION_GOTO_MESAAGE_FINISH";

    public static int get_UPLOAD_DATA_PENDING_STATUS() {
        return UPLOAD_DATA_PENDING_STATUS;
    }

    public static int get_UPLOAD_DATA_PUSHED_STATUS() {
        return UPLOAD_DATA_PUSHED_STATUS;
    }

    public static String getMimeType(final String filename) {
        // There does not seem to be a way to ask the OS or file itself for this
        // information, so unfortunately resorting to extension sniffing.

        int pos = filename.lastIndexOf('.');
        if (pos != -1) {
            String ext = filename.substring(filename.lastIndexOf('.') + 1,
                    filename.length());

            if (ext.equalsIgnoreCase("mp3"))
                return "audio/mpeg";
            if (ext.equalsIgnoreCase("aac"))
                return "audio/aac";
            if (ext.equalsIgnoreCase("wav"))
                return "audio/wav";
            if (ext.equalsIgnoreCase("ogg"))
                return "audio/ogg";
            if (ext.equalsIgnoreCase("mid"))
                return "audio/midi";
            if (ext.equalsIgnoreCase("midi"))
                return "audio/midi";
            if (ext.equalsIgnoreCase("wma"))
                return "audio/x-ms-wma";

            if (ext.equalsIgnoreCase("mp4"))
                return "video/mp4";
            if (ext.equalsIgnoreCase("avi"))
                return "video/x-msvideo";
            if (ext.equalsIgnoreCase("wmv"))
                return "video/x-ms-wmv";

            if (ext.equalsIgnoreCase("png"))
                return "image/png";
            if (ext.equalsIgnoreCase("jpg"))
                return "image/jpeg";
            if (ext.equalsIgnoreCase("jpe"))
                return "image/jpeg";
            if (ext.equalsIgnoreCase("jpeg"))
                return "image/jpeg";
            if (ext.equalsIgnoreCase("gif"))
                return "image/gif";

            if (ext.equalsIgnoreCase("xml"))
                return "text/xml";
            if (ext.equalsIgnoreCase("txt"))
                return "text/plain";
            if (ext.equalsIgnoreCase("cfg"))
                return "text/plain";
            if (ext.equalsIgnoreCase("csv"))
                return "text/plain";
            if (ext.equalsIgnoreCase("conf"))
                return "text/plain";
            if (ext.equalsIgnoreCase("rc"))
                return "text/plain";
            if (ext.equalsIgnoreCase("htm"))
                return "text/html";
            if (ext.equalsIgnoreCase("html"))
                return "text/html";

            if (ext.equalsIgnoreCase("pdf"))
                return "application/pdf";
            if (ext.equalsIgnoreCase("apk"))
                return "application/vnd.android.package-archive";

            // Additions and corrections are welcomed.
        }
        return "*/*";
    }

    public static final int TRX_TYPE_RETURN_ORDER = 4;
    public static final int TRX_TYPE_MISSED_ORDER = 5;
    //	public final static String[] strAllFilter={"Today","week","Last 4 weeks"};
    public final static String[] strAllFilter = {"Today", "week", "Month"};
    public static final int PrintAll = 3;
    public static final int Print_All_ExceptFoc = 1;
    public static final int Print_FOC = 2;

    public static String jsonCmdAttribStr = "";
    public static String base64LogoPng = "";


    //van to van
    public static final int LOAD_STOCK_OTHER_VAN = 3;
    public static final int UNLOAD_COLLECTED_STOCK = 10;
    public static final int UNLOAD_STOCK_RETURN = 5;
    public static final int UNLOAD_BAD_RETURN_EXPIRY = 6;
    public static final int UNLOAD_BAD_RETURN_DAMAGE = 7;

    public static final int REQUEST_CODE_CAPTURE = 1001;

}
