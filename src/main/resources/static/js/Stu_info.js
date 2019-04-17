$(function () {

    var id = window.location.href.substring(window.location.href.lastIndexOf("?") + 4);
    //先查看是否有user 再将其发送给后台校验 查看他的权限 再展示不同的页面
    var username = localStorage.getItem("username");
    // false为老师 true为学生
    var bok = false;
    $.ajax(
        {
            url: 'http://localhost:8090/user/iflogin',
            type: 'POST',
            async: false,
            dataType: 'JSON',
            // xhrFields: {withCredentials: true},
            data: {
                username: username
            },
            success: function (data) {
                if(data == null)
                {
                    window.location.href = 'login.html';
                }else
                {
                    var name = data.role.name;
                    if(name == '管理员')
                    {
                        $.ajax(
                            {
                                url:'http://localhost:8090/user/finduserbyid',
                                type: 'POST',
                                async: false,
                                dataType: 'JSON',
                                data:
                                    {
                                        id:id
                                    },
                                success:function (data) {
                                    var role = data.role.name;
                                    if(role == '老师')
                                    {
                                        $(".tea-info").css("display","block");
                                        $("#content-teacher-nickname").val(data.nickname);
                                        $("#content-teacher-username").val(data.username);

                                    }else if(role == '学生')
                                    {
                                        bok = true;
                                        $(".stu_info").css("display","block");
                                        $("#content-nickname").val(data.nickname);
                                        $("#content-username").val(data.username);
                                        $("#content-deparment").val(data.deparment);
                                        $("#content-score").val(data.score);
                                        $("#content-grade").val(data.grade);

                                    }else
                                    {
                                        window.location.href = 'login.html';
                                    }
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

    $(".btn-back").click(function () {
        window.location.href = 'admin.html';
    });
    
    $(".btn-save").click(function () {
        if(bok)
        {
            //学生
            if($("#content-nickname").val() == '' || $("#content-username").val() == ''||$("#content-deparment").val() == ''||$("#content-score").val() == ''||$("#content-grade").val() == '')
            {
                alert("请填写完整");
            }else
            {
                var username = $("#content-nickname").val();
                var nickname = $("#content-username").val();
                var deparment = $("#content-deparment").val();
                var score = $("#content-score").val();
                var grade = $("#content-grade").val();
                $.ajax(
                    {
                        url:'http://localhost:8090/user/saveuser',
                        type: 'POST',
                        async: false,
                        dataType: 'text',
                        data:{
                            username:username,
                            nickname:nickname,
                            deparment:deparment,
                            score:score,
                            grade:grade,
                            id:id
                        },
                        success:function (data) {
                            alert("修改成功 确定后返回");
                            window.location.href = 'admin.html';
                        }
                    }
                )
            }
        }else
        {
            //老师
            if($("#content-teacher-nickname").val() == ''||$("#content-teacher-username").val() == '')
            {
                alert("请填写完整");
            }else
            {
                var username = $("#content-teacher-nickname").val();
                var nickname = $("#content-teacher-username").val();
                $.ajax(
                    {
                        url:'http://localhost:8090/user/saveteacher',
                        type: 'POST',
                        async: false,
                        dataType: 'text',
                        data:{
                            username:username,
                            nickname:nickname,
                            id:id
                        },
                        success:function (data) {
                            alert("修改成功 确定后返回");
                            window.location.href = 'admin.html';
                        }
                    }
                )
            }
        }
    });
});