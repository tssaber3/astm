$(function () {
    //先查看是否有user 再将其发送给后台校验 查看他的权限 再展示不同的页面
    var username = localStorage.getItem("username");
    $.ajax(
        {
            url:'http://localhost:8090/user/iflogin',
            type:'POST',
            dataType:'JSON',
            async:false,
            // xhrFields: {withCredentials: true},
            data:{
                username:username
            },
            success:function (data) {
                if(data == 'null')
                {
                    window.location.href = 'login.html';
                }else {
                    var name = data.role.name;
                    if(name == '管理员')
                    {
                        // 进行页面加载的到除自己之外的所有用户
                        $.ajax(
                            {
                                url:'http://localhost:8090/user/getAllUser',
                                type:"get",
                                dataType:'json',
                                async: false,
                                success:function (data) {
                                    var list = data.user;
                                    for (var i = 0;i < list.length;i++)
                                    {
                                        if(list[i].role.name == '老师')
                                        {
                                            list[i].deparment = '无';
                                            list[i].grade = '无';
                                            list[i].score = '无';
                                        }
                                        $(".stu-info-list").append('<div class="stu-info">\n' +
                                            '                <div class="stu-info-project_name">'+ list[i].role.name +'</div>\n' +
                                            '                <div class="stu-info-user_nickname">'+ list[i].nickname +'</div>\n' +
                                            '                <div class="stu-info-user_name">'+ list[i].username +'</div>\n' +
                                            '                <div class="stu-info-project_type">'+ list[i].deparment +'</div>\n' +
                                            '                <div class="stu-info-project_credit">'+ list[i].grade +'</div>\n' +
                                            '                <div class="stu-info-project_proof">'+ list[i].score +'</div>\n' +
                                            '                <div id="stu-info-id" style="display: none">'+ list[i].id +'</div>\n' +
                                            '            </div>')
                                    }
                                },
                            }
                        );
                        $.ajax(
                            {
                                url:'http://localhost:8090/user/getAllReport',
                                type:"get",
                                dataType:'json',
                                async: false,
                                success:function (data) {
                                    var list = data.report;
                                    for (var i = 0;i < list.length;i++)
                                    {
                                        $(".project-list").append('<div class="project-info">\n' +
                                            '                <div class="project-info-project_name">'+ list[i].project.project_name +'</div>\n' +
                                            '                <div class="project-info-user_nickname">'+ list[i].stu_nickname +'</div>\n' +
                                            '                <div class="project-info-user_name">'+ list[i].stu_username  +'</div>\n' +
                                            '                <div class="project-info-project_type">'+ list[i].project.type +'</div>\n' +
                                            '                <div class="project-info-project_credit">'+ list[i].credit +'</div>\n' +
                                            '                <div class="project-info-project_proof">'+ list[i].proof +'</div>\n' +
                                            '                <div style="display: none" id="project-info-project_id">'+ list[i].id +'</div>\n' +
                                            '            </div>')
                                    }
                                },
                                error:function (data) {
                                    alert("s");
                                }
                            }
                        )
                    }else
                    {
                        window.location.href = 'login.html';
                    }
                }
            }
        }
    );

    $("#stu_search").click(function () {
        var username = $("#stu_input").val();
        $.ajax(
            {
                url:'http://localhost:8090/user/findUserlike',
                type:"post",
                dataType:'json',
                async: false,
                data: {
                    username:username
                },
                success:function (data) {
                    var list = data.user;
                    $(".stu-info-list").html("");
                    for (var i = 0;i < list.length;i++)
                    {
                        if(list[i].role.name == '老师')
                        {
                            list[i].deparment = '无';
                            list[i].grade = '无';
                            list[i].score = '无';
                        }
                        $(".stu-info-list").append('<div class="stu-info">\n' +
                            '                <div class="stu-info-project_name">'+ list[i].role.name +'</div>\n' +
                            '                <div class="stu-info-user_nickname">'+ list[i].nickname +'</div>\n' +
                            '                <div class="stu-info-user_name">'+ list[i].username +'</div>\n' +
                            '                <div class="stu-info-project_type">'+ list[i].deparment +'</div>\n' +
                            '                <div class="stu-info-project_credit">'+ list[i].grade +'</div>\n' +
                            '                <div class="stu-info-project_proof">'+ list[i].score +'</div>\n' +
                            '                <div id="stu-info-id" style="display: none">'+ list[i].id +'</div>\n' +
                            '            </div>')
                    }
                }
            }
        )

    });

        $(document).on("click",".stu-info",function () {
        var id = $(this).children("#stu-info-id").text();
        window.location.href = "Stu_info.html?id=" + id;
    });
    $(document).on("click",".project-info",function () {
        var id = $(this).children("#project-info-project_id").text();
        window.location.href = "Rep_info.html?id=" + id;
    });

        $("#report-search").click(function () {
            var report_id = $("#report-input").val();
            $.ajax(
                {
                    url:'http://localhost:8090/user/findreportbyid',
                    type:"post",
                    dataType:'json',
                    async: false,
                    data: {
                        id:report_id
                    },
                    success:function (data) {
                        $(".project-info").html('');
                        $(".project-list").append('<div class="project-info">\n' +
                            '                <div class="project-info-project_name">'+ data.project.project_name +'</div>\n' +
                            '                <div class="project-info-user_nickname">'+ data.stu_nickname +'</div>\n' +
                            '                <div class="project-info-user_name">'+ data.stu_username  +'</div>\n' +
                            '                <div class="project-info-project_type">'+ data.project.type +'</div>\n' +
                            '                <div class="project-info-project_credit">'+ data.credit +'</div>\n' +
                            '                <div class="project-info-project_proof">'+ data.proof +'</div>\n' +
                            '                <div style="display: none" id="project-info-project_id">'+ data +'</div>\n' +
                            '            </div>')
                    },

                }
            )
        });
});