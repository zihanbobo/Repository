<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h3 id="myModalLabel">${empty entity?'新增':'查看' }用户详情信息新增信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="productRuleForm"
				name="productRuleForm"
				action="account/basic/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-4 control-label">用户编号</label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" class="form-control"
							name="accountId" value="${entity.accountId}"> <input
							type="hidden" name="accountId" value="${entity.accountId}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">手机号码</label>
					<div class="col-sm-8">
						<input type="number" readonly="readonly" class="form-control"
							name="phoneNum" value="${entity.phoneNum}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">真实姓名</label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" class="form-control"
							name="accountName" value="${entity.accountName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">身份证号码<font
						color="red">&nbsp;</font></label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" class="form-control"
							name="idCode" value="${entity.idCode}">

					</div>
				</div>

				<div class="form-group">
					<label readonly="readonly" class="col-sm-4 control-label">性别<font
						color="red">&nbsp;</font></label>
					<div class="col-sm-8">
						<label readonly="readonly" class="checkbox-inline"> <input
							type="radio" name="gender" value="1"
							${entity.gender=='1'?'checked':'' }> 男
						</label> <label readonly="readonly" class="checkbox-inline"> <input
							type="radio" name="gender" value="0"
							${entity.gender=='2'?'checked':'' }> 女
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">工作地详细地址</label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" class="form-control"
							name="workAddress" value="${entity.workAddress}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">学校名称</label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" class="form-control"
							name="schoolName" value="${entity.schoolName}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">身份证正面照</label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" class="form-control"
							name="fontPic" value="${entity.fontPic}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">身份证反面照</label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" class="form-control"
							name="backPic" value="${entity.backPic}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">活体人脸照</label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" class="form-control"
							name="facePic" value="${entity.facePic}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">学历</label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" name="education"
							class="form-control" value="${entity.education}" />
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-4 control-label">居住地详细地址</label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" class="form-control"
							name="liveAddress" value="${entity.liveAddress}" />
					</div>
				</div>
				<div class="form-group">
					<label readonly="readonly" class="col-sm-4 control-label">婚姻状况</label>
					<div class="col-sm-8">
						<select readonly="readonly" class="form-control" id="ifMarriage"
							name="ifMarriage">
							<option value="1" ${entity.ifMarriage=='1'?'selected':''}>已婚</option>
							<option value="2" ${entity.ifMarriage=='2'?'selected':''}>未婚</option>
						</select>
					</div>
				</div>



				<span id="tip"
					style="color: red; font-size: 14px; margin-left: 20px;"></span>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">取消</button>
		</div>
	</div>
</div>

<script type="text/javascript">
	
</script>