<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="community:statistics:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">统计管理</h1>
            <ul class="breadcrumb">
                <li>社区统计分析</li>
                <li class="active">社区统计列表</li>
                <spam>&nbsp;&nbsp;<font color="red">(*时间不填或只填一个默认查近15天数据)</font></spam>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-4">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">时间</div>
                            <input class="form-control" type="date" id="sTime">
                            <div class="input-group-addon">到</div>
                            <input class="form-control" type="date" id="eTime">
                        </div>
                    </div>
                </div>
                <div class="col-md-1">
                    &nbsp;
                </div>
                <div class="col-md-1">
                    <button type="button" class="btn btn-primary btn-small btn-block"
                            id="query">
                        <i class="glyphicon glyphicon-search"></i> 查询
                    </button>
                </div>
                <div class="col-md-1">
                    <div class="form-group">
                        <a class="btn btn-primary btn-small btn-block" id="data_export" onclick="exportData();">导出数据</a>
                    </div>
                </div>
            </div>
            <table id="example" class="table"></table>
        </div>

        <script>
            //表格的初始化
            $(document).ready(function () {
                var table = $('#example').initTable({
                    sAjaxSource:"${productLabels }"+"/initTable.htm",
                    aoColumns: [
                        {
                            "data": "date",
                            "sTitle": "统计时间",
                            'sClass': "text-center"
                        },
                        {
                            "data": "actualPraiseNum",
                            "sTitle": "实际点赞数量",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == null || data =='') {
                                    return 0;
                                } else {
                                    return data;
                                }
                            }
                        },
                        {
                            "data": "actualSeeNum",
                            "sTitle": "实际查看数量",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == null || data =='') {
                                    return 0;
                                } else {
                                    return data;
                                }
                            }
                        },
                        {
                            "data": "actualCommentNum",
                            "sTitle": "实际评论数量",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == null || data =='') {
                                    return 0;
                                } else {
                                    return data;
                                }
                            }
                        },
                        {
                            "data": "realPraiseNum",
                            "sTitle": "点赞总人数",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == null || data =='') {
                                    return 0;
                                } else {
                                    return data;
                                }
                            }
                        },
                        {
                            "data": "realSeeNum",
                            "sTitle": "查看总人数",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == null || data =='') {
                                    return 0;
                                } else {
                                    return data;
                                }
                            }
                        },
                        {
                            "data": "realCommentNum",
                            "sTitle": "评论总人数",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == null || data =='') {
                                    return 0;
                                } else {
                                    return data;
                                }
                            }
                        },
                        {
                            "data": "postIdCount",
                            "sTitle": "发帖量",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == null || data =='') {
                                    return 0;
                                } else {
                                    return data;
                                }
                            }
                        },
                        {
                            "data": "acountIdCount",
                            "sTitle": "发帖人数",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == null || data =='') {
                                    return 0;
                                } else {
                                    return data;
                                }
                            }
                        }
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "sDate", "value": $("#sTime").val()});
                        aoData.push({"name": "eDate", "value": $("#eTime").val()});
                    },
                });

                $('#query').click(function () {
                    $('#example').dataTable().fnDraw();
                });

            });

            function exportData() {
                var href = "export/community.htm"
                    + "?sDate=" + $("#sTime").val()
                    + "&eDate=" + $("#eTime").val();
                $('#data_export').attr("href", href);
            }

        </script>
        <!---dialog选项-->
        <div>
            <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>
            <jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp"/>

        </div>
    </div>
</shiro:hasPermission>