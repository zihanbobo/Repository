package com.honglu.quickcall.user.service.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

//import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exception.RemoteException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.core.util.Detect;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.rongyun.models.CodeSuccessReslut;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.constants.UserBizConstants;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.CustomerInterest;
import com.honglu.quickcall.user.facade.entity.CustomerOccupation;
import com.honglu.quickcall.user.facade.entity.Fans;
import com.honglu.quickcall.user.facade.entity.Interest;
import com.honglu.quickcall.user.facade.entity.Occupation;
import com.honglu.quickcall.user.facade.entity.Orders;
import com.honglu.quickcall.user.facade.entity.Product;
import com.honglu.quickcall.user.facade.entity.SensitivityWord;
import com.honglu.quickcall.user.facade.entity.in.HomePageLogout;
import com.honglu.quickcall.user.facade.entity.in.PersonHomePage;
import com.honglu.quickcall.user.facade.entity.in.VProductTag;
import com.honglu.quickcall.user.facade.exchange.request.AddOrCancelFansRequest;
import com.honglu.quickcall.user.facade.exchange.request.CheckAttentionRequest;
import com.honglu.quickcall.user.facade.exchange.request.PersonInfoRequest;
import com.honglu.quickcall.user.facade.exchange.request.QueryAttentionFansListRequest;
import com.honglu.quickcall.user.facade.exchange.request.QueryInterestListRequest;
import com.honglu.quickcall.user.facade.exchange.request.QueryOccupationListRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveBirthRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveGenderRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveInterestRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveNickNameRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveOccupationRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveSignNameRequest;
import com.honglu.quickcall.user.facade.exchange.request.ShowHomePageLogout;
import com.honglu.quickcall.user.facade.vo.AttentionFansVO;
import com.honglu.quickcall.user.facade.vo.InterestVO;
import com.honglu.quickcall.user.facade.vo.OccupationVO;
import com.honglu.quickcall.user.service.dao.CustomerInterestMapper;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.dao.CustomerOccupationMapper;
import com.honglu.quickcall.user.service.dao.FansMapper;
import com.honglu.quickcall.user.service.dao.InterestMapper;
import com.honglu.quickcall.user.service.dao.OccupationMapper;
import com.honglu.quickcall.user.service.dao.OrdersMapper;
import com.honglu.quickcall.user.service.dao.ProductMapper;
import com.honglu.quickcall.user.service.dao.SensitivityWordMapper;
import com.honglu.quickcall.user.service.dao.SkillMapper;
import com.honglu.quickcall.user.service.service.CustomerRedisManagement;
import com.honglu.quickcall.user.service.service.PersonInfoService;
import com.honglu.quickcall.user.service.util.CountAge;
import com.honglu.quickcall.user.service.util.JsonParseUtil;
import com.honglu.quickcall.user.service.util.RedisKeyConstants;

import cn.jiguang.commom.utils.StringUtils;

@Service
@Transactional
public class PersonInfoServiceImpl implements PersonInfoService {
	@Autowired
	private CustomerRedisManagement customerRedisManagement;
	@Autowired
	private InterestMapper interestMapper;
	@Autowired
	private OccupationMapper occupationMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private SensitivityWordMapper sensitivityWordMapper;
	@Autowired
	private CustomerInterestMapper customerInterestMapper;
	@Autowired
	private CustomerOccupationMapper customerOccupationMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private FansMapper fansMapper;
	@Autowired
	private SkillMapper skillMapper;
	@Autowired
	private OrdersMapper ordersMapper;
	/**
	 * 中文、英文、数字、下划线校验 4-24位
	 */
	private final static Pattern CH_EN_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5a-z\\d_]{4,24}$");
	private final static Pattern ID_PATTERN = Pattern
			.compile("^\\d{6}(18|19|20)?\\d{2}(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])\\d{3}(\\d|[xX])$");
	private static final Logger logger = LoggerFactory.getLogger(PersonInfoServiceImpl.class);

	// 测试正则
	// public static void main(String[] args) {
	// String string = "大猫__";
	// Matcher m = CH_EN_PATTERN.matcher(string);
	// System.out.println(m.matches());
	//
	// }
	/**
	 * @author liuyinkai 查看个人信息
	 */
	@Override
	public CommonResponse queryPersonInfo(PersonInfoRequest params) {
		if (null == params.getCustomerId() || "".equals(params.getCustomerId())) {
			throw new RemoteException(UserBizReturnCode.UserNotExist,
					"用户不存在 request.getJson()=" + params.getCustomerId());
		}
		CommonResponse commonResponse = new CommonResponse();
		PersonHomePage personHomePage = null;
		// if (null == (params.getCustomerId()) || null == (params.getOtherId())) {
		// throw new RemoteException(UserBizReturnCode.paramError, "参数错误
		// request.getJson()=" + params.getCustomerId());
		// }
		try {
			// 判断是不是查看自己的资料
			// if (!params.getCustomerId().equals(params.getOtherId())) {
			// personHomePage = queryPersonal(params.getOtherId());
			// } else {
			personHomePage = queryPersonal(params.getCustomerId());
			// }

			commonResponse.setData(personHomePage);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
		} catch (Exception e) {
			throw new RemoteException(UserBizReturnCode.UserNotExist, "用户不存在");
		}

		return commonResponse;
	}

	/**
	 * @Title 查询详细展示信息
	 * @modify liuyinkai
	 * @param accountId
	 * @return outPacket
	 */
	public PersonHomePage queryPersonal(Long customerId) {
		Customer customer = customerRedisManagement.getCustomer(customerId);
		// 获取身份证
		String identityID = customer.getCredentialsNum();
		PersonHomePage personHomePage = new PersonHomePage();
		if (customer != null) {
			personHomePage.setCustomerId(customerId);// id
			personHomePage.setNickName(customer.getNickName());// 昵称
			personHomePage.setSignName(customer.getSignName());// 签名
			personHomePage.setStarSign(customer.getStarSign());// 星座
			personHomePage.setTokenCode(customer.getTokenCode());// token
			personHomePage.setvStatus(customer.getvStatus());// 大V审核状态
			personHomePage.setIdentityStatus(customer.getIdentityStatus());// 身份证审核状态
			// 查询粉丝数量R
			Long fansNum = fansMapper.queryFansNumByCustomerId(customerId);
			personHomePage.setFansNum(fansNum);
			// 查询关注数量
			int attentionNum = fansMapper.queryAttentionNumByCustomerId(customerId);
			personHomePage.setAttentionNum(attentionNum);
			// 获取年纪
			Date birthday = customer.getBirthday();
			// 用工具类去转换
			int age = CountAge.getAgeByBirth(birthday);
			personHomePage.setAge(age);
			// 判断身份证是否为空，如果又身份证则按找身份证上面的性别
			if (StringUtils.isNotEmpty(identityID)) {
				// TODO 此处不应该校验身份证号码是否正确
				// Matcher m = ID_PATTERN.matcher(identityID);
				// if (!m.matches()) {
				// throw new RemoteException(UserBizReturnCode.paramError, "身份证参数错误");
				// }
				// 判断身份证男女，截取身份证倒数第二位
				String genderStr = identityID.substring(identityID.length() - 2, identityID.length() - 1);
				int genderInt = Integer.parseInt(genderStr);
				if (genderInt % 2 == 0) {
					personHomePage.setSex(0);// 女
				} else {
					personHomePage.setSex(1);// 男
				}
			} else {
				personHomePage.setSex(customer.getSex());// 性别
			}
			personHomePage.setHeadPortraitUrl(customer.getHeadPortraitUrl());// 头像
			personHomePage.setBirthday(customer.getBirthday());// 生日
			// 查询兴趣爱好 by customerId
			try {

				List<Interest> interestList = interestMapper.selectInterestByCustomerId(customerId);
				personHomePage.setInterest(interestList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 查询职业 by accountId
			List occupation = occupationMapper.selectByCustomerId(customerId);
			personHomePage.setOccupation(occupation);
			return personHomePage;
		} else {
			return null;
		}
	}

	/**
	 * 保存昵称和头像
	 * 
	 * @author liuyinkai
	 * @param params
	 */
	@Override
	public CommonResponse saveNicknameImage(SaveNickNameRequest params) {
		CommonResponse commonResponse = new CommonResponse();
		Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());
		String newNickname = params.getNickName();

		if (StringUtils.isNotEmpty(newNickname)) {
			if (newNickname.length() > 24) {
				throw new RemoteException(UserBizReturnCode.paramError, "您的昵称超出长度！");
			}
			String oldNickname = customer.getNickName();
			customer.setNickName(newNickname);
			if (newNickname.equals(oldNickname)) {

			} else {
				// 中文、英文、数字、下划线校验 4-24位
				Integer check = 2;
				// 敏感词
				Integer checkDetail = 1;
				Integer checkResult = checkNickName(newNickname);
				if (check.equals(checkResult)) {
					throw new RemoteException(UserBizReturnCode.paramError, "用户名不符合规则");
				} else if (checkDetail.equals(checkResult)) {
					throw new RemoteException(UserBizReturnCode.nickNameSensitive, "您输入的昵称包含敏感字，请重新输入！");
				}
			}
		}
		if (StringUtils.isNotEmpty(params.getHead_portrait_url())) {
			customer.setHeadPortraitUrl(params.getHead_portrait_url());
		}
		int result = customerMapper.updateByPrimaryKeySelective(customer);
		logger.info("=====saveNickName,更新数量" + result);
		if (result > 0) {

			// 刷新融云用户信息
			CodeSuccessReslut reslut = RongYunUtil.refreshUser(String.valueOf(customer.getCustomerId()),
					customer.getNickName(), customer.getHeadPortraitUrl());
			// 刷新失败
			if (reslut.getCode() != 200) {
				logger.error("刷新融云用户信息失败，用户id为：" + String.valueOf(customer.getCustomerId()) + "失败原因为："
						+ reslut.getErrorMessage());
			} else {
				logger.info("刷新融云用户信息成功！");
			}

			JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + params.getCustomerId(),
					JsonParseUtil.castToJson(customer));
			commonResponse.setData(customer);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
			return commonResponse;
		} else {
			throw new RemoteException(UserBizReturnCode.paramError, "参数错误，修改失败");
		}
	}

	/**
	 * 昵称规则校验
	 * 
	 * @modify liuyinkai
	 * @param nickName
	 *            用户昵称
	 * @return 0 - 通过，1 - 敏感词，2 - 中英文
	 */
	private Integer checkNickName(String nickName) {
		try {
			// 中文、英文、数字、下划线校验 4-24位
			Matcher m = CH_EN_PATTERN.matcher(nickName);
			if (!m.matches()) {
				return 2;
			}
			// 昵称敏感词校验
			List<SensitivityWord> sensitivityList = sensitivityWordMapper.querySensitiveName();
			if (Detect.notEmpty(sensitivityList)) {
				for (SensitivityWord obj : sensitivityList) {
					if (nickName.contains(obj.getContent())) {
						logger.info("昵称包含敏感词！");
						return 1;
					}
				}
			}
		} catch (Exception e) {
			logger.error("用户修改昵称校验异常！异常信息:{}", e.getMessage(), e);
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	/**
	 * 保存性别
	 * 
	 * @author liuyinkai
	 */
	@Override
	public CommonResponse saveGender(SaveGenderRequest params) {
		CommonResponse commonResponse = new CommonResponse();
		// 取账号信息并存redis
		Customer customer = customerRedisManagement.getCustomer(params.getCustomerId());
		Integer newGender = params.getGender();
		// 如果newGender 为空或者不等于 0、1 则返回错误
		if (null != newGender && newGender != 0 && newGender != 1) {
			throw new RemoteException(UserBizReturnCode.paramError, "参数错误，修改失败");
		}
		customer.setSex(newGender);
		// 更新性别
		int result = customerMapper.updateByPrimaryKeySelective(customer);
		if (result > 0) {
			JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + params.getCustomerId(),
					JsonParseUtil.castToJson(customer));
			commonResponse.setData(customer);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
			return commonResponse;
		}
		return null;
	}

	/**
	 * 保存签名
	 * 
	 * @author liuyinkai
	 */
	@Override
	public CommonResponse saveSignName(SaveSignNameRequest params) {

		CommonResponse commonResponse = new CommonResponse();
		Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());
		// 获取新签名
		String newSign = params.getSignName();
		// if (StringUtils.isNotEmpty(newSign)) {
		// 获取旧签名
		String oldSign = customer.getSignName();
		customer.setSignName(newSign);
		if (newSign.equals(oldSign)) {

		} else {
			if (StringUtils.isNotEmpty(newSign)) {
				if (newSign.length() > 200) {
					throw new RemoteException(UserBizReturnCode.paramError, "您的签名超出长度！");
				}
				List<SensitivityWord> sensitivityList = sensitivityWordMapper.querySensitiveName();
				for (SensitivityWord sensitiveWord : sensitivityList) {
					if (StringUtils.isNotEmpty(newSign) && newSign.contains(sensitiveWord.getContent())) {
						throw new RemoteException(UserBizReturnCode.nickNameSensitive, "您输入的签名包含敏感字，请重新输入！");
					}
				}
			}
		}
		int result = customerMapper.updateByPrimaryKeySelective(customer);
		logger.info("=====saveNickName,更新数量" + result);
		if (result > 0) {
			JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + params.getCustomerId(),
					JsonParseUtil.castToJson(customer));
			commonResponse.setData(customer);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
			return commonResponse;
		} else {
			throw new RemoteException(UserBizReturnCode.paramError, "参数错误，修改失败");
		}

	}

	/**
	 * 保存生日
	 * 
	 * @author liuyinkai
	 */
	@Override
	public CommonResponse saveBirthday(SaveBirthRequest params) {
		CommonResponse commonResponse = new CommonResponse();
		Customer customer = customerRedisManagement.getCustomer(params.getCustomerId());
		customer.setBirthday(params.getBirthday());// 生日
		customer.setStarSign(params.getStarSign());// 星座
		try {
			// 更新生日
			int result = customerMapper.updateByPrimaryKeySelective(customer);

			if (result > 0) {
				JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + params.getCustomerId(),
						JsonParseUtil.castToJson(customer));
				commonResponse.setData(customer);
				commonResponse.setCode(UserBizReturnCode.Success);
				commonResponse.setMessage(UserBizReturnCode.Success.desc());
				return commonResponse;
			}
			logger.info("=====saveGender,更新数量" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存兴趣
	 * 
	 * @author liuyinkai
	 */
	@Override
	@Transactional
	public CommonResponse saveInterest(SaveInterestRequest params) {
		CommonResponse commonResponse = new CommonResponse();
		CustomerInterest customerInterest = new CustomerInterest();
		customerInterest.setCustomerId(params.getCustomerId());

		Customer customer = customerRedisManagement.getCustomer(params.getCustomerId());
		// 获取兴趣,截取
		String interests = params.getInterestId();
		String[] interest = interests.split(",");
		if (null != customer) {
			// 更新customer_interest表
			try {
				// 如果customer_interest中间表有该用户，先删除该用户在此表的数据
				customerInterestMapper.deleteByCustomerId(params.getCustomerId());
				for (String str : interest) {
					customerInterest.setInterestId(Integer.parseInt(str));
					// customerInterest.setCreateTime(new Date());
					// 判断是否有重复数据
					// int num = customerInterestMapper.selectRepetitiveData(customerInterest);
					// if (num>0) {
					// //更新
					// customerInterestMapper.updateByCustomerIdSelective(customerInterest);
					// } else {
					// 插入
					int result = customerInterestMapper.insertSelective(customerInterest);
					// }
				}
				commonResponse.setData(customerInterest);
				commonResponse.setCode(UserBizReturnCode.Success);
				commonResponse.setMessage(UserBizReturnCode.Success.desc());
				return commonResponse;
			} catch (Exception e) {
				e.printStackTrace();
				throw new BizException(AccountBizReturnCode.JdbcError, "操作数据库异常");
			}
		} else {
			throw new BizException(AccountBizReturnCode.JdbcError, "操作数据库异常");
		}
	}

	/**
	 * 保存职业
	 * 
	 * @author liuyinkai
	 */
	@Override
	public CommonResponse saveOccupation(SaveOccupationRequest params) {

		CommonResponse commonResponse = new CommonResponse();
		CustomerOccupation customerOccupation = new CustomerOccupation();
		// 先存入用户id
		customerOccupation.setCustomerId(params.getCustomerId());

		Customer customer = customerRedisManagement.getCustomer(params.getCustomerId());
		// 获取职业
		int occupation = params.getOccupationId();
		if (null != customer) {
			// 更新customer_interest表
			try {
				// 存入职业ID
				// customerOccupation.setOccupationId(occupation);
				// 创建时间
				// customerOccupation.setCreateTime(new Date());
				// 查看重复数量
				int num = customerOccupationMapper.findRepetitveData(params.getCustomerId());
				if (num > 0) {
					// 更新
					// 存入职业ID
					customerOccupation.setOccupationId(occupation);
					customerOccupation.setModifyTime(new Date());// 更新修改时间
					int nn = customerOccupationMapper.updateByCustomerIdSelective(customerOccupation);
				} else {
					// 存入职业ID
					customerOccupation.setOccupationId(occupation);
					// 插入
					int result = customerOccupationMapper.insertSelective(customerOccupation);
				}
				commonResponse.setData(customerOccupation);
				commonResponse.setCode(UserBizReturnCode.Success);
				commonResponse.setMessage(UserBizReturnCode.Success.desc());
			} catch (Exception e) {
				e.printStackTrace();
				throw new BizException(AccountBizReturnCode.JdbcError, "操作数据库异常");
			}
			return commonResponse;
		} else {
			throw new BizException(AccountBizReturnCode.JdbcError, "操作数据库异常");
		}

	}

	/**
	 * 大V主页，普通用户主页（客态）
	 * 
	 * @author liuyinkai
	 * @param params
	 */
	@Override
	public CommonResponse showHomePageLogout(ShowHomePageLogout params) {
		CommonResponse commonResponse = new CommonResponse();
		if (null != params) {
			HomePageLogout homePageLogout = new HomePageLogout();
			try {

				// 获取主页所有资料
				homePageLogout = customerMapper.showHomePageLogout(params.getCustomerId());

				Integer voiceStatus = homePageLogout.getVoiceStatus();
				// voiceStatus == null 未录制声音
				if (voiceStatus == null) {
					voiceStatus = UserBizConstants.VOICE_STATUS_UNEXIST;
				}

				// 获取年纪
				Date birthday = homePageLogout.getBirthday();
				// 用工具类去转换
				int age = CountAge.getAgeByBirth(birthday);
				homePageLogout.setAge(age);
				// 获取兴趣名字
				List<Interest> interestName = interestMapper.selectInterestByCustomerId(params.getCustomerId());
				homePageLogout.setInterestName(interestName);
				// 获取职业名字
				List<Occupation> occupationName = occupationMapper.selectByCustomerId(params.getCustomerId());
				homePageLogout.setOccupationName(occupationName);
				// 判断是否是大V用户，只有拥有上架商品的用户和通过大V认证的用户才会显示大V认证
				int num = productMapper.queryVProductNum(params.getCustomerId());
				if (num > 0 && homePageLogout.getvStatus() == 2) {
					// 可以显示大V图标
					homePageLogout.setvStatus(1);
				} else {
					// 不显示大V图标
					homePageLogout.setvStatus(0);
				}
				// 主播擅长项目
				List<VProductTag> vProductTags = this.queryTag(params.getCustomerId());
				homePageLogout.setvProductTags(vProductTags);
				// 查询粉丝数量
				Long fansNum = fansMapper.queryFansNumByCustomerId(params.getCustomerId());
				homePageLogout.setFansNum(fansNum);
				// 查询关注数量
				int attentionNum = fansMapper.queryAttentionNumByCustomerId(params.getCustomerId());
				homePageLogout.setAttentionNum(attentionNum);

				// 判断当前用户是否关注对方
				Long userId = params.getMyUserId();
				Long customerId = params.getCustomerId();

				Integer attentionStatus = UserBizConstants.ATTENTION_STATUS_UN_ATTENED;
				if (userId != null) {
					Fans fans = fansMapper.queryFans(userId, customerId);
					if (fans != null) {
						attentionStatus = fans.getAttentionState();
					}
				}
				homePageLogout.setAttentionStatus(attentionStatus);

				commonResponse.setData(homePageLogout);
				commonResponse.setCode(UserBizReturnCode.Success);
				commonResponse.setMessage(UserBizReturnCode.Success.desc());
				return commonResponse;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		throw new BizException(AccountBizReturnCode.JdbcError, "未查询到此用户");
	}

	/**
	 * 查询主播产品标签
	 * 
	 * @author liuyinkai
	 * @param customerId
	 * @return
	 */
	public List<VProductTag> queryTag(Long customerId) {
		List<VProductTag> list = new ArrayList<VProductTag>();
		Orders orders = new Orders();
		// 获取标签名称id，价钱，服务时间
		list = productMapper.selectVProductTag(customerId);
		for (VProductTag tag : list) {
			try {
				// 获取标签名称
				Product product = productMapper.selectByPrimaryKey(customerId);
				if (product == null) {
					continue;
				}
				tag.setTagName(product.getName());
				// 获取该产品接单次数(订单完成状态)
				// 封装参数
				orders.setProductId(product.getProductId());
				orders.setSellerId(customerId);
				// 查询完成数量
				Orders num = ordersMapper.queryCompleteNumByCustomerIdProductId(orders);
				if (null != num) {
					int completeNum = orders.getOrderNum();

					tag.setCompletedOrderNum(completeNum);
				} else {
					tag.setCompletedOrderNum(0);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	@Override
	public CommonResponse queryInterestList(QueryInterestListRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			List<InterestVO> interestList = interestMapper.selectInterestList();
			commonResponse.setData(interestList);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
		} catch (Exception e) {
			logger.error("查询异常，异常信息：", e);
			// throw new RemoteException(UserBizReturnCode.UserNotExist, "用户不存在");
		}

		return commonResponse;
	}

	@Override
	public CommonResponse queryOccupationList(QueryOccupationListRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			List<OccupationVO> interestList = occupationMapper.selectOccupationList();
			commonResponse.setData(interestList);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
		} catch (Exception e) {
			logger.error("查询异常，异常信息：", e);
			// throw new RemoteException(UserBizReturnCode.UserNotExist, "用户不存在");
		}

		return commonResponse;
	}

	@Override
	public CommonResponse queryAttentionFansList(QueryAttentionFansListRequest request) {

		CommonResponse commonResponse = new CommonResponse();
		try {
			List<AttentionFansVO> resultList = new ArrayList<AttentionFansVO>();

			Long customerId = request.getCustomerId();
			Integer type = request.getType();
			if (UserBizConstants.QUERY_ATTENTION_LIST_TYPE == type) {
				// 查询关注列表
				resultList = fansMapper.queryAttentionListByCustomerId(customerId,
						UserBizConstants.ATTENTION_STATUS_ATTENED);
			} else if (UserBizConstants.QUERY_FANS_LIST_TYPE == type) {
				// 查询粉丝列表
				// resultList
				// =fansMapper.queryFansListByCustomerId(customerId,UserBizConstants.ATTENTION_STATUS_ATTENED);
				// 粉丝ID
				List<Long> fansIdList = fansMapper.queryFansIdListByCustomerId(customerId,
						UserBizConstants.ATTENTION_STATUS_ATTENED);

				if (CollectionUtils.isEmpty(fansIdList)) {

				} else {

					// 获取
					resultList = fansMapper.queryCustomerListByCustomerIdList(fansIdList);

					// 获取
					List<Fans> fansList = fansMapper.queryFansListByFansIdList(fansIdList, customerId);

					HashMap<Long, Integer> attentionStatusMap = new HashMap<Long, Integer>();
					if (!CollectionUtils.isEmpty(fansList)) {
						for (Fans fans : fansList) {
							Long anchorId = fans.getAnchorId();
							attentionStatusMap.put(anchorId, fans.getAttentionState());
						}
					}

					for (AttentionFansVO vo : resultList) {
						Long custId = vo.getCustomerId();
						Integer attentionStatus = attentionStatusMap.get(custId);
						if (attentionStatus == null) {
							attentionStatus = UserBizConstants.ATTENTION_STATUS_UN_ATTENED;
						}
						vo.setAttentionStatus(attentionStatus);
					}

				}

			}
			commonResponse.setData(resultList);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
		} catch (Exception e) {
			logger.error("查询异常，异常信息：", e);
			// throw new RemoteException(UserBizReturnCode.UserNotExist, "用户不存在");
		}

		return commonResponse;
	}

	@Override
	public CommonResponse addOrCancelFans(AddOrCancelFansRequest request) {
		if (request == null || request.getFansId() == null || request.getAttendedId() == null) {
			throw new BizException(UserBizReturnCode.paramError, "参数异常");
		}

		CommonResponse commonResponse = new CommonResponse();

		try {

			Long fansId = request.getFansId();
			Long attendedId = request.getAttendedId();
			Integer type = request.getType();

			Fans fans = fansMapper.queryFans(fansId, attendedId);

			if (UserBizConstants.ATTENTION_TYPE_ADD == type) {
				// 添加关注
				if (fans == null) {
					// 添加关注
					Fans fan = new Fans();
					fan.setId(UUIDUtils.getId());
					fan.setAttentionState(UserBizConstants.ATTENTION_STATUS_ATTENED);
					fan.setCreateTime(new Date());
					fan.setFansId(fansId);
					fan.setAnchorId(attendedId);
					fansMapper.insert(fan);
				} else {
					// 更改状态为关注
					Integer attentionStatus = fans.getAttentionState();
					if (UserBizConstants.ATTENTION_STATUS_UN_ATTENED == attentionStatus) {
						Fans record = new Fans();
						record.setId(fans.getId());
						record.setAttentionState(UserBizConstants.ATTENTION_STATUS_ATTENED);
						record.setModifyTime(new Date());
						fansMapper.updateByPrimaryKey(record);
					}
				}
			} else if (UserBizConstants.ATTENTION_TYPE_CANCEL == type) {
				// 取消关注
				if (fans != null) {
					Integer attentionStatus = fans.getAttentionState();
					if (UserBizConstants.ATTENTION_STATUS_ATTENED == attentionStatus) {
						// 更改状态
						Fans record = new Fans();
						record.setId(fans.getId());
						record.setAttentionState(UserBizConstants.ATTENTION_STATUS_UN_ATTENED);
						record.setModifyTime(new Date());
						fansMapper.updateByPrimaryKey(record);
					}
				}

			}

			commonResponse.setData("00000");
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
		} catch (Exception e) {
			logger.error("查询异常，异常信息：", e);
			// throw new RemoteException(UserBizReturnCode.UserNotExist, "用户不存在");
		}

		return commonResponse;
	}

	@Override
	public CommonResponse checkAttention(CheckAttentionRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		return commonResponse;
	}

}
