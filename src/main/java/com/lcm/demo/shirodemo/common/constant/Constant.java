package com.lcm.demo.shirodemo.common.constant;

import java.io.Serializable;

/**
 * Description:常量
 *
 * @author lcm
 * @date 2019/4/3 23:00
 */
public class Constant implements Serializable {

    private static final long serialVersionUID = -7252409957374883991L;

    /**
     * 数字0，后端相应成功的状态码
     */
    public static final int ZERO = 0;

    /**
     * 超级管理员id
     */
    public static final int SUPER_ADMIN = 1;

    /**
     * 页码
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 默认页码
     */
    public static final int DEFAULT_PAGE_NUM = 1;

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 默认每页显示记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 数据
     */
    public static final String DATA = "data";

    /**
     * 列表
     */
    public static final String LIST = "list";

    /**
     * 分页数据
     */
    public static final String PAGE = "page";


    /**
     * 成功
     */
    public static final String SUCCESS = "success";

    /**
     * 状态码
     */
    public static final String CODE = "code";

    /**
     * 信息
     */
    public static final String MSG = "msg";

    /**
     * 令牌token
     */
    public static final String TOKEN = "token";


    public static final long TOKEN_TIMEOUT=7*24*3600L;
}
