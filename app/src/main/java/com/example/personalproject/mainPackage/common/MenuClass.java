package com.example.personalproject.mainPackage.common;

import android.content.Context;

import com.example.personalproject.R;
import com.example.personalproject.mainPackage.dataobject.MenuDO;

import java.util.Vector;

public class MenuClass {

    private Context context;

    public MenuClass(Context context) {
        this.context = context;
    }

    public enum MENUS{
        MENU_JOURNEY_PLAN,
        MENU_NEXTDAY_JOURNEY_PLAN,
        MENU_MY_CUSTOMER,
        MENU_BLOCKED_CUSTOMER,
        MENU_LOAD_MANAGEMENT,
        MENU_EXECUTION_SUMMARY,
        MENU_ADD_NEW_CUSTOMER,
        MENU_OTHERS,
        MENU_LOGOUT,
        MENU_CUSTOMER_DASHBOARD,
        MENU_SETTINGS,
        MENU_ABOUT_APPLICATION,
        MENU_VAN_STOCK,
        MENU_PAYMENT_SUMMMARY,
        MENU_ORDER_SUMMMARY,
        MENU_CHECK_OUT,
        MENU_ACTIVE_PROMOTIONS,

        MENU_ACTIVE_SP,
        MENU_SURVEY,
        MENU_ENDORSEMENT,
        MENU_LOG_REPORT,
        MENU_RETURN_SUMMARY,
        MENU_DAMAGE_RETURNS,
        MENU_EXPIRY_RETURNS,
        MENU_UNLOAD,

        MENU_FOOTER
    }

    public MENUS menuVanSeller[] = {
            MENUS.MENU_JOURNEY_PLAN,
            MENUS.MENU_NEXTDAY_JOURNEY_PLAN,
            MENUS.MENU_MY_CUSTOMER,
            MENUS.MENU_BLOCKED_CUSTOMER,
            MENUS.MENU_LOAD_MANAGEMENT,
            MENUS.MENU_EXECUTION_SUMMARY,
            MENUS.MENU_ADD_NEW_CUSTOMER,
            MENUS.MENU_ENDORSEMENT,
            MENUS.MENU_OTHERS,
            MENUS.MENU_LOGOUT,
            MENUS.MENU_FOOTER
    };
    public MENUS menuVanSellerAfterCheckIn[] = new MENUS[]{
            MENUS.MENU_CUSTOMER_DASHBOARD,
            MENUS.MENU_EXECUTION_SUMMARY,
            MENUS.MENU_ACTIVE_PROMOTIONS,
            MENUS.MENU_ACTIVE_SP,
            MENUS.MENU_OTHERS,
            MENUS.MENU_CHECK_OUT,
            MENUS.MENU_FOOTER
    };

    public MENUS menuPreSeller[] = {
//            MENUS.MENU_JOURNEY_PLAN,
            MENUS.MENU_NEXTDAY_JOURNEY_PLAN,
            MENUS.MENU_MY_CUSTOMER,
            MENUS.MENU_BLOCKED_CUSTOMER,
            MENUS.MENU_EXECUTION_SUMMARY,
            MENUS.MENU_ADD_NEW_CUSTOMER,
            MENUS.MENU_ENDORSEMENT,
            MENUS.MENU_OTHERS,
            MENUS.MENU_LOGOUT,
//            MENUS.MENU_FOOTER //by kush
    };
    public MENUS menuPreSellerAfterCheckIn[] = new MENUS[]{
            MENUS.MENU_CUSTOMER_DASHBOARD,
            MENUS.MENU_EXECUTION_SUMMARY,
            MENUS.MENU_ACTIVE_PROMOTIONS,
            MENUS.MENU_ACTIVE_SP,
            MENUS.MENU_OTHERS,
            MENUS.MENU_CHECK_OUT,
//            MENUS.MENU_FOOTER //by kush
    };

    public MENUS loadManagement[] = {
            MENUS.MENU_VAN_STOCK,
            MENUS.MENU_DAMAGE_RETURNS,
            MENUS.MENU_EXPIRY_RETURNS,
            MENUS.MENU_UNLOAD
    };

    public MENUS executionSummary[] = new MENUS[]{
            MENUS.MENU_ORDER_SUMMMARY,
            MENUS.MENU_PAYMENT_SUMMMARY,
            MENUS.MENU_LOG_REPORT,
            MENUS.MENU_RETURN_SUMMARY //by kush
    };

    public MENUS others[] = {
            MENUS.MENU_SETTINGS,
            MENUS.MENU_ABOUT_APPLICATION
    };


    public Vector<MenuDO> loadMenu(int userType, boolean isCheckIn) {
        Vector<MenuDO> vecMenuDOs = new Vector<>();
        switch (userType){
            case AppConstants.USER_VAN_SALES:
                if (isCheckIn) {
                    for (MENUS objMenu : menuVanSellerAfterCheckIn) {
                        MenuDO menuDO = getMenuDO(objMenu);
                        menuDO.objMenu = objMenu;
                        menuDO = getMenuChildDO(menuDO);
                        vecMenuDOs.add(menuDO);
                    }
                } else {
                    for (MENUS objMenu : menuVanSeller) {
                        MenuDO menuDO = getMenuDO(objMenu);
                        menuDO.objMenu = objMenu;
                        menuDO = getMenuChildDO(menuDO);
                        vecMenuDOs.add(menuDO);
                    }
                }
                break;
            case AppConstants.USER_PRESELER:
                if (isCheckIn) {
                    for (MENUS objMenu : menuPreSellerAfterCheckIn) {
                        MenuDO menuDO = getMenuDO(objMenu);
                        menuDO.objMenu = objMenu;
                        menuDO = getMenuChildDO(menuDO);
                        vecMenuDOs.add(menuDO);
                    }
                }
                else {
                    for (MENUS objMenu : menuPreSeller) {
                        MenuDO menuDO = getMenuDO(objMenu);
                        menuDO.objMenu = objMenu;
                        menuDO = getMenuChildDO(menuDO);
                        vecMenuDOs.add(menuDO);
                    }
                }
                break;
        }
        return vecMenuDOs;
    }


    public MenuDO getMenuDO(MENUS field){
        MenuDO menu = new MenuDO();
        switch (field) {
            case MENU_JOURNEY_PLAN:
                menu.menuName = context.getString(R.string.Journey_Plan);
                menu.menuImage = R.drawable.journey_plan_icon;
                break;
            case MENU_NEXTDAY_JOURNEY_PLAN:
                menu.menuName = context.getString(R.string.Next_Day_Journey_Plan);
                menu.menuName = "Testing 1";
                menu.menuImage = R.drawable.next_journey_plan_icon;
                break;
            case MENU_MY_CUSTOMER:
                menu.menuName = context.getString(R.string.My_Customers1);
                menu.menuName = "Testing 2";
                menu.menuImage = R.drawable.mycustomer_icon;
                break;
            case MENU_BLOCKED_CUSTOMER:
                menu.menuName = context.getString(R.string.Blocked_Customers);
                menu.menuName = "Testing 3";
//                menu.menuImage = R.drawable.mycustomer_icon;
                menu.menuImage = R.drawable.mycustomer_icon_blocked;
                break;
            case MENU_LOAD_MANAGEMENT:
                menu.menuName = context.getString(R.string.Daily_Load);
                menu.menuImage = R.drawable.load_management_icon;
                break;
            case MENU_EXECUTION_SUMMARY:
                menu.menuName = context.getString(R.string.Execution_Summary);
                menu.menuName = "Testing 4";
//                menu.menuImage = R.drawable.orders_summary_icon;
                menu.menuImage = R.drawable.execution_summary_icon;
                break;
            case MENU_ADD_NEW_CUSTOMER:
                menu.menuName = context.getString(R.string.get_prospect);         // "Add Prospectus";
                menu.menuName = "Testing 5";
                menu.menuImage = R.drawable.add_necustomer_icon;
                break;
            case MENU_OTHERS:
                menu.menuName = context.getString(R.string.Others);
                menu.menuName = "More";
                menu.menuImage = R.drawable.about_application_icon;
                break;
            case MENU_LOGOUT:
                menu.menuName = context.getString(R.string.Logout);
                menu.menuImage = R.drawable.logout_menu_icon;
                break;
            case MENU_FOOTER:
                menu.menuName = context.getString(R.string.Footer);
                menu.menuImage = R.drawable.footer_new;
                break;

            case MENU_CUSTOMER_DASHBOARD:
                menu.menuName = context.getString(R.string.Customer_Dashboard);
                menu.menuImage = R.drawable.today_dashboard_icon;
                break;
            case MENU_ACTIVE_PROMOTIONS:
                menu.menuName = context.getString(R.string.active_promotions);
                menu.menuImage = R.drawable.active_promotions;
                break;
            case MENU_ACTIVE_SP:
                menu.menuName = context.getString(R.string.active_sp);// "Active SP";
                menu.menuImage = R.drawable.active_promotions;
                break;
            case MENU_CHECK_OUT:
                menu.menuName = context.getString(R.string.check_out);
                menu.menuImage = R.drawable.menu_checkout_icon;
                break;

            case MENU_SURVEY:
                menu.menuName = context.getString(R.string.survey);
                menu.menuImage = R.drawable.about_application_icon;
                break;
            case MENU_ENDORSEMENT:
//                menu.menuName = context.getString(R.string.endorsement);
                menu.menuName = context.getString(R.string.eot);
                menu.menuName = "Testing 6";
//                menu.menuImage = R.drawable.about_application_icon;
                menu.menuImage = R.drawable.eot_icon;
                break;

            case MENU_SETTINGS:
                menu.menuName = context.getString(R.string.Sync_Data);
                menu.menuName = "Settings";
                menu.menuImage = R.drawable.settings_icon;
                break;
            case MENU_ABOUT_APPLICATION:
                menu.menuName = context.getString(R.string.About_Application);
                menu.menuImage = R.drawable.about;
                break;

            case MENU_ORDER_SUMMMARY:
                menu.menuName = context.getString(R.string.Order_Summary2);
                menu.menuName = "Testing 4_1";
                menu.menuImage = R.drawable.orders_summary_icon;
                break;
            case MENU_PAYMENT_SUMMMARY:
                menu.menuName = context.getString(R.string.Payment_Summary);
                menu.menuName = "Testing 4_2";
                menu.menuImage = R.drawable.paymentsummary_icon;
                break;
            case MENU_LOG_REPORT:
                menu.menuName = context.getString(R.string.Log_Report);
                menu.menuName = "Testing 4_3";
                menu.menuImage = R.drawable.assets_request_icon;
                break;
            case MENU_RETURN_SUMMARY:
                menu.menuName = context.getString(R.string.Return_Summary);
                menu.menuName = "Testing 4_4";
//                menu.menuImage = R.drawable.paymentsummary_icon;
                menu.menuImage = R.drawable.returnsummary_icon;
                break;

            case MENU_VAN_STOCK:
                menu.menuName = context.getString(R.string.Van_Stock);
                menu.menuImage = R.drawable.salable_van_stock_icon;
                break;
            case MENU_DAMAGE_RETURNS:
                menu.menuName = context.getString(R.string.Damage_Returns);
                menu.menuImage = R.drawable.order_summary;
                break;
            case MENU_EXPIRY_RETURNS:
                menu.menuName = context.getString(R.string.Expiry_Returns);
                menu.menuImage = R.drawable.order_summary;
                break;
            case MENU_UNLOAD:
                menu.menuName = context.getString(R.string.Unload_Stock);
                menu.menuImage = R.drawable.load_management_icon;
                break;
        }
        return menu;
    }

    private MenuDO getMenuChildDO(MenuDO menu) {
        switch (menu.objMenu) {

            case MENU_OTHERS:
                for (MENUS objMenu : others) {
                    MenuDO menuDO = getMenuDO(objMenu);
                    menuDO.objMenu = objMenu;
                    menu.vecMenuDOs.add(menuDO);
                }
                break;
            case MENU_EXECUTION_SUMMARY:
                for (MENUS objMenu : executionSummary) {
                    MenuDO menuDO = getMenuDO(objMenu);
                    menuDO.objMenu = objMenu;
                    menu.vecMenuDOs.add(menuDO);
                }
                break;
            case MENU_LOAD_MANAGEMENT:
                for (MENUS objMenu : loadManagement) {
                    MenuDO menuDO = getMenuDO(objMenu);
                    menuDO.objMenu = objMenu;
                    menu.vecMenuDOs.add(menuDO);
                }
                break;
        }
        return menu;
    }

    public boolean isCommonEOTPopupDisplayed(Context context) {
        boolean isShowed = false;
        /*if (((BaseActivity) context).isShortRoute&&!((BaseActivity) context).isPreviousDayEOTDone) {
            isShowed = true;
            ((BaseActivity) context).showCustomDialog(context, context.getResources().getString(R.string.warning), context.getResources().getString(R.string.Please_submit_the_previous_trip_EOT), context.getResources().getString(R.string.OK), null, "");
        }
        else if (!((BaseActivity) context).isCanStartDayNew) {
            isShowed = true;
            ((BaseActivity) context).showCustomDialog(context, context.getResources().getString(R.string.warning), context.getResources().getString(R.string.Trip_ended_for_the_day), context.getResources().getString(R.string.OK), null, "");
        }
        else if (!((BaseActivity) context).isDayStartedNew) {
            isShowed = true;
            ((BaseActivity) context).showCustomDialog(context,context.getResources().getString(R.string.warning), context.getResources().getString(R.string.please_start_the_day), context.getResources().getString(R.string.OK), null, "");
        }*/
//        else if (!((BaseActivity) context).isEOTDoneNew) {
//            isShowed = true;
//            ((BaseActivity) context).showCustomDialog(context,context.getResources().getString(R.string.warning), "Please start the day.", context.getResources().getString(R.string.OK), null, "");
//        }
        return isShowed;
    }
    public boolean isCommonEOTPopupCheckinBlockDisplayed(Context context) {
        boolean isShowed = false;
        /*if (((BaseActivity) context).isShortRoute&&!((BaseActivity) context).isPreviousDayEOTDone) {
            isShowed = true;
            ((BaseActivity) context).showCustomDialog(context, context.getResources().getString(R.string.warning), context.getResources().getString(R.string.Please_submit_the_previous_trip_EOT), context.getResources().getString(R.string.OK), null, "");
        }*/
//        else if (!((BaseActivity) context).isCanStartDayNew) {
//            isShowed = true;
//            ((BaseActivity) context).showCustomDialog(context, context.getResources().getString(R.string.warning), "Trip ended for the day.", context.getResources().getString(R.string.OK), null, "");
//        }
//        else if (!((BaseActivity) context).isDayStartedNew) {
//            isShowed = true;
//            ((BaseActivity) context).showCustomDialog(context,context.getResources().getString(R.string.warning), "Please start the day.", context.getResources().getString(R.string.OK), null, "");
//        }

//        else if (!((BaseActivity) context).isEOTDoneNew) {
//            isShowed = true;
//            ((BaseActivity) context).showCustomDialog(context,context.getResources().getString(R.string.warning), "Please start the day.", context.getResources().getString(R.string.OK), null, "");
//        }
        return isShowed;
    }
    public boolean isCommonEOTPopupDisplayedCheckEOT(Context context) {
        boolean isShowed = false;
        /*if (!((BaseActivity) context).isCanStartDayNew) {
            isShowed = true;
            ((BaseActivity) context).showCustomDialog(context, context.getResources().getString(R.string.warning), context.getResources().getString(R.string.Trip_ended_for_the_day), context.getResources().getString(R.string.OK), null, "");
        }
        else if (!((BaseActivity) context).isDayStartedNew) {
            isShowed = true;
            ((BaseActivity) context).showCustomDialog(context,context.getResources().getString(R.string.warning),  context.getResources().getString(R.string.please_start_the_day), context.getResources().getString(R.string.OK), null, "");
        }*/
        return isShowed;
    }
    public boolean isEOTPopupDisplayed(Context context) {
        boolean isShowed = false;
       /* if ( !((BaseActivity) context).isOdometerReadingDoneforLastdate) {
            isShowed = true;
            ((BaseActivity) context).showCustomDialog(context, context.getResources().getString(R.string.warning),
                    "You haven't done EOD for Date : " + ((BaseActivity) context).tripDate + " ,Please do EOD.", context.getResources().getString(R.string.OK), null, "LAST_DATE_EOD");
        } else if (((BaseActivity) context).isEOTDoneForTodaysDate) {
            isShowed = true;
            ((BaseActivity) context).showCustomDialog(context,context.getResources().getString(R.string.warning), context.getResources().getString(R.string.journey_ended_for_the_day), context.getResources().getString(R.string.OK), null, "");
        } else if (!((BaseActivity) context).isJourneyStarted && !((BaseActivity) context).isStartDayAllowed) {
            isShowed = true;
            ((BaseActivity) context).showCustomDialog(context, context.getResources().getString(R.string.warning), context.getResources().getString(R.string.Trip_ended_for_the_day), context.getResources().getString(R.string.OK), null, "");
        }*/
//        else {
//            isShowed = false;
//        }
        return isShowed;
    }

    //added by sai ====

    static String DASHBOARD = "Dashboard";
    static String LEADERBOARD = "Leaderboard";
//    static String LEADERBOARD = getString(R.string.);
    static String JP = "JP";
    static String MENU = "Menu";

    public static String[] MENU_DASHBOARD = {
            /* RSO_MYADVISOR,*/
            DASHBOARD,
            LEADERBOARD,
            JP,
            MENU
    };

    public static Integer[] MENU_ICON_DASHBOARD = {
            /* R.drawable.pos,*/
//            R.drawable.dashboard_1,
            R.drawable.dashboard_2,
            R.drawable.leaderboard_1,
            R.drawable.jp_1,
            R.drawable.menu_1
    };

}
