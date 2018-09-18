<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="accountdetailinfo:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">用户详情信息新增face++报告列表</h1>
			<ul class="breadcrumb">
				<li>用户详情信息新增face++报告管理</li>
				<li class="active">用户详情信息新增face++报告列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">真实姓名</div>
							<input class="form-control" type="text" name="accountName"
								id="accountName">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<button type="button" class="btn btn-primary btn-small btn-block"
						id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
				</div>
			</div>
			<table id="example" class="table"></table>
		</div>
		<script>
			//表格的初始化

  
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"accountdetailinfo/initTable.htm",
					aoColumns: [
					            { 
					                "data": "accountPingFen",
					                "sTitle":"人脸识别质量评分",
					                'sClass':"text-center"
					            },
			            { 
			                "data": "accountName",
			                "sTitle":"真实姓名",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "accountMZhu",
			                "sTitle":"名族",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "gender",
			                "sTitle":"性别",
			                'sClass':"text-center",
			                "mRender": function(data, ifMarriage, full) { 
			                	if(data == 1){
									return "<font color='red'>男</font>";
								}else if(data == 2){
									return "<font color='blue'>女</font>";
								}else{
									return "<font color='blue'></font>";
								}
			                } 
			               
			            },
			            { 
			                "data": "accountAdress",
			                "sTitle":"出生",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "liveAddress",
			                "sTitle":"居住地详细地址",
			                'sClass':"text-center"
			            }, 
			            { 
						    "data": "idCode",
						    "sTitle":"公民身份证号码",
						    'sClass':"text-center" 
						  },
			            { 
			                "data": "schoolName",
			                "sTitle":"学校名称",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "fontPic",
			                "sTitle":"身份证正面照",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "backPic",
			                "sTitle":"身份证反面照",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "facePic",
			                "sTitle":"活体人脸照",
			                'sClass':"text-center"
			              
			            },
			            { 
			                "data": "education",
			                "sTitle":"学历",
			                'sClass':"text-center"
			               
			            },
			           
			            { 
			                "data": "accountZhengJIE",
			                "sTitle":"综合结果",
			                'sClass':"text-center"
			            },
			           
			            { 
			                "data": "ifMarriage",
			                "sTitle":"婚姻状况",
			                'sClass':"text-center",
			                "mRender": function(data, ifMarriage, full) { 
			                	if(data == 1){
									return "<font color='red'>已婚</font>";
								}else if(data == 2){
									return "<font color='blue'>未婚</font>";
								}else{
									return "<font color='blue'>未婚</font>";
								}
			                } 
			            }
			           
			            
			            <shiro:hasPermission name="accountdetailinfo:update or accountdetailinfo:delete">
			            ,{"data": "detailInfoId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
					 	aoData.push({ "name": "accountName", "value": $("#accountName").val() } ); 
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="accountdetailinfo:update or accountdetailinfo:delete">
		             	{"aTargets" : 14,"mRender" : function(data, type, row){
		             	   
		             		  var  detail="";
		             		<shiro:hasPermission name="accountdetailinfo:update">
		             		 edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看详情</a>";
		             		/*  detail = "<a href='#' onclick='detailRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看</a>"; */
		         		     </shiro:hasPermission>
		             		
		             		
		         		    return  edit;
		                }
		             }
		            	</shiro:hasPermission>
		             ]
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			
			});
			 //增加或者修改受影响的行数
            function addAndUpdateRow(id){
                $('#insertAndUpdate').addAndUpdateRow("accountdetailinfo/addAndUpdateHome.htm?id="+id);
            }
			
			</script>
		<!---dialog选项-->
		<div>
			<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />
			<jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp" />

			<div class="modal fade" id="insertAndUpdate" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			</div>
		</div>
	</div>
</shiro:hasPermission>