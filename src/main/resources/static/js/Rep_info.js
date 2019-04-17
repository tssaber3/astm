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
                                url:'http://localhost:8090/user/findreportbyid',
                                type: 'POST',
                                async: false,
                                dataType: 'JSON',
                                data:
                                    {
                                        id:id
                                    },
                                success:function (data) {
                                    $("#content-username").val(data.stu_nickname);
                                    $("#content-nickname").val(data.stu_username);
                                    $("#content-pro-name").val(data.project.project_name);
                                    $("#content-credit").val(data.credit);
                                    $("#info-item-content-deparment").text(data.project.type);
                                    $("#content-description").val(data.project.description);
                                }
                            }
                        )
                    }
                }
            }
        }
    );


    $(".info-item-content-deparment").click(function () {
        $(".drop-cascader-container").css("display","block");
    });


//    取消按钮
    $("#cascader-cancel").click(function () {
        $(".drop-cascader-container").css("display","none");
    });

    $(".cascader-item").click(function () {
        $("#info-item-content-deparment").html($(this).children("p").html());
        $(".drop-cascader-container").css("display","none");
    });

    $(".btn-back").click(function () {
        window.location.href = 'admin.html';
    });

    //    提交按钮
    $(".btn-shenghe").click(function () {
        var bok = true;
        if($("#content-username").val() == ''|| $("#content-nickname").val() == '')
        {
            alert("请填写完整");
            bok = false;
        }

        if($("#content-pro-name").val() == '')
        {
            $(".project_name_error").css("display","inline-block");
            bok = false;
        }else
        {
            $(".project_name_error").css("display","none");
        }
        if($("#content-credit").val() == '')
        {
            $(".credit_error").css("display","inline-block");
            bok = false;
        }else
        {
            var reg = /^[0-9]*$/;
            if(reg.test($("#content-credit").val()))
            {
                $(".credit_error").css("display","none");

            }else
            {
                $(".credit_error").css("display","inline-block");
                bok = false;
            }
        }
        if($("#info-item-content-deparment").text() == '点击选择' ||$(".info-item-content-deparment").text() == '')
        {
            $(".project_type_error").css("display","inline-block");
            bok = false;
        }else
        {
            $(".project_type_error").css("display","none");
        }
        if($("#content-description").val() == '')
        {
            $(".description_error").css("display","inline-block");
            bok = false;
        }else
        {
            $(".description_error").css("display","none");
        }

        if(bok)
        {
            var username = $("#content-username").val();
            var nickname = $("#content-nickname").val();
            var project_name = $("#content-pro-name").val();
            var credit = $("#content-credit").val();
            var type = $("#info-item-content-deparment").text();
            var description = $("#content-description").val();

            $.ajax(
                {
                    url:'http://localhost:8090/user/savereport',
                    type: 'POST',
                    async: false,
                    dataType: 'text',
                    data:{
                        username:username,
                        nickname:nickname,
                        project_name:project_name,
                        credit:credit,
                        type:type,
                        description:description,
                        id:id
                    },
                    success:function (data) {
                        alert("修改成功");
                        window.location.href = 'admin.html';
                    },
                    error:function (data) {
                    }
                }
            )
        }

    });
});