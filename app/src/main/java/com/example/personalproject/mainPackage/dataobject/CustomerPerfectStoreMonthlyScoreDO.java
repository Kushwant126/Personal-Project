package com.example.personalproject.mainPackage.dataobject;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerPerfectStoreMonthlyScoreDO implements Serializable {

    public String CustomerPerfectStoreMonthlyScoreId = "";
    public String CustomerPerfectStoreMonthlyScoreUID = "";
    public String CustomerCode = "" ;
    public String SalesOrgCode = "";
    public String PerfectStoreGroupId = "";
    public String Year ="" ;
    public String Month ="" ;
    public String Status = "";//=========InProgress
    public boolean IsPerfectStore =false;
    public String Score = "";
    public String MaxScore = "";

    public String RouteCode = "";
    public String CaptureDate = "";
    public String UserCode = "";
    public String CustomerName = "" ;
    public ArrayList<CustomerPerfectStoreMonthlyKPIScoreDO> arrCustomerPerfectStore=new ArrayList<>();
    public ArrayList<CustomerPerfectStoreMonthlyKPIWiseExecutionBreakUpDO> arrCustomerPerfectStoreMonthlyKPIWiseExecutionBreakUp=new ArrayList<>();

}
