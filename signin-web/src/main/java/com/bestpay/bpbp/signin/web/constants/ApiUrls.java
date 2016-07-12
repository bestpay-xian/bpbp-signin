/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2016 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.web.constants;

/**
 * 定义调用接口的url常量
 * @author gaoxiang
 * @version Id: TestJsonUtils.java, v 0.1 2016/1/18 15:29 gaoxiang Exp $$
 */
public class ApiUrls {

    /**登录接口 url*/
    public static final String LOGIN_URL       = "/login";
    /**退出接口 url*/
    public static final String LOGOUT_URL       = "/logout";

    /** 返回登录界面，退出跳转界面 **/
    public static final String LOGIN_JSP       = "/login/login";

    /** 管理员登录成功界面 url**/
    public static final String LAYOUT_URL        = "/main";
    /** 页面布局top页面  url**/
    public static final String LAYOUT_TOP_URL   = "/top";
    /** 页面布局top页面  url**/
    public static final String LAYOUT_INDEX_URL   = "/index";
    /** 页面布局left页面  url**/
    public static final String LAYOUT_LEFT_URL   = "/left";
    /** 页面布局left用户页面  url**/
    public static final String LAYOUT_LEFT_USER_URL   = "/left_user";
    /** 页面布局center页面  url**/
    public static final String LAYOUT_CENTER_URL   = "/center";
    /** 页面布局center页面  url**/
    public static final String LAYOUT_FOOTER_URL   = "/footer";


    /**查询员工列表接口 url*/
    public static final String QUERY_EMPLOYEE_URL       = "/employee/list";
    /**查询员工列表接口 from url*/
    public static final String QUERY_EMPLOYEE_FORM_URL       = "/employee/fromList";
    /**跳转新增员工界面 url*/
    public static final String SAVE_EMPLOYEE_URL       = "/employee/saveEmployee";
    /**保存员工信息接口 url*/
    public static final String INSERT_EMPLOYEE_URL       = "/employee/insertEmployee";
    /**跳转修改员工界面 url*/
    public static final String EDIT_EMPLOYEE_URL       = "/employee/editEmployee";
    /**修改员工信息 url*/
    public static final String UPDATE_EMPLOYEE_URL       = "/employee/updateEmployee";
    /**修改密码 url*/
    public static final String UPDATE_PASSWORD_URL       = "/employee/updatePassword";
    /**删除员工信息接口 url*/
    public static final String DELETE_EMPLOYEE_URL      = "/employee/deleteEmployee";
    /**重置员工登录密码信息接口 url*/
    public static final String RESET_PASSWORD_URL      = "/employee/resetPassword";
    /**检查员工信息是否存在接口 url*/
    public static final String CHECK_EMPLOYEE_URL       = "/employee/checkEmployee";


    /**通讯录信息查询接口 url*/
    public static final String QUERY_ALL_URL       = "/selectAll";
    /**通讯录信息查询 from url*/
    public static final String QUERY_ALL_FROM_URL = "/comm/fromList";

    /** 管理员登录成功界面 jsp**/
    public static final String LAYOUT_JSP        = "/common/main";
    /** 页面布局top页面  jsp**/
    public static final String LAYOUT_TOP_JSP   = "/common/top";
    /** 页面布局top页面  jsp**/
    public static final String LAYOUT_INDEX_JSP   = "/common/index";
    /** 页面布局left页面  jsp**/
    public static final String LAYOUT_LEFT_JSP   = "/common/left";
    /** 页面布局left用户页面  jsp**/
    public static final String LAYOUT_LEFT_USER_JSP   = "/common/left_user";
    /** 页面布局center页面  jsp**/
    public static final String LAYOUT_CENTER_JSP   = "/common/center";
    /** 页面布局center页面  jsp**/
    public static final String LAYOUT_FOOTER_JSP   = "/common/footer";

    /** 查询员工签到记录*/
    public static final String QUERY_RECORD_URL       = "/record/list";
    /** 员工签到*/
    public static final String INSERT_RECORD_URL       = "/record/insertRecord";
    /** 更新员工签到记录*/
    public static final String UPDATE_RECORD_URL       = "/record/updateRecord";
    /** 删除员工签到记录*/
    public static final String DELETE_RECORD_URL       = "/record/deleteRecord";
    /** 员工签到跳转*/
    public static final String RECORD_LIST_URL       = "/record/record_list";
    /** 员工签到失败跳转*/
    public static final String RECORD_SIGNIN_URL       = "/common/signin";
    /** 员工签到记录 from*/
    public static final String RECORD_SIGNIN__FROM_URL = "/record/recordFromList";
    /**签到页面 url*/
    public static final String SIGNIN__FROM_URL = "/signin";

    /** 员工列表信息 jsp**/
    public static final String QUERY_EMPLOYEE_JSP       = "/user/select_user";
    /** 新增员工信息 jsp**/
    public static final String ADD_EMPLOYEE_JSP       = "/user/add_user";
    /** 修改员工信息 jsp**/
    public static final String UPDATE_EMPLOYEE_JSP       = "/user/update_user";
    /**修改密码 jsp*/
    public static final String UPDATE_PASSWORD_JSP       = "/user/update_password";
    /**通讯录信息 jsp*/
    public static final String QUERY_COMM_JSP = "/user/communication_list";
    /**签到记录信息 jsp*/
    public static final String QUERY_RECORD_JSP = "/record/record_list";
    /**转到签到页面 jsp*/
    public static final String SIGNIN__FROM_URL_JSP = "/common/signin";

    /**新增、修改、查询厂商后返回到list页面*/
    public static final String SAVE_OR_UPDATE_PLATFORM_BACK_URL   = "/user/select_platfrm";
    /**跳转到更新页面*/
    public static final String UPDATE_PLATFORM_BACK_URL   = "/user/update_platfrm";
    /**跳转到新增页面*/
    public static final String ADD_PLATFORM_BACK_URL   = "/user/add_platfrm";



    /** 厂商名称空间*/
    public static final String PLATFORM_URL   = "/platform";
    /** 新增厂商*/
    public static final String ADD_PLATFORM_URL   = "/addPlatform";
    /** 更新厂商*/
    public static final String UPDATE_PLATFORM_URL   = "/updatePlatform";
    /** 获取厂商(分页)*/
    public static final String SELECT_PLATFORM_URL   = "/selectAllPlatformByName";
    /** 获取厂商(不分页)*/
    public static final String SELECT_PLATFORMLIST_URL   = "/selectPlatformList";
    /** 获取厂商名*/
    public static final String SELECT_PLATFORMNAME_URL   = "/selectAllPlatformName";
    /** 删除厂商*/
    public static final String DELETE_PLATFORM_URL   = "/deletePlatform";
    /** 根据主键查询跳转到更新页面*/
    public static final String FIND_PLATFORM_BYID_URL   = "/selectPlatformById";


    /**新增跳转请求*/
    public static final String ADD_PLATFORM_DIRECTIVE_URL   = "/add_platform_jsp";
    /**查询跳转请求*/
    public static final String SELECT_PLATFORM_DIRECTIVE_URL   = "/select_platform_jsp";

    /** 验证厂商名是否重复*/
    public static final String VALIDATE_PLATFORMNAME_ISEXIST_URL   = "/validatePlatformNameIsExist";



    /*************************************部门表url****************************************/
    /**查询部门列表接口Ajax url*/
    public static final String QUERY_DEPT_URL       = "/dept/list";
    /**查询部门列表接口Form url*/
    public static final String QUERY_DEPT_FORM_URL       = "/dept/formList";
    /**跳转新增部门界面 url*/
    public static final String SAVE_DEPT_URL       = "/dept/saveDept";
    /**保存部门信息接口 url*/
    public static final String INSERT_DEPT_URL       = "/dept/insertDept";
    /**跳转修改部门界面 url*/
    public static final String EDIT_DEPT_URL       = "/dept/editDept";
    /**修改部门信息 url*/
    public static final String UPDATE_DEPT_URL       = "/dept/updateDept";
    /**删除部门信息接口 url*/
    public static final String DELETE_DEPT_URL       = "/dept/deleteDept";
    /**检查部门信息是否存在接口 url*/
    public static final String CHECK_DEPT_URL       = "/dept/checkDept";

    /***********************************部门管理jsp*****************************************/
    /** 部门列表信息 **/
    public static final String QUERY_DEPT_JSP       = "/dept/select_dept";
    /** 新增部门信息 **/
    public static final String ADD_DEPT_JSP       = "/dept/add_dept";
    /** 修改部门信息 **/
    public static final String UPDATE_DEPT_JSP       = "/dept/update_dept";


    /*************************************中心url****************************************/
    /** 中心名称空间*/
    public static final String CENTER_URL   = "/center";
    /**新增或修改中心后返回到list页面*/
    public static final String SAVE_OR_UPDATE_CENTER_BACK_URL   = "/center/select_center";
    /**请求：跳转到更新页面*/
    public static final String UPDATE_CENTER_DIRECTIVE_URL = "/update_directive";
    /**跳转到更新页面*/
    public static final String UPDATE_CENTER_BACK_URL   = "/center/update_center";
    /**请求：跳转到新增页面*/
    public static final String ADD_CENTER_DIRECTIVE_URL = "/add_directive";
    /**跳转到新增页面*/
    public static final String ADD_CENTER_BACK_URL   = "/center/add_center";
    /**请求：跳转到查询页面*/
    public static final String SELECT_CENTER_DIRECTIVE_URL = "/select_directive";


    /** 新增中心*/
    public static final String ADD_CENTER_URL   = "/addCenter";
    /** 更新中心*/
    public static final String UPDATE_CENTER_URL   = "/updateCenter";
    /** 按条件获取中心记录（不分页）*/
    public static final String SELECT_CENTER_URL   = "/selectCenterList";
    /** 按条件获取中心记录（分页）*/
    public static final String SELECT_CENTER_PAGE_URL   = "/selectCenterPageList";
    /** 删除中心*/
    public static final String DELETE_CENTER_URL   = "/deleteCenter";
    /** 按主键查询*/
    public static final String FIND_CENTER_BYID_URL   = "/selectCenterById";

    /** 验证中心名是否重复*/
    public static final String VALIDATE_CENTERNAME_ISEXIST_URL   = "/validateCenterIsExist";

    /*************************************中心url****************************************/

    /**添加小组信息*/
    public static final String ADD_TEAM_URL   = "/insertTeamInfo";
    /**小组信息*/
    public static final String ADD_TEAM_SUCCESS_URL   = "/user/select_team";
    /**添加小组信息页面*/
    public static final String ADD_TEAM_FAILURE_URL   = "/user/add_team";
    /**查询小组信息*/
    public static final String LIST_TEAM_URL   = "/team/list";
    /**更新小组信息*/
    public static final String UPDATE_TEAM_URL   = "/team/update";
    /**删除小组信息*/
    public static final String DELETE_TEAM_URL   = "/team/delete";
    /**更新小组信息跳转页面Controller*/
    public static final String SELECT_TEAM_URL   = "/team/single";
    /**更新小组信息跳转页面*/
    public static final String SELECT_TEAM_SUCCESS_URL   = "/user/update_team";
    /** 验证小组名是否重复*/
    public static final String VALIDATE_TEAMNAME_ISEXIST_URL   = "/validateTeamIsExist";

    /** 跳转查询页面请求url*/
    public static final String TO_SELECT_URL   = "/team/toSelectPage";


}
