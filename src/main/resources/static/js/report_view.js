$(function () {

    //得到上个页面传来的id值
    var id = window.location.href.substring(window.location.href.lastIndexOf("?") + 4);
    //先查看是否有user 再将其发送给后台校验 查看他的权限 再展示不同的页面
    var username = localStorage.getItem("username");
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
                if(data == '')
                {
                    window.location.href = 'login.html';
                }else
                {
                    var name = data.role.name;
                    if(name = '老师')
                    {
                        //通过id获得report对象
                        $.ajax(
                            {
                                url:'http://localhost:8090/user/getreportbyid',
                                async: false,
                                type: 'post',
                                dataType: 'JSON',
                                data:{
                                    id:id
                                },
                                success:function (data) {
                                    $(".info-item-stu_username").text(data.stu_username);
                                    $(".info-item-stu_nickname").text(data.stu_nickname);
                                    $(".info-item-pro_name").text(data.project.project_name);
                                    $(".info-item-pro_credit").text(data.credit);
                                    $(".info-item-pro_type").text(data.project.type);
                                    $(".info-item-pro_description").text(data.project.description);
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

    //通过按钮
    $(".btn-pass").click(function () {
       //项目状态变为通过 学生学分增加
       $.ajax(
           {
               url:'http://localhost:8090/user/passproject',
               type: 'POST',
               async: false,
               dataType:'text',
               data:{
                    id:id,
                    username:username
               },
               success:function (data) {
                   if(data == 'success')
                   {
                       alert("提交成功 确认后返回");
                       window.location.href = 'review.html';
                   }else
                   {
                       alert("提交失败");
                   }
               },
           }
       )
    });

    //不通过按钮
    $(".btn-reject").click(function () {
        //项目状态变为不通过
        $.ajax(
            {
                url:'http://localhost:8090/user/rejectproject',
                type: 'POST',
                async: false,
                dataType:'text',
                data:{
                    id:id
                },
                success:function (data) {
                    if(data == 'success')
                    {
                        alert("提交成功 确认后返回");
                        window.location.href = 'review.html';
                    }else
                    {
                        alert("提交失败");
                    }
                },
            }
        )
    });

    //返回按钮
    $(".btn-back").click(function () {
        window.location.href = 'review.html';
    })
});