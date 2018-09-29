package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.exchange.request.IsPhoneExistsRequest;
import com.honglu.quickcall.user.facade.exchange.request.PersonInfoRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveBirthRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveGenderRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveInterestRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveNickNameRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveOccupationRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveSignNameRequest;
import com.honglu.quickcall.user.facade.exchange.request.ShowHomePageLogout;

/**
 * 
 * @author liuyinkai
 *
 */
public interface PersonInfoMapper {
	
	/**
     * 根据用户id 获取用户信息
     *
     * @param params
     * @return
     */
    CommonResponse queryPersonInfo( PersonInfoRequest params);
    /**
     * 保存昵称/头像
     */
    CommonResponse saveNicknameImage( SaveNickNameRequest params);
    /**
     * 保存性别
     */
    CommonResponse saveGender( SaveGenderRequest params);
    /**
     * 保存签名
     */
    CommonResponse saveSignName( SaveSignNameRequest params);
    /**
     * 保存生日
     */
    CommonResponse saveBirthday( SaveBirthRequest params);
    /**
     * 保存兴趣
     */
    CommonResponse saveInterest( SaveInterestRequest params);
    /**
     * 保存职业
     */
    CommonResponse saveOccupation( SaveOccupationRequest params);
    /**
     * 大V主页，普通用户主页（客态）
     */
    CommonResponse showHomePageLogout( ShowHomePageLogout params);
}
