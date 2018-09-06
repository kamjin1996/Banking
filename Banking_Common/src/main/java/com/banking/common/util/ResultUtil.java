package com.banking.common.util;

import com.banking.common.vo.R;

public class ResultUtil {
    public static R createResult(int res){
        if(res>0){
            return R.OK();
        }else {
            return R.ERROR();
        }
    }
}
